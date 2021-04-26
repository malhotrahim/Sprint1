package com.cg.ima.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ima.entity.Resource;

@Repository
public interface IResourceRepository extends JpaRepository<Resource, Integer> {

	@Query("from Resource where category=:category and type=:type")
	List<Resource> findByCategoryAndType(@Param("category") String category, @Param("type") String type);

	@Query("from Resource where empId=:empid")
	List<Resource> getByEmpId(@Param("empid") int empId);

}
