package com.jproject.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jproject.workshopmongo.domain.User;
import com.jproject.workshopmongo.dto.UserDTO;
import com.jproject.workshopmongo.repository.UserRepository;
import com.jproject.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(String id) {
		var user = repository.findById(id);
		
		if (user.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		} 
		
		return user.get();
	}
	
	public User insert(User user) {
		return repository.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User update(User user) {
		var newObj = repository.findById(user.getId()).get();
		updateData(newObj, user);
		return repository.save(newObj);
		
	}
	
	private void updateData(User newObj, User user) {
		newObj.setName(user.getName());
		newObj.setEmail(user.getEmail());
	}

	public User fromDTO(UserDTO dto) {
		return new User(dto.getId(), dto.getName(), dto.getEmail());
	}
	

}
