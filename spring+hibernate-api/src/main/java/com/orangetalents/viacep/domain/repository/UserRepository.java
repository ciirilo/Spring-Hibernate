package com.orangetalents.viacep.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orangetalents.viacep.domain.model.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long>{
	
	boolean existsByNome(String nome);
	boolean existsByEmail(String email);
	boolean existsByCPF(String cpf);
	String findByNome(Long id);
	
}

