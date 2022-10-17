package br.com.app.fatec.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.fatec.entities.Atividade;
import br.com.app.fatec.repositories.AtividadeRespository;

@RequestMapping("/atividade")
@RestController
public class AtividadeController {

	@Autowired
	AtividadeRespository repository;
	
	@GetMapping
	public List<Atividade> findAll(){
		return repository.findAll();
	}
	
	@PostMapping
	public Atividade findById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	@PostMapping("/novo")
	public Atividade save(@RequestBody Atividade atividade) {
		return repository.save(atividade);		
	}
	
	@PostMapping("/alterar")
	public Atividade alterar(Atividade atividade) {
		if (atividade.getId() != null && atividade.getId() != 0L) {
			return repository.save(atividade);
		}else {
			return null;
		}
	}
	
	@DeleteMapping
	public boolean delete(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
