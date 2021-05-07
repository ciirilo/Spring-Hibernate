package com.orangetalents.viacep.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orangetalents.viacep.domain.model.Enderecos;


@Repository
public interface AdressRepository extends JpaRepository<Enderecos, Long>{
	
	boolean existsByUser(String user);
	List<Enderecos> findByUser(String user);
	
}
