package com.cg.ima.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	private Logger logger = LoggerFactory.getLogger(OfferController.class);
	@Autowired
	private IOfferService oServive;

	@RequestMapping("/hello")
	public String greet() {
		return "Hello from student controller";
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/add")
	public ResponseEntity<Offer> addOffer(@RequestBody Offer off) {

		Offer addOffer = oServive.addOffer(off);
		if (addOffer == null) {
			logger.debug("Offer cannot be added");
		}

		return new ResponseEntity<>(addOffer, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Offer> removeOffer(@PathVariable("id") Integer id) {
		Offer delOff = oServive.removeOffer(id);
		if (delOff == null) {
			logger.debug("Offer cannot be deleted");
		}
		return new ResponseEntity<>(delOff, HttpStatus.OK);

	}

	@GetMapping("/getall")
	public ResponseEntity<List<Offer>> getAllOffer() {
		List<Offer> offerList = oServive.getAllOffers();
		if (offerList.isEmpty()) {
			logger.debug("No Offer available");
		}
		return new ResponseEntity<>(offerList, HttpStatus.OK);
	}

	@GetMapping("/getby/{cat}/{type}")
	public ResponseEntity<List<Offer>> getAllOffers(@PathVariable("cat") String category,
			@PathVariable("type") String type) {
		List<Offer> offerList = oServive.getAllOffers(category, type);
		if (offerList.isEmpty()) {
			logger.debug("No Offer available");
		}
		return new ResponseEntity<>(offerList, HttpStatus.OK);

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Offer> getOffer(@PathVariable("id") int offerId) throws InvalidOfferException {
		Offer offer = oServive.getOffer(offerId);
		if (offer == null) {
			logger.debug("No Offer found");
		}
		return new ResponseEntity<>(offer, HttpStatus.OK);
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<Offer> editOffer(@PathVariable("id") int id, @RequestBody boolean bool)
			throws InvalidOfferException {
		Offer offer = oServive.getOffer(id);
		offer.setIsAvailable(bool);
		Offer off = oServive.editOffer(offer);
		if (off == null) {
			logger.debug("Offer not edited");
		}
		return new ResponseEntity<>(off, HttpStatus.OK);

	}
}
