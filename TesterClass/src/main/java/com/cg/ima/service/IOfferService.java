package com.cg.ima.service;

import java.util.List;

import com.cg.ima.entity.Offer;

import com.cg.ima.exception.InvalidOfferException;

public interface IOfferService {
	/**
	 * @author Himanshu
	 * @param Offer offer
	 * @return Offer
	 * This is used to add offers
	 */
Offer addOffer(Offer offer);
/**
 * @author Himanshu
 * @param Offer offer
 * @return Offer
 * This is used to edit offers
 */
	
	Offer editOffer(Offer offer) ;
	/**
	 * @author Himanshu
	 * @param int offerId
	 * @return Offer
	 * @throws InvalidOfferException
	 * This is used to get offers with id
	 */
	
	Offer getOffer(int offerId) throws InvalidOfferException; 
	/**
	 * @author Himanshu
	 * @param int offerId
	 * @return Offer
	 * @throws InvalidOfferException
	 * This is used to remove offers with id
	 */
	
	Offer removeOffer(int offerId) throws InvalidOfferException;
	
	
	/**
	 * @author Himanshu
	 * @return List
	 * This is used to get all offers
	 */
	List<Offer> getAllOffers();
	/**
	 * @author Himanshu
	 * @return List
	 * This is used to get all offers with category and type
	 */
	
	List<Offer> getAllOffers(String category, String type);
}
