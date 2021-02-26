package com.docsapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.docsapi.entities.SavedData;

@Service
@Repository
public interface SavedDataRepository extends CrudRepository<SavedData, Long> {

	
	@Query("select s from SavedData s where s.docs.doc_id= :doc_id")
	List<SavedData> findUpdate(@Param("doc_id") long doc_id);
	
	@Query("select s.id from SavedData s where s.docs.doc_id= :doc_id")
	List<String> findAllIdBydoc_id(@Param("doc_id") long doc_id);
	
	
	@Transactional
	@Modifying
	@Query("delete from SavedData s where s.docs.doc_id= :doc_id")
	int deletedataByDocId(@Param("doc_id") long doc_id);
	
	
	
	@Transactional
	@Modifying
	@Query("delete from SavedData s where s.docs.doc_id= :doc_id")
	int deleteExisting(@Param("doc_id") long doc_id);
	
	@Query("select s from SavedData s where s.docs.doc_id= :doc_id order by s.docs.doc_id asc")
	List<SavedData> findById(@Param("doc_id") long doc_id);

	@Query("select s from SavedData s where s.docs.doc_id= :doc_id order by s.serialno+0 asc")
	List<SavedData> findBySerial(@Param("doc_id") long doc_id);
	
	@Modifying
	@Transactional
	@Query("update SavedData s set s.serialno= :serial where s.id= :id")
	int UpdateRowBelow(@Param("serial") int serial,@Param("id") long id);
	
	
	@Modifying
	@Transactional
	@Query("update SavedData s set s.column1= :column1,s.column2= :column2,s.column3= :column3,s.column4= :column4,s.column5= :column5,s.column6= :column6,s.column7= :column7,"
			+ "s.column8= :column8,s.column9= :column9,s.column10= :column10,s.column11= :column11,s.column12= :column12,s.column13= :column13,s.column14= :column14,"
			+ "s.column15= :column15,s.column16= :column16,s.column17= :column17,s.column18= :column18,s.column19= :column19,s.column20= :column20,"
			+ "s.height= :height, s.width= :width where s.id= :id")
	void updateExisting(@Param("column1") String column1,@Param("column2") String column2,@Param("column3") String column3,@Param("column4") String column4
			,@Param("column5") String column5,@Param("column6") String column6,@Param("column7") String column7,@Param("column8") String column8,
			@Param("column9") String column9,@Param("column10") String column10,@Param("column11") String column11,@Param("column12") String column12
			,@Param("column13") String column13,@Param("column14") String column14,@Param("column15") String column15,@Param("column16") String column16
			,@Param("column17") String column17,@Param("column18") String column18,@Param("column19") String column19,@Param("column20") String column20
			,@Param("height") String height,@Param("width") String width
			,@Param("id") long id);
	
}
