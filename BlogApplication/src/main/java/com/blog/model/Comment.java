package com.blog.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer commentId;
	private String comment;
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime timeStamp ;
	
	@ManyToOne
	@JsonBackReference
	private Blog blog;
	public Comment() {
		this.timeStamp=LocalDateTime.now();
	}
	
	

}
