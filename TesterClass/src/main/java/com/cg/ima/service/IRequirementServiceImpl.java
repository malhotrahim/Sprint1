package com.cg.ima.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ima.entity.Requirement;
import com.cg.ima.exception.InvalidRequirementException;
import com.cg.ima.repository.IRequirementRepository;

@Service
@Transactional
public class IRequirementServiceImpl implements IRequirementService {

	@Autowired
	private IRequirementRepository rDao;

	@Override
	public Requirement addRequirement(Requirement requirement) {
		Requirement req = rDao.save(requirement);
		return req;
	}

	@Override
	public Requirement editRequirement(Requirement requirement) {
		Optional<Requirement> opt = rDao.findById(requirement.getReqId());
		if(!opt.isPresent()) {
			throw new InvalidRequirementException("Requirement not found for id= "+ requirement.getReqId());
		}
		Requirement req = opt.get();
		req.setFulfilled(requirement.getisFulfilled());
		
		return req;
	}

	@Override
	public Requirement getRequirement(int requirementId) throws InvalidRequirementException {
		Optional<Requirement> opt = rDao.findById(requirementId);
		if (!opt.isPresent()) {
			throw new InvalidRequirementException("Requirement not found for id ="+ requirementId);
			}
		Requirement requirement = opt.get();
		return requirement;

	}

	@Override
	public Requirement removeRequirement(int requirementId) {
		Requirement requirement = getRequirement(requirementId);
		rDao.deleteById(requirementId);
		return requirement;
	}

	@Override
	public List<Requirement> getAllRequirements() {
		List<Requirement> requirementList = rDao.findAll();
		return requirementList;

	}

	@Override
	public List<Requirement> getAllRequirements(String category, String type) {
		List<Requirement> requirementList = rDao.findBy(category, type);
		if(requirementList.isEmpty()) {
			throw new InvalidRequirementException("No requirement found for category ="+ category + "and type = "+type);
		}
		return requirementList;
	}

	


	

}
