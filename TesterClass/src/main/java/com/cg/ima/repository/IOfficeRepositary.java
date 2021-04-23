package com.cg.ima.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ima.entity.Offer;

@Repository
public interface IOfficeRepositary extends JpaRepository<Offer, Integer>{
	@Query(value ="select	 \"OFFER\".\"IS_AVAILABLE\" as \"IS_AVAILABLE\",\r\n" + 
			"	 \"OFFER\".\"AVAILABLE_UPTO\" as \"AVAILABLE_UPTO\",\r\n" + 
			"	 \"OFFER\".\"OFFER_ID\" as \"OFFER_ID\" \r\n" + 
			" from	 \"RES_TEST\" \"RES_TEST\",\r\n" + 
			"	 \"OFFER\" \"OFFER\" \r\n" + 
			" where 	 \"RES_TEST\".\"CATEGORY\" =:cat \r\n" + 
			"   and	 \"RES_TEST\".\"TYPE\" =:type", nativeQuery = true)
	List<Offer> findBy(@Param("cat") String category, @Param("type") String type);
	

}
