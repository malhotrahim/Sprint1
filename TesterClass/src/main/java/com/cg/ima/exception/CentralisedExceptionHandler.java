package com.cg.ima.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralisedExceptionHandler {
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(InvalidRequirementException.class)
	public String handlesInvalidRequirement(InvalidRequirementException e) {
		return e.getMessage();
	}

	@ExceptionHandler(InvalidRequirementException.class)
	public String handlesInvalidRequirement(InvalidOfferException e) {
		return e.getMessage();
	}

	@ExceptionHandler(InvalidRequirementException.class)
	public String handlesInvalidRequirement(InvalidEmployeeException e) {
		return e.getMessage();
	}

	@ExceptionHandler(InvalidRequirementException.class)
	public String handlesInvalidRequirement(InvalidProposalException e) {
		return e.getMessage();
	}
}
