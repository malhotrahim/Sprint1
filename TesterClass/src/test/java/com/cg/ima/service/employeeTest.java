package com.cg.ima.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
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
import com.cg.ima.entity.Requirement;
import com.cg.ima.entity.Resource;
import com.cg.ima.entity.User;
import com.cg.ima.exception.InvalidEmployeeException;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(IEmployeeServiceImpl.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class employeeTest {

	@Autowired
	private IEmployeeService eService;
	
	@Autowired
	private EntityManager em;
	
	@Test
	void testAddEmployee() {
		User user = new User("Anmol","Anmol2099");
		Employee emp = new Employee("Anmol","HR","Bangalore", user);
		em.persist(emp);
		Employee emp1 = eService.addEmployee(emp);
		Assertions.assertEquals(emp1.getEmpName(), emp.getEmpName());
	}

	@Test
	void testEditEmployee() {
		User user = new User("Anmol","Anmol2099");
		
		Employee emp1 = new Employee("Anmol","Manager","Bangalore", user);
		em.persist(emp1);
		Employee emp = new Employee("Anmol","HR","Bangalore", user);
		emp.setEmpId(emp1.getEmpId());
		Employee empEdit = eService.editEmployee(emp);
		System.out.println(empEdit);
		Assertions.assertEquals(empEdit.getDeptName(),"HR");
	}

	@Test
	void testGetEmployee() throws InvalidEmployeeException {
		User user = new User("Anmol","Anmol2099");
		Employee emp = new Employee("Anmol","HR","Bangalore", user);
		em.persist(emp);
		Integer id = emp.getEmpId();
		Employee empFound = eService.getEmployee(id);
		Assertions.assertEquals(empFound.getEmpId(), emp.getEmpId());
	}

	@Test
	void testUpdateIsAvailable() {
		Offer of = new Offer(false,null,null);
		em.persist(of);
		Assertions.assertEquals(of.getIsAvailable(), false);
	}

	@Test
	void testUpdateIsFulfilled() {

		Requirement	 req = new Requirement(false,null,null);
		em.persist(req);
		Assertions.assertEquals(req.isFulfilled(), false);
	}

	@Test
	void testUpdateIsAccepted() {
		Resource res = new Resource("anm ", "skdhb", "Service", null, "kjdv", 3400, null);
		Proposal pro = new Proposal("ABC",25000,null,false,null,res);
		em.persist(pro);
		Assertions.assertEquals(pro.isAccepted(), false);
	}

	@Test
	void testGetAllOffers() throws InvalidEmployeeException {
		User user = new User("Anmol","Anmol2099");
		Employee emp = new Employee("Anmol","HR","Bangalore", user);
		em.persist(emp);
		List<Offer> list = eService.getAllOffers(emp.getEmpId());
		Assertions.assertEquals(list.size(), 0);
	}

	@Test
	void testGetAllRequirements() throws InvalidEmployeeException {
		User user = new User("Anmol","Anmol2099");
		Employee emp = new Employee("Anmol","HR","Bangalore", user);
		em.persist(emp);
		List<Requirement> list = eService.getAllRequirements(emp.getEmpId());
		Assertions.assertEquals(list.size(), 0);
	}

}
