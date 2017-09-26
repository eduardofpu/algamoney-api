package com.example.algamoneyapi.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.example.algamoneyapi.model.Categoria_;
import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.model.Lancamento_;
import com.example.algamoneyapi.model.Pessoa_;
import com.example.algamoneyapi.repository.filter.LancamentoFilter;
import com.example.algamoneyapi.repository.projection.ResumoLancamento;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter,Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		
		
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		//criar restrições
		Predicate[] predicates = criarRestricoes(lancamentoFilter,builder,root);
		
		criteria.where(predicates);
		
		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		
		adicionarRestricaesDePaginacao(query,pageable);		
		
		return new PageImpl<>(query.getResultList(),pageable, total(lancamentoFilter));
	}



	@Override
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		
		criteria.select(builder.construct(ResumoLancamento.class
				, root.get(Lancamento_.codigo),root.get(Lancamento_.descricao) 
				, root.get(Lancamento_.dataVencimento),root.get(Lancamento_.dataPagamento)
				, root.get(Lancamento_.valor),root.get(Lancamento_.tipo) 
				, root.get(Lancamento_.categoria).get(Categoria_.nome)
				, root.get(Lancamento_.pessoa).get(Pessoa_.nome)));
		
		//criar restrições
				Predicate[] predicates = criarRestricoes(lancamentoFilter,builder,root);
				
				criteria.where(predicates);
				
				TypedQuery<ResumoLancamento> query = manager.createQuery(criteria);
				
				adicionarRestricaesDePaginacao(query,pageable);
				
				
				return new PageImpl<>(query.getResultList(),pageable, total(lancamentoFilter));
	}


	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
			//where descricao like '%asssd%'
			predicates.add(builder.like(
                        builder.lower(root.get(Lancamento_.descricao)),"%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
			
		}
		
        if(lancamentoFilter.getDataVencimentoDe()!= null) {
			//maior ou iqual
        	predicates.add(
        			builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento), lancamentoFilter.getDataVencimentoDe()));
			
			
		}
        
        if(lancamentoFilter.getDataVencimentoAte()!= null) {
			//menor ou igual
        	predicates.add(
        			
        			builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento), lancamentoFilter.getDataVencimentoAte()));
			
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}
	//Restrições de paginações total de registro e onde começa
    private void adicionarRestricaesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
		
	}
    

	private Long total(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));//conta quantos registro tem pra min
		
		
		return manager.createQuery(criteria).getSingleResult();//Retorna um resultado
	}






}
