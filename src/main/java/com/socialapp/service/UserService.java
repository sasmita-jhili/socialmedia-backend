package com.socialapp.service;

import java.util.List;

import com.socialapp.entity.User;

public interface UserService {
	User registerUser(User user);

	User findByUserId(Integer userId);

	User findUserByEmail(String email);

	User followUser(Integer userId1, Integer userId2);

	User updateUser(User user, Integer userID);

	List<User> searchUser(String query);

	List<User> fetchAllUsers();

	void DeleteUser(Integer id);

}
