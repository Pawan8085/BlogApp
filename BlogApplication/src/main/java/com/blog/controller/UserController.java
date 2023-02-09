package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exceptions.UserException;
import com.blog.model.Blog;
import com.blog.model.USER;
import com.blog.service.UserService;

@RestController
@RequestMapping("/masaiblog")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/user/register")
	public ResponseEntity<USER> registerUserHandler(@RequestBody USER user){
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		USER savedUser = userService.userRegister(user);
		
		return new ResponseEntity<USER>(savedUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<USER> loginUserHandler() throws UserException{
		
		USER user = userService.userLogin();
		
		return new ResponseEntity<USER>(user, HttpStatus.OK);
	}
	
	@GetMapping("/user/blogs")
	public ResponseEntity<List<Blog>> getAllBlogsHandler(){
		List<Blog> blogs = userService.getAllBlogs();
		
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/blog/{blogId}")
	public ResponseEntity<USER> deleteBlogHandler(@PathVariable Integer blogId){
		
		USER user = userService.deletBlog(blogId);
		
		return new ResponseEntity<USER>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/blog/comment/{commentId}")
	public ResponseEntity<USER> deleteBlogCommentHandler(@PathVariable Integer commentId){
		
		USER user = userService.deleteBlogComment(commentId);
		
		return new ResponseEntity<USER>(user, HttpStatus.OK);	
	}
}
