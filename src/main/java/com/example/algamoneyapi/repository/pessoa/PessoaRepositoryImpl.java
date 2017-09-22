package com.example.algamoneyapi.repository.pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.util.StringUtils;
import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.model.Pessoa_;
import com.example.algamoneyapi.repository.filter.PessoaFilter;

public class PessoaRepositoryImpl implements PessoaRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<Pessoa> filtrar(PessoaFilter pessoaFilter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
		
		
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		//criar restrições
		Predicate[] predicates = criarRestricoes(pessoaFilter,builder,root);				
				criteria.where(predicates);
				
				TypedQuery<Pessoa> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}
	
	
	private Predicate[] criarRestricoes(PessoaFilter pessoaFilter, CriteriaBuilder builder, Root<Pessoa> root) {
		
		
     List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(pessoaFilter.getNome())) {
			//where descricao like '%asssd%'
	     predicates.add(builder.like(builder.lower(root.get(Pessoa_.nome)),"%" + pessoaFilter.getNome().toLowerCase() + "%"));
			
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
