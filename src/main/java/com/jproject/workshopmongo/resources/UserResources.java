package com.jproject.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jproject.workshopmongo.dto.UserDTO;
import com.jproject.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResources {
	
	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> FindAll() {
		var list = service.findAll();
		
		var listDTO = list.stream().map(user -> {
			return new UserDTO(user);
		}).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
}
