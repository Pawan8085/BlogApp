package com.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blog.exceptions.CategoryException;
import com.blog.model.Blog;
import com.blog.model.USER;
import com.blog.repository.BlogRepository;
import com.blog.repository.UserRepository;
@Service
public class BlogServiceImpl implements BlogService{
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	

	@Override
	public USER createBlog(Blog blog) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		Optional<USER> opt= userRepository.findByEmail(userName);
		
		USER user =  opt.get();
		
		user.getBlogs().add(blog);
		blog.setUser(user);
		
		blogRepository.save(blog);
		
		return user;
	}

	@Override
	public List<Blog> getBlogsByCategory(String category) throws CategoryException {
		
		
		List<Blog> blogs = blogRepository.findByCategory(category);
		
		if(blogs.isEmpty()) {
			throw new CategoryException("Invalid category "+category);
		}
		return blogs;
	}

}
