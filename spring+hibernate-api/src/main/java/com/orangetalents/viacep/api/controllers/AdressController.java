package com.orangetalents.viacep.api.controllers;

import java.util.List;

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

import com.orangetalents.viacep.domain.model.Enderecos;
import com.orangetalents.viacep.domain.model.Usuario;
import com.orangetalents.viacep.domain.repository.AdressRepository;
import com.orangetalents.viacep.domain.repository.UserRepository;


@RestController
@RequestMapping("/adress")
public class AdressController {
	
	@Autowired
	private AdressRepository adressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping
	public List<Enderecos> read() {
		return adressRepository.findAll();
	}
	
	
	@GetMapping("/{usuarioId}")
	public ResponseEntity<List<Enderecos>> find(@PathVariable Long usuarioId) {
		
		Usuario usuario = userRepository.findById(usuarioId).orElse(null);
		
		if(usuario == null)return ResponseEntity.notFound().build();
		
		String usuarioNome = usuario.getNome();
		List<Enderecos> endereco = adressRepository.findByUser(usuarioNome);
			if(!endereco.isEmpty()) {
			
				return  ResponseEntity.ok(endereco);
			}
		
		return ResponseEntity.notFound().build();
				
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Enderecos> create(@Valid @RequestBody Enderecos endereco) {
		
		
		if(!userRepository.existsByNome(endereco.getUser())) {
			
			return ResponseEntity.badRequest().build();
		}
		
		adressRepository.save(endereco);
		return ResponseEntity.created(null).body(endereco);
		
	}
	
	@DeleteMapping("/{enderecoId}")
	public ResponseEntity<Void> delete(@PathVariable Long enderecoId){
		if(!adressRepository.existsById(enderecoId)) {
			return ResponseEntity.notFound().build();
		}
		
		adressRepository.deleteById(enderecoId);
		
		return ResponseEntity.noContent().build();
	}

}
