package com.blog.service;

import java.util.List;

import com.blog.exceptions.CategoryException;
import com.blog.model.Blog;
import com.blog.model.USER;

public interface BlogService {
	
	public USER createBlog(Blog blog);
	
	public List<Blog> getBlogsByCategory(String category)throws CategoryException;

}
