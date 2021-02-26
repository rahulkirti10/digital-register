package com.docsapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.docsapi.entities.UserDetails;

@Component
public interface UserDetailsRepository extends JpaRepository<UserDetails,String>{

	@Transactional
	@Modifying
	@Query("update UserDetails u set u.phone_no= :phone_no where u.user_id= :user_id")
	int updatePhone(String user_id, String phone_no);

	@Transactional
	@Modifying
	@Query("update UserDetails u set u.b_name= :bname where u.user_id= :user_id")
	int updateBname(String user_id, String bname);

	@Transactional
	@Modifying
	@Query("update UserDetails u set u.b_add1= :b_add1, u.b_add2= :b_add2,u.pin= :pin,u.city_state_country= :city_state_country where u.user_id= :user_id")
	int updateAddress(String user_id, String b_add1, String b_add2, String pin, String city_state_country);

	@Query("select  u from UserDetails u")
	List<UserDetails> docuemntCount();
	
	@Query("select u.document_count from UserDetails u where u.user_id= :user_id")
	String findCount(String user_id);

	@Transactional
	@Modifying
	@Query("update UserDetails u set u.document_count= :count where u.user_id= :user_id")
	void updateCount(String count,String user_id);

	@Transactional
	@Modifying
	@Query("update UserDetails u set u.branding= :branding where u.user_id= :user_id")
	int UpdateBranding(String user_id,String branding);

	@Transactional
	@Modifying
	@Query("update UserDetails u set u.b_details= :branding where u.user_id= :user_id")
	int UpdateBdetails(String user_id, String branding);

}
