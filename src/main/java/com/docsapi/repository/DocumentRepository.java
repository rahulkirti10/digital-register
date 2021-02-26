package com.docsapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.docsapi.entities.Document;
@Component
public interface DocumentRepository  extends JpaRepository<Document, Long>{
	
	@Transactional
	@Modifying
	@Query("delete from Document d where d.doc_id= :doc_id")
	void deleteById(@Param("doc_id") long doc_id);
	
	
	@Query("select d from Document d where d.details.user_id= :user_id")
	List<Document> findAll(@Param("user_id") String user_id);

	@Query("select count(d.id) from Document d where d.details.user_id= :user_id")
	int count( String user_id);

	@Transactional
	@Modifying
	@Query("update Document d set d.document_name= :doc_name where d.doc_id= :doc_id")
	int changeName(@Param("doc_name") String doc_name,@Param("doc_id") long doc_id);

	@Transactional
	@Modifying
	@Query("update Document d set d.update_document= :update where d.doc_id= :doc_id")
	int updateDocs(@Param("update") String update,@Param("doc_id")long doc_id);
	
}
