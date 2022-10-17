package br.com.app.fatec.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.fatec.entities.Sala;
import br.com.app.fatec.repositories.SalaRepository;

@RestController
@RequestMapping("/sala")
public class SalaController {
	
	@Autowired
	private SalaRepository repository;
	
	@GetMapping
	public List<Sala> findAll(){
		return repository.findByAtivoTrue();
	}
	
	@PostMapping
	public Sala findById(@RequestParam long id) {
		return repository.findByAtivoTrueAndId(id);
	}
	
	@PostMapping("/novo")
	public Sala insert(@RequestParam int n,@RequestParam int c) {
		return repository.save(new Sala(n,c,true));
	}
	
	@PostMapping("/alterar")
	public Sala alterar(Sala sala) {
		return repository.save(sala);
	}
	
	@DeleteMapping
	public boolean deletar(@RequestParam long id) {
		try {
			Sala sala = repository.findById(id).orElseThrow();
			sala.setAtivo(false);
			repository.save(sala);			
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
