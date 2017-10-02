package com.algaworks.patrimonio.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.patrimonio.model.Item;
import com.algaworks.patrimonio.repository.itemRepository;

@RestController//controlar a classe
@CrossOrigin("${origem-permitida}")
public class ItemResource {
	@Autowired//diser para o spring fazer uma injeção de instancia
	private itemRepository itemRepository;
	
	//Atender uma requisição http Listar todos os itens
	@GetMapping("/itens") //mapear o que o spring vai fazer requisição get e o mapeamento
	public List<Item> listar(){
		return itemRepository.findAll();
	}
	@PostMapping("/itens")
	public Item adicionar(@RequestBody @Valid Item item) {
		return itemRepository.save(item);
	}
	
	
	

}
