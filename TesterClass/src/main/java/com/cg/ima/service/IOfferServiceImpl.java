package com.cg.ima.service;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ima.entity.Offer;
import com.cg.ima.exception.InvalidOfferException;
import com.cg.ima.repository.IOfficeRepositary;
@Service
@Transactional

public class IOfferServiceImpl implements IOfferService {
	@Autowired
	private IOfficeRepositary edao;
	
	
	@Override
	public Offer addOffer(Offer offer) {
		Offer off = edao.save(offer);
		return off;
	}

	@Override
	public Offer editOffer(Offer offer)  {
		Optional<Offer> opt=edao.findById(offer.getOfferId());
		if(!opt.isPresent()) {
			throw new InvalidOfferException("Offer not found for id = "+ offer.getOfferId());
			
			
		}
		Offer off = opt.get();
		off.setIsAvailable(offer.getIsAvailable());
		
		return off;
	}

	@Override
	public Offer getOffer(int offerId) throws InvalidOfferException {
		Optional<Offer> opt= edao.findById(offerId);
		if(!opt.isPresent()) {
			throw new InvalidOfferException("Offer not found for id = "+ offerId);
			
			
		}
		Offer offer= opt.get();
		return offer;

	}

	@Override
	public Offer removeOffer(int offerId) {
		Offer offer = getOffer(offerId);
	
		edao.deleteById(offerId);
		return offer;
	}

	@Override
	public List<Offer> getAllOffers() {
		List<Offer> offerList = edao.findAll();
		return offerList;
//		edao.deleteAll();
//		return null;

	}

	@Override
	public List<Offer> getAllOffers(String category, String type) {
		List<Offer> offerList = edao.findBy(category,type);
		if(offerList.isEmpty()) {
			throw new InvalidOfferException("No offer found for category = "+ category + " and type = "+ type);
		}
		return offerList;
	}
	

}
