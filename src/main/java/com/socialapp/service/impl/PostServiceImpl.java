package com.socialapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialapp.entity.Post;
import com.socialapp.entity.User;
import com.socialapp.repo.PostRepository;
import com.socialapp.repo.UserRepository;
import com.socialapp.service.PostService;
import com.socialapp.service.UserService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

	@Override
	public Post createNewPost(Post post, Integer userId) {

		User user = userService.findByUserId(userId);

		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
//		newPost.setCreatedAt(new LocalDate);
		newPost.setVideo(post.getVideo());
		newPost.setUser(user);
		return newPost;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findByUserId(userId);
		if (post.getUser().getId() != user.getId()) {
			throw new Exception("you can't delete another user post");
		}
		postRepository.delete(post);
		return "post deleted successfully";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {

		return postRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {
		Optional<Post> opt = postRepository.findById(postId);
		if (opt.isEmpty()) {
			throw new Exception("Post not found with this id " + postId);
		}
		return opt.get();
	}

	@Override
	public List<Post> findAllPost() {
		return postRepository.findAll();
	}

	@Override
	public Post savedPost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findByUserId(userId);
		if (user.getSavedPosts().contains(post)) {
			user.getSavedPosts().remove(post);
		} else
			user.getSavedPosts().add(post);
		userRepository.save(user);
		return post;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {
		Post post = findPostById(postId);
		User user = userService.findByUserId(userId);
		post.getLiked().add(user);

		if (post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		} else {
			post.getLiked().add(user);
		}
		return postRepository.save(post);
	}

}
