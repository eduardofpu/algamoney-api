package com.example.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.LancamentoRepository;
import com.example.algamoneyapi.repository.PessoaRepository;
import com.example.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;
@Service
public class LancamentoService{
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private LancamentoRepository lancamentoReposotory;
	
	
	
	public Lancamento Salvar(Lancamento lancamento) {
		
		validarPessoa(lancamento);	
		
		return lancamentoReposotory.save(lancamento);
	}
	
	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		
		Lancamento lancamentoSalvo = buscarLancamentoExistente(codigo);
		
		if(!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())) {
			
			validarPessoa(lancamento);
		}
		BeanUtils.copyProperties(lancamento,lancamentoSalvo, "codigo");
		
		return lancamentoReposotory.save(lancamentoSalvo);
	}


	
	private void validarPessoa(Lancamento lancamento) {
		
		Pessoa pessoa = null;
		
		if(lancamento.getPessoa().getCodigo() !=null) {
			
			pessoa=pessoaRepository.findOne(lancamento.getPessoa().getCodigo());				
		}
		if(pessoa==null || pessoa.isInativo()) {
			
			
				throw new PessoaInexistenteOuInativaException();
			
		}
		
	}
	

	private Lancamento buscarLancamentoExistente(Long codigo) {
		Lancamento lancamentoSalvo = lancamentoReposotory.findOne(codigo);
		if(lancamentoSalvo == null) {
			throw new  IllegalArgumentException();
		}
		return lancamentoSalvo;
	}
	
	
}
