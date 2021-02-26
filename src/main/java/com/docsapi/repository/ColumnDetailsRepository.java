package com.docsapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.docsapi.entities.ColumnDetails;
import com.docsapi.entities.GetDataByColumn;

public interface ColumnDetailsRepository extends JpaRepository<ColumnDetails, Long>{
	
	@Query("select c from ColumnDetails c where c.document.doc_id= :doc_id order by c.id asc")
	List<ColumnDetails> findById(@Param("doc_id") long doc_id);
	
	@Transactional
	@Modifying
	@Query("delete from ColumnDetails c where c.document.doc_id= :doc_id")
	void deleteBydocId(@Param("doc_id") long doc_id);
	
	@Transactional
	@Modifying
	@Query("delete from ColumnDetails c where c.id= :col_id")
	void deleteColumn(@Param("col_id") long col_id);
	
	@Transactional
	@Modifying
	@Query("update ColumnDetails c set c.column_names= :column_names where c.id= :id")
	int changeColumn(@Param("column_names") String column_names,@Param("id") long id);
	
	@Transactional
	@Modifying
	@Query("update ColumnDetails c set c.column_nums= :column_nums where c.document.doc_id= :doc_id")
	int addColumn(@Param("column_nums") String column_nums,@Param("doc_id") long doc_id);

	@Transactional
	@Modifying
	@Query("update ColumnDetails c set c.row_nums= :row_nums where c.document.doc_id= :doc_id")
	void addRow(@Param("row_nums")String row_nums,@Param("doc_id") long doc_id);

	@Transactional
	@Modifying
	@Query("update ColumnDetails c set c.total= :value where c.id= :id")
	int updateTotal(@Param("value")String value,@Param("id") long id);
	
	@Transactional
	@Modifying
	@Query("update ColumnDetails c set c.column_type= :columnType where c.id= :id")
	int changeColumnType(@Param("columnType") String columnType,@Param("id") long id);

	@Transactional
	@Modifying
	@Query("update ColumnDetails c set c.formula= :formula where c.id= :id")
	int saveFormula(@Param("id") long id,@Param("formula") String formula);
	
	
}
