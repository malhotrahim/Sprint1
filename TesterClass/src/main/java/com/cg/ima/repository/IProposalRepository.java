package com.cg.ima.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ima.entity.Proposal;
import com.cg.ima.exception.InvalidProposalException;

@Repository
public interface IProposalRepository extends JpaRepository<Proposal, Integer> {

}
