package com.example.algamoneyapi.repository.pessoa;

import java.util.List;

import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.filter.PessoaFilter;

public interface PessoaRepositoryQuery {
	
	public List<Pessoa> filtrar(PessoaFilter pessoaFilter);

}
