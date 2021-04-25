package com.cg.ima.service;

import org.junit.jupiter.api.Assertions;
import  org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.ima.entity.Employee;
import com.cg.ima.entity.Offer;
import com.cg.ima.entity.Proposal;
import com.cg.ima.entity.Resource;
import com.cg.ima.entity.User;
import com.cg.ima.exception.InvalidOfferException;
@ExtendWith({SpringExtension.class})
@DataJpaTest
@Import(IOfferServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IOfferServiceTest {
	@Autowired
	private IOfferService oService;
	@Autowired
	private EntityManager em;
	@Test
	void testAddOffer() {
		User user= new User("himanshu", "123456");
		Employee emp = new Employee("Himanshu", "Tester", "Faridabad",user);
		Resource res = new Resource("hiiii", "desc", "cat", LocalDate.now(), "type", 1000, emp);
		Proposal p = new Proposal("pg", 2000, LocalDate.now(), false, null, res);
			List<Proposal> prop = new ArrayList<>();
			prop.add(p);
			
			Offer off = new Offer( true, LocalDate.now(), prop);
			Offer offer = oService.addOffer(off);
			Assertions.assertEquals(offer.getOfferId(), off.getOfferId());
	}

	@Test
	void testEditOffer() {
		User user= new User("himanshu", "123456");
		Employee emp = new Employee("Himanshu", "Tester", "Faridabad",user);
		Resource res = new Resource("hiiii", "desc", "cat", LocalDate.now(), "type", 1000, emp);
		Proposal p = new Proposal("pg", 2000, LocalDate.now(), false, null, res);
			List<Proposal> prop = new ArrayList<>();
			prop.add(p);
			
			Offer off = new Offer( true, LocalDate.now(), prop);
			em.persist(off);
			Offer editOff = oService.editOffer(off);
	}

	@Test
	void testGetOffer() throws InvalidOfferException {
		User user= new User("himanshu", "123456");
		Employee emp = new Employee("Himanshu", "Tester", "Faridabad",user);
		Resource res = new Resource("hiiii", "desc", "cat", LocalDate.now(), "type", 1000, emp);
		Proposal p = new Proposal("pg", 2000, LocalDate.now(), false, null, res);
			List<Proposal> prop = new ArrayList<>();
			prop.add(p);
			
			Offer off = new Offer( true, LocalDate.now(), prop);
			em.persist(off);
			int offerId=off.getOfferId();
			Offer offFound = oService.getOffer(offerId);
			Assertions.assertEquals(offFound.getOfferId(), off.getOfferId());
	}

	@Test
	void testRemoveOffer() {
		User user= new User("himanshu", "123456");
		Employee emp = new Employee("Himanshu", "Tester", "Faridabad",user);
		Resource res = new Resource("hiiii", "desc", "cat", LocalDate.now(), "type", 1000, emp);
		Proposal p = new Proposal("pg", 2000, LocalDate.now(), false, null, res);
			List<Proposal> prop = new ArrayList<>();
			prop.add(p);
			
			Offer off = new Offer( true, LocalDate.now(), prop);
			em.persist(off);
			Offer offer = oService.removeOffer(off.getOfferId());
			Assertions.assertEquals(offer.getOfferId(), off.getOfferId());
		
	}

	@Test
	void testGetAllOffers() {
//		User user= new User("himanshu", "123456");
//		Employee emp = new Employee("Himanshu", "Tester", "Faridabad",user);
//		Resource res = new Resource("hiiii", "desc", "cat", LocalDate.now(), "type", 1000, emp);
//		Proposal p = new Proposal("pg", 2000, LocalDate.now(), false, null, res);
//			List<Proposal> prop = new ArrayList<>();
//			prop.add(p);
//			Offer off = new Offer( true, LocalDate.now(), prop);
//			em.persist(off);
//			User user1= new User("himanshu2", "123456");
//			emp = new Employee("Himanshu", "Tester", "Faridabad",user1);
//			 res = new Resource("hiiii", "desc", "cat", LocalDate.now(), "type", 1000, emp);
//			 p = new Proposal("pg", 2000, LocalDate.now(), false, null, res);
//			 List<Proposal> prop1 = new ArrayList<>();
//				prop.add(p);
//			Offer off1 = new Offer( true, LocalDate.now(), prop);
//			em.persist(off1);
			List<Offer> offList = oService.getAllOffers();
			System.out.println(offList);
			Assertions.assertEquals(offList.size(), 5);
		
	}

	@Test
	void testGetAllOffersStringString() {
		User user= new User("himanshu", "123456");
		Employee emp = new Employee("Himanshu", "Tester", "Faridabad",user);
		Resource res = new Resource("hiiii", "desc", "category", LocalDate.now(), "type23", 1000, emp);
		Proposal p = new Proposal("pg", 2000, LocalDate.now(), false, null, res);
			List<Proposal> prop = new ArrayList<>();
			prop.add(p);
			
			Offer off = new Offer( true, LocalDate.now(), prop);
			em.persist(off);
			List<Offer> offerfound = oService.getAllOffers("category", "type23");
			System.out.println(offerfound);
			Assertions.assertEquals(offerfound.size(), 1);
		
	}

}
