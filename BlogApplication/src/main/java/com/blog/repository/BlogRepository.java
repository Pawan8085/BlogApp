package com.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.model.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer>{

	public List<Blog> findByCategory(String category);
}
