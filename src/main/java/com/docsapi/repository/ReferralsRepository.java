package com.docsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.docsapi.entities.Document;
import com.docsapi.entities.Referrals;

@Service
@Repository
public interface ReferralsRepository  extends JpaRepository<Referrals, Long>{
	
	
	@Query("select r from Referrals r where r.referFrom= :referFrom")
	List<Referrals> findCount(String referFrom);

	
}
