package com.cg.ima.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ima.entity.Employee;
import com.cg.ima.entity.Offer;
import com.cg.ima.entity.Proposal;
import com.cg.ima.entity.Requirement;
import com.cg.ima.exception.InvalidEmployeeException;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
	
	
	@Query(value = "select * FROM Offer WHERE is_Available = 1", nativeQuery = true)
	Offer statusUpdate(Offer offer);
	
	@Query(value ="select * FROM Requirement WHERE is_Fulfilled = 1",nativeQuery = true )
	Requirement statusUpdate(Requirement req);

	@Query(value="select * FROM Proposal WHERE is_Accepted = 1",nativeQuery = true)
	Proposal statusUpdate(Proposal prop);

	@Query(value="select Offer.offer_Id as offerId, Offer.available_Upto as availableUpto, Offer.is_Available as isAvailable, Proposal.emp_Id as empId from Proposal Proposal, Offer OFFER where OFFER.offer_Id=Proposal.offer_Id and Proposal.emp_Id=:empId",nativeQuery = true) 
	List<Offer> fetchAllOffers(@Param("empId") int empId) throws InvalidEmployeeException;
	
	@Query(value="select Requirement.req_Id as reqId, Requirement.fulfilled_On as fulfilledOn, Requirement.is_Fulfilled as isFulfilled, Proposal.emp_Id as empId from Proposal Proposal, Requirement Requirement where Requirement.req_Id=Proposal.PROPOSALS_REQ_ID and Proposal.emp_Id =:empId", nativeQuery = true)
	List<Requirement> fetchAllRequirements(@Param("empId") int empId) throws InvalidEmployeeException;
}
