package com.cg.ima.service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.ima.entity.Employee;
import com.cg.ima.entity.Proposal;
import com.cg.ima.entity.Requirement;
import com.cg.ima.entity.Resource;
import com.cg.ima.entity.User;
import com.cg.ima.exception.InvalidRequirementException;
import com.cg.ima.service.IRequirementService;
import com.cg.ima.service.IRequirementServiceImpl;

@ExtendWith({ SpringExtension.class })
@DataJpaTest
@Import(IRequirementServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IRequirementServiceTest {

	@Autowired
	private IRequirementService rService;
	@Autowired
	private EntityManager em;

	@Test
	void testAddRequirement() {
		User user = new User("Faleen", "12345");
		Employee emp = new Employee("Faleen", "Developer", "Srinagar", user);
		Resource res = new Resource("hello", "desc", "cat", LocalDate.now(), "type", 1000, emp);
		Proposal p = new Proposal("pg", 2000, LocalDate.now(), false, null, res);
		List<Proposal> prop = new ArrayList<>();

		Requirement req = new Requirement(true, LocalDate.now(), prop);
		Requirement requirement = rService.addRequirement(req);
		Assertions.assertEquals(requirement.getReqId(), req.getReqId());
	}

	@Test
	void testEditRequirement() {
		User user = new User("Faleen", "12345");
		Employee emp = new Employee("Faleen", "Developer", "Srinagar", user);
		Resource res = new Resource("hello", "desc", "cat", LocalDate.now(), "type", 1000, emp);
		Proposal p = new Proposal("pg", 2000, LocalDate.now(), false, null, res);
		List<Proposal> prop = new ArrayList<>();
		prop.add(p);

		Requirement req = new Requirement(true, LocalDate.now(), prop);
		em.persist(req);
		Requirement editreq = rService.editRequirement(req);
	}

	@Test
	void testGetRequirement() throws InvalidRequirementException {
		User user = new User("Faleen", "12345");
		Employee emp = new Employee("Faleen", "Developer", "Srinagar", user);
		Resource res = new Resource("hello", "desc", "cat", LocalDate.now(), "type", 1000, emp);
		Proposal p = new Proposal("pg", 2000, LocalDate.now(), false, null, res);
		List<Proposal> prop = new ArrayList<>();
		prop.add(p);

		Requirement req = new Requirement(true, LocalDate.now(), prop);
		em.persist(req);
		int requirementId = req.getReqId();
		Requirement reqFound = rService.getRequirement(requirementId);
		Assertions.assertEquals(reqFound.getReqId(), req.getReqId());
	}

	@Test
	void testRemoveRequirement() {
		User user = new User("Faleen", "12345");
		Employee emp = new Employee("Faleen", "Developer", "Srinagar", user);
		Resource res = new Resource("hello", "desc", "cat", LocalDate.now(), "type", 1000, emp);
		Proposal p = new Proposal("pg", 2000, LocalDate.now(), false, null, res);
		List<Proposal> prop = new ArrayList<>();
		prop.add(p);

		Requirement req = new Requirement(true, LocalDate.now(), prop);
		em.persist(req);
		Requirement requirement = rService.removeRequirement(req.getReqId());
		Assertions.assertEquals(req.getReqId(), req.getReqId());

	}

	@Test
	void testGetAllRequirements() {
//		User user= new User("Faleen", "12345");
//		Employee emp = new Employee("Faleen", "Developer", "Srinagar",user);
//		Resource res = new Resource("hello", "desc", "cat", LocalDate.now(), "type", 1000, emp);
//		Proposal p = new Proposal("pg", 2000, LocalDate.now(), false, null, res);
//			List<Proposal> prop = new ArrayList<>();
//		prop.add(p);
//		Requirement req = new Requirement ( true, LocalDate.now(), prop);
//			em.persist(req);
//			User user1= new User("Faleen2", "123456");
//			emp = new Employee("Faleen", "Developer", "Srinagar",user1);
//			 res = new Resource("hello", "desc", "cat", LocalDate.now(), "type", 1000, emp);
//		 p = new Proposal("pg", 2000, LocalDate.now(), false, null, res);
//			 List<Proposal> prop1 = new ArrayList<>();
//				prop.add(p);
//			Requirement req1 = new Requirement( true, LocalDate.now(), prop);
//			em.persist(req1);
		List<Requirement> reqList = rService.getAllRequirements();
		System.out.println(reqList);
		Assertions.assertEquals(reqList.size(), 2);

	}

	@Test
	void testGetAllRequirementsStringString() {
		User user = new User("bhagdk", "12345");
		Employee emp = new Employee("Vishal", "Developer", "Srinagar", user);
		Resource res = new Resource("hello", "desc", "category", LocalDate.now(), "type23", 1000, emp);
		Proposal p = new Proposal("pg", 2000, LocalDate.now(), false, null, res);
		List<Proposal> prop = new ArrayList<>();
		prop.add(p);

		Requirement req = new Requirement(true, LocalDate.now(), prop);
		em.persist(req);
		List<Requirement> requirementfound = rService.getAllRequirements("category", "type23");
		System.out.println(requirementfound);
		Assertions.assertEquals(requirementfound.size(), 3);

	}
}
