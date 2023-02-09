package com.blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blog.exceptions.BlogException;
import com.blog.model.Blog;
import com.blog.model.Comment;
import com.blog.model.USER;
import com.blog.repository.BlogRepository;
import com.blog.repository.CommentRepository;
import com.blog.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Blog writeComment(Comment comment, Integer blogid) throws BlogException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		Optional<USER> opt= userRepository.findByEmail(userName);
		
		USER user =  opt.get();
		
		Optional<Blog> optBlog = blogRepository.findById(blogid);
		if(optBlog.isPresent()) {
			
			Blog blog = optBlog.get();
			USER blogUser = blog.getUser();
			
			if(user.getEmail().equals(blogUser.getEmail())) {
				throw new BlogException("Yout can't comment in your own blog!");
			}
			
			blog.getComments().add(comment);
			comment.setBlog(blog);
			
			commentRepository.save(comment);
			return blog;
		}
		 throw new BlogException("Invallid Blog id "+blogid);
	}

}
