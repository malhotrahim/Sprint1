package com.cg.ima.service;

import java.util.List;

import com.cg.ima.entity.Resource;
import com.cg.ima.exception.InvalidEmployeeException;

public interface IResourceService {
	
List<Resource> getAllResources(String category, String type);
	
	List<Resource> getAllResources(int empId) throws InvalidEmployeeException;

}
