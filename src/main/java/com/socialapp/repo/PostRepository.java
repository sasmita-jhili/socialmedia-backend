package com.socialapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.socialapp.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	@Query("select p from Post where p.user.id=userId")
	List<Post> findPostByUserId(Integer userId);
}
