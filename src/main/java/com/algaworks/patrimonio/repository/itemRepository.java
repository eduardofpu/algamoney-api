package com.algaworks.patrimonio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.patrimonio.model.Item;

public interface itemRepository extends JpaRepository<Item,Long>{

}
