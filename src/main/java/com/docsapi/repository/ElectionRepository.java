package com.docsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.docsapi.entities.Election;

public interface ElectionRepository extends JpaRepository<Election, Long> {

	@Query("select max(e.id) from Election e")
	long getId();

}
