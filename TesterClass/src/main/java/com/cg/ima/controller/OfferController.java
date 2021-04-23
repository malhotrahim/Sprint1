package com.cg.ima.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ima.entity.Employee;
import com.cg.ima.entity.Offer;
import com.cg.ima.entity.Proposal;
import com.cg.ima.entity.Resource;
import com.cg.ima.entity.User;
import com.cg.ima.exception.InvalidOfferException;
import com.cg.ima.service.IOfferService;

@RestController
@RequestMapping("/offer")
@Validated
public class OfferController {
	@Autowired
	private IOfferService oServive;
	
	@RequestMapping("/hello")
	public String greet() {
		return "Hello from student controller";
		}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	 @PostMapping("/add")
	public ResponseEntity<Offer> addOffer() {
		User user= new User("himanshu", "123456");
		Employee emp = new Employee("Himanshu", "Tester", "Faridabad",user);
		Resource res = new Resource("hiiii", "desc", "cat", LocalDate.now(), "type", 1000, emp);
		Proposal p = new Proposal("proposal", 2000, LocalDate.now(), false, null, res);
			List<Proposal> prop = new ArrayList<>();
			prop.add(p);
			
			Offer off = new Offer( true, LocalDate.now(), prop);
			System.out.println("req data = " +off);
		Offer offer =oServive.addOffer(off);
		System.out.println("req data = " +offer);
		
		return new ResponseEntity<Offer>(off, HttpStatus.OK);
	}
	 @GetMapping("/delete/{id}")
	 public ResponseEntity<Offer> removeOffer (@PathVariable("id") Integer id)  {
		Offer off;
		try {
			off = oServive.removeOffer(id);
			return new ResponseEntity<Offer>(off, HttpStatus.OK);
		} catch (InvalidOfferException e) {
			
		}
		return null;
			
		
	 }
	 	@GetMapping("/getall")
	 	public ResponseEntity<List<Offer>> getAllOffer(){
	 		List<Offer> offerList = oServive.getAllOffers();
	 		return new ResponseEntity<List<Offer>>(offerList, HttpStatus.OK);
	 	}
	 	@GetMapping("/getby/{cat}/{type}")
	 	public ResponseEntity<List<Offer>> getAllOffers(@PathVariable("cat")String category, @PathVariable("type")String type){
	 		List<Offer> offerList = oServive.getAllOffers(category, type);
			return new ResponseEntity<List<Offer>>(offerList, HttpStatus.OK);
	 		
	 	}
	 	@GetMapping("/get/{id}")
	 	public ResponseEntity<Offer> getOffer(@PathVariable("id")int offerId) throws InvalidOfferException {
	 		Offer offer = oServive.getOffer(offerId);
	 		return new ResponseEntity<Offer>(offer, HttpStatus.OK);
	 	}
	 	@PostMapping("/edit/{id}")
	 	public ResponseEntity<Offer> editOffer(@PathVariable("id")int id, @RequestBody boolean bool) throws InvalidOfferException {
	 		Offer offer = oServive.getOffer(id);
	 		offer.setIsAvailable(bool);
	 		Offer off=oServive.editOffer(offer);
	 		return new ResponseEntity<Offer>(off, HttpStatus.OK);
	 		
	 		
	 	}
}
