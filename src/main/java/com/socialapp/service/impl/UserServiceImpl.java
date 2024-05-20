package com.socialapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialapp.entity.User;
import com.socialapp.repo.UserRepository;
import com.socialapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		try {
			userRepository.save(user);
		} catch (Exception e) {
			throw new RuntimeException("Add user failed ");
		}
		return user;
	}

	@Override
	public User findByUserId(Integer userId) {
		try {
			User user = userRepository.findById(userId).get();
			return user;
		} catch (Exception e) {
			throw new RuntimeException("User not exist with userid" + userId);
		}

	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		try {
			Optional<User> user = userRepository.findByEmail(email);
			return user;
		} catch (Exception e) {
			throw new RuntimeException("User not exist with this email");
		}
	}

	@Override
	public User followUser(Integer userId1, Integer userId2) {
		try {
			User user1 = findByUserId(userId1);
			User user2 = findByUserId(userId2);
			user2.getFollowers().add(user1.getId());
			user1.getFollowings().add(user2.getId());
			userRepository.save(user1);
			userRepository.save(user2);
			return user1;
		} catch (Exception e) {
			throw new RuntimeException("follow user failed ");
		}
	}

	@Override
	public User updateUser(User user, Integer userID) {
		try {
			User findUser = userRepository.findById(userID).get();
			findUser.setFirstName(user.getFirstName());
			findUser.setLastName(user.getLastName());
			findUser.setEmail(user.getEmail());
			findUser.setGender(user.getGender());
			findUser.setPassword(user.getPassword());
			findUser.setFollowers(user.getFollowers());
			findUser.setFollowings(user.getFollowings());

			 userRepository.save(findUser);
			 return user;
		} catch (Exception e) {
			throw new RuntimeException("update user failed ");
		}

	}

	@Override
	public List<User> searchUser(String query) {
		try {
			return userRepository.searchUser(query);
		} catch (Exception e) {
			throw new RuntimeException("search user failed ");
		}

	}

	@Override
	public List<User> fetchAllUsers() {
		try {
			List<User> users = userRepository.findAll();
			return users;
		} catch (Exception e) {
			throw new RuntimeException("fetch user failed ");
		}
	}

}
