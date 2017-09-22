package com.example.algamoneyapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoneyapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	//O optional nao precisa ficar verificando se a string e diferente de null
	public Optional<Usuario> findByEmail(String email);

}
