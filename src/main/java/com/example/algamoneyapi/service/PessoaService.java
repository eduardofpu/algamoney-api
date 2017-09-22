package com.example.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.PessoaRepository;
import com.example.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class PessoaService {
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		
		Pessoa pessoaSalva = pessoaRepository.findOne(codigo);		
	      if(pessoaSalva==null) {    	  
	    	  throw new EmptyResultDataAccessException(1);			
			}
			BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
			return pessoaRepository.save(pessoaSalva);
		
	}
	
	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);		
		pessoaSalva.setAtivo(ativo);
		
		pessoaRepository.save(pessoaSalva);
		
	}
	
private Pessoa buscarPessoaPeloCodigo(Long codigo) {
		
		Pessoa pessoaSalva = pessoaRepository.findOne(codigo);		
	      if(pessoaSalva==null) {    	  
	    	  //throw new EmptyResultDataAccessException(1);
	    	  throw new PessoaInexistenteOuInativaException();
			}
			
			return pessoaSalva;
		
	}
	

}
