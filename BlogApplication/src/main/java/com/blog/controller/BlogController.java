package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.model.Blog;
import com.blog.model.USER;
import com.blog.service.BlogService;

@RestController
@RequestMapping("/masaiblog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@PostMapping("/user/blog")
	public ResponseEntity<USER> postBlogHandler(@RequestBody Blog blog){
		
		USER user = blogService.createBlog(blog);
		
		return new ResponseEntity<USER>(user, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/blog/{category}")
	public ResponseEntity<List<Blog>> getBlogsByCategory(@PathVariable String category){
		
		List<Blog> blogs = blogService.getBlogsByCategory(category);
		
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}
}
