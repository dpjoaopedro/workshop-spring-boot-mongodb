package com.jproject.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jproject.workshopmongo.domain.Post;
import com.jproject.workshopmongo.repository.PostRepository;
import com.jproject.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	public Post findById(String id) {
		var post = repository.findById(id);
		
		if (post.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		} 
		
		return post.get();
	}
	
	public List<Post> findByTitle(String text) {
		return repository.findByTitleContainingIgnoreCase(text);
	}
}
