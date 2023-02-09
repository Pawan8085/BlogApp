package com.blog.service;

import java.util.List;

import com.blog.exceptions.BlogException;
import com.blog.exceptions.CommentException;
import com.blog.exceptions.UserException;
import com.blog.model.Blog;
import com.blog.model.USER;

public interface UserService {
	public USER userRegister(USER user);
	
	public USER userLogin()throws UserException;
	
	public List<Blog> getAllBlogs()throws BlogException;
	
	public USER deletBlog(Integer blogid)throws BlogException;
	
	public USER deleteBlogComment(Integer commentId)throws CommentException;
	
	

}
