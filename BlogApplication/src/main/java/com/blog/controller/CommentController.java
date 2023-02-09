package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.model.Blog;
import com.blog.model.Comment;
import com.blog.service.CommentService;

@RestController
@RequestMapping("/masaiblog")
public class CommentController {

	@Autowired
	private CommentService commentService;
	@PostMapping("/user/{blogId}")
	public ResponseEntity<Blog> postCommentHandler(@RequestBody Comment comment, @PathVariable Integer blogId){
		
		Blog blog = commentService.writeComment(comment, blogId);
		
		return new ResponseEntity<Blog>(blog, HttpStatus.CREATED);
		
		
		
	}
}
