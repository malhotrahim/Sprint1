package com.cg.ima.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ima.entity.Offer;
import com.cg.ima.exception.InvalidOfferException;
import com.cg.ima.repository.IOfficeRepositary;

@Service
@Transactional

public class IOfferServiceImpl implements IOfferService {
	private Logger logger = LoggerFactory.getLogger(IOfferServiceImpl.class);
	@Autowired
	private IOfficeRepositary edao;

	@Override
	public Offer addOffer(Offer offer) {
		Offer off = edao.save(offer);
		logger.info("Offer added : " + offer);
		return off;
	}

	@Override
	public Offer editOffer(Offer offer) {
		Optional<Offer> opt = edao.findById(offer.getOfferId());
		if (!opt.isPresent()) {
			logger.info("Offer : " + offer + "not edited  ");
			throw new InvalidOfferException("Offer not found for id = " + offer.getOfferId());

		}
		Offer off = opt.get();
		off.setIsAvailable(offer.getIsAvailable());
		logger.info("Offer edited : " + offer);

		return off;
	}

	@Override
	public Offer getOffer(int offerId) throws InvalidOfferException {
		Optional<Offer> opt = edao.findById(offerId);
		if (!opt.isPresent()) {
			logger.info("offer not found with id = " + offerId);
			throw new InvalidOfferException("Offer not found for id = " + offerId);

		}
		Offer offer = opt.get();
		logger.info("offer found with id = " + offerId);
		return offer;

	}

	@Override
	public Offer removeOffer(int offerId) {
		Offer offer = getOffer(offerId);

		edao.deleteById(offerId);
		logger.info("offer deleted with id = " + offerId);
		return offer;
	}

	@Override
	public List<Offer> getAllOffers() {
		List<Offer> offerList = edao.findAll();
		logger.info("got all offers");
		return offerList;

	}

	@Override
	public List<Offer> getAllOffers(String category, String type) {
		List<Offer> offerList = edao.findBy(category, type);
		if (offerList.isEmpty()) {
			logger.info("No offers found with category = " + category + " and type = " + type);
			throw new InvalidOfferException("No offer found for category = " + category + " and type = " + type);
		}
		logger.info("got all offers with category = " + category + " and type = " + type);
		return offerList;
	}

}
