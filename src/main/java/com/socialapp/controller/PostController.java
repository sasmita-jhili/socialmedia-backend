package com.socialapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialapp.entity.Post;
import com.socialapp.response.ApiResponse;
import com.socialapp.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	PostService postService;

	@PostMapping("/user/{userId}")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userId) {
		Post createdPost = postService.createNewPost(post, userId);
		return ResponseEntity.ok(createdPost);
	}

	@DeleteMapping("/{postId}/{userId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId)
			throws Exception {
		String message = postService.deletePost(postId, userId);
		ApiResponse response = new ApiResponse(message, true);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/get/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {

		Post post = postService.findPostById(postId);

		return ResponseEntity.ok(post);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Post>> findUserPost(@PathVariable Integer userId) throws Exception {

		List<Post> posts = postService.findPostByUserId(userId);

		return ResponseEntity.ok(posts);
	}

	@GetMapping("/getall")
	public ResponseEntity<List<Post>> findAllPost() {

		List<Post> posts = postService.findAllPost();

		return ResponseEntity.ok(posts);
	}

	@PutMapping("/save/{postId}/user/{userId}")
	public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId, @PathVariable Integer userId)
			throws Exception {

		Post post = postService.savedPost(postId, userId);

		return ResponseEntity.ok(post);
	}
	@PutMapping("/like/{postId}/user/{userId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @PathVariable Integer userId)
			throws Exception {

		Post post = postService.likePost(postId, userId);

		return ResponseEntity.ok(post);
	}

}
