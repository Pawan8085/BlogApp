package com.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blog.exceptions.BlogException;
import com.blog.exceptions.CommentException;
import com.blog.exceptions.UserException;
import com.blog.model.Blog;
import com.blog.model.Comment;
import com.blog.model.USER;
import com.blog.repository.BlogRepository;
import com.blog.repository.CommentRepository;
import com.blog.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	@Override
	public USER userRegister(USER user) {
		return userRepository.save(user);
	}

	@Override
	public USER userLogin() throws UserException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		Optional<USER> opt = userRepository.findByEmail(userName);
		if(opt.isPresent()) {
			USER user =opt.get();
			
			return user;
		}
		else throw new UserException("Wrong email or password!");
	}

	@Override
	public List<Blog> getAllBlogs() throws BlogException {
		
		List<Blog> blogs = blogRepository.findAll();
		
		if(blogs.isEmpty()) {
			throw new BlogException("Blog not found!");
		}
		return blogs;
	}

	@Override
	public USER deletBlog(Integer blogid) throws BlogException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		Optional<USER> opt= userRepository.findByEmail(userName);
		
		USER user =  opt.get();
		
		Optional<Blog> optBlog = blogRepository.findById(blogid);
		if(optBlog.isPresent()) {
			
			Blog blog = optBlog.get();
			
			USER blogUser = blog.getUser();
			
			if(user.getEmail().equals(blogUser.getEmail())) {
				blogRepository.delete(blog);
				
				return user;
			}
			throw new BlogException("Invalid blog id "+blogid);
		}
		throw new BlogException("Invalid blog id "+blogid);
	}

	@Override
	public USER deleteBlogComment(Integer commentId) throws CommentException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		Optional<USER> opt= userRepository.findByEmail(userName);
		
		USER user =  opt.get();
		
		Optional<Comment> optComment = commentRepository.findById(commentId);
		
		if(optComment.isPresent()) {
			
			Comment comment = optComment.get();
			
			USER blogUser = comment.getBlog().getUser();
			
			if(user.getEmail().equals(blogUser.getEmail())) {
				
				commentRepository.delete(comment);
				
				return user;
			}
			throw new CommentException("Invalid comment id "+commentId);
			
		}
		throw new CommentException("Invalid comment id "+commentId);
	}

}
