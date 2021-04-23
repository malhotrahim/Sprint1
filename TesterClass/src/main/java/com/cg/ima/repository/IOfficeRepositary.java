package com.cg.ima.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ima.entity.Offer;

@Repository
public interface IOfficeRepositary extends JpaRepository<Offer, Integer>{
	@Query(value ="select * from Offer o where o.resource_res_id=(select res_id from res_test r where r.Category = :cat and r.Type=:type)", nativeQuery = true)
	List<Offer> findBy(@Param("cat") String category, @Param("type") String type);
	

}
