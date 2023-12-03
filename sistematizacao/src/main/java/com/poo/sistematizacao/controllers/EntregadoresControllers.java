package com.poo.sistematizacao.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.poo.sistematizacao.dtos.EntregadoresDto;
import com.poo.sistematizacao.models.Entregadores;
import com.poo.sistematizacao.repositories.EntregadoresRepository;


@RestController
public class EntregadoresControllers {
	
	@Autowired
	EntregadoresRepository entregadoresrepository;
	
	@PostMapping("/entregadores")
	public ResponseEntity<Entregadores> salvar(@RequestBody @Valid EntregadoresDto entregadoresDto) {
		var entregadores = new Entregadores();
		BeanUtils.copyProperties(entregadoresDto, entregadores);
		return ResponseEntity.status(HttpStatus.CREATED).body(entregadoresrepository.save(entregadores));
	}
	
	@GetMapping("/entregadores")
	public ResponseEntity<List<Entregadores>> listar(){
		List<Entregadores> entregadoresList = entregadoresrepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(entregadoresList);
		
	}
	

	@GetMapping("/entregadores/{id}")
	public ResponseEntity<Object> detalhar(@PathVariable(value="id") Integer id){
		Optional<Entregadores> entregadores = entregadoresrepository.findById(id);
		if(entregadores.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entregador não encontrado.");
	}
		return ResponseEntity.status(HttpStatus.OK).body(entregadores.get());
		
}
	
	@DeleteMapping("/entregadores/{id}")
	public ResponseEntity<Object> deletarProduct(@PathVariable(value="id") Integer id) {
		Optional<Entregadores> entregadores = entregadoresrepository.findById(id);
		if(entregadores.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entregador não encontrado para remoção.");
		}
		
		entregadoresrepository.delete(entregadores.get());
		return ResponseEntity.status(HttpStatus.OK).body("O entregador foi excluído.");
	}

	@PutMapping("/entregadores/{id}")
	public ResponseEntity<Object> atualizarEntregador(@PathVariable(value="id") Integer id, @RequestBody @Valid EntregadoresDto entregadoresDto) {
		Optional<Entregadores> Entregadores = entregadoresrepository.findById(id);
		if(Entregadores.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entregador não encontrado para atualização.");
		}
		var entregadores = Entregadores.get();
		BeanUtils.copyProperties(entregadoresDto, entregadores);
		return ResponseEntity.status(HttpStatus.OK).body(entregadoresrepository.save(entregadores));
	}


}
	
		

	
	





