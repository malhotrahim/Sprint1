package com.cg.ima.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.ima.entity.Proposal;
import com.cg.ima.exception.InvalidProposalException;
import com.cg.ima.repository.IProposalRepository;

@Service
@Transactional
public class IProposalServiceImpl implements IProposalService {
	@Autowired
	private IProposalRepository pRepo;

	@Override
	public Proposal addProposal(Proposal proposal) {
		Proposal prop = pRepo.save(proposal);
		return prop;
	}

	@Override
	public Proposal editProposal(Proposal proposal) throws InvalidProposalException {
		int propId = proposal.getPropId();
		Optional<Proposal> opt = pRepo.findById(propId);
		if (!opt.isPresent()) {
			throw new InvalidProposalException("Proposal not found for id: " + propId);
		}
		Proposal prop = opt.get();
		prop.setAmount(prop.getAmount());
		return prop;
	}

	@Override
	public Proposal getProposal(int propId) throws InvalidProposalException {
		Optional<Proposal> opt = pRepo.findById(propId);
		if (!opt.isPresent()) {
			throw new InvalidProposalException("Proposal not found for id: " + propId);
		}
		Proposal prop = opt.get();
		return prop;
	}

	@Override
	public Proposal removeProposal(int propId) throws InvalidProposalException {
		Optional<Proposal> opt = pRepo.findById(propId);
		if (!opt.isPresent()) {
			throw new InvalidProposalException("Proposal not found for id: " + propId);
		}
		Proposal prop = opt.get();
		pRepo.delete(prop);
		return prop;
	}

	@Override
	public List<Proposal> getAllProposals() {
		List<Proposal> list = pRepo.findAll();
		return list;
	}

}
