package com.cg.ima.service;

import java.util.List;

import com.cg.ima.entity.Proposal;
import com.cg.ima.exception.InvalidProposalException;

public interface IProposalService {

	Proposal addProposal(Proposal prop);

	Proposal editProposal(Proposal prop) throws InvalidProposalException;

	Proposal getProposal(int propId) throws InvalidProposalException;

	Proposal removeProposal(int propId) throws InvalidProposalException;

	List<Proposal> getAllProposals();
}
