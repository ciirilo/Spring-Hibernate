package com.orangetalents.viacep.api.controllers;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.orangetalents.viacep.domain.model.*;


import com.orangetalents.viacep.domain.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public List<Usuario> read() {
		return userRepository.findAll();
	}
	
	@GetMapping("/{usuarioId}")
	public ResponseEntity<Usuario> find(@PathVariable Long usuarioId) {
		Optional<Usuario> usuario = userRepository.findById(usuarioId);
		
		if(usuario.isPresent()) {
			return ResponseEntity.ok(usuario.get());
		}
		
		return ResponseEntity.notFound().build();
				
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario usuario) {
		
		if(userRepository.existsByEmail(usuario.getEmail())
				|| userRepository.existsByCPF(usuario.getCPF())) {
			
			return ResponseEntity.badRequest().build();
		}
		
		userRepository.save(usuario);
		return ResponseEntity.created(null).body(usuario);
		
	}
	
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Void> delete(@PathVariable Long usuarioId){
		if(!userRepository.existsById(usuarioId)) {
			return ResponseEntity.notFound().build();
		}
		
		userRepository.deleteById(usuarioId);
		
		return ResponseEntity.noContent().build();
	}

}
