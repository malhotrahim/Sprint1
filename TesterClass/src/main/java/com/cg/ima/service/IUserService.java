package com.cg.ima.service;

import com.cg.ima.entity.User;

public interface IUserService {

	User login(User user);

	User logout();

	User addUser(User user);

	User editUser(User user);

	User removeUser(String userId);

}
