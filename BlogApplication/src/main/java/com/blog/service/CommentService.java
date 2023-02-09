package com.blog.service;

import com.blog.exceptions.BlogException;
import com.blog.model.Blog;
import com.blog.model.Comment;

public interface CommentService {

	public Blog writeComment(Comment comment, Integer blogid)throws BlogException;
}
