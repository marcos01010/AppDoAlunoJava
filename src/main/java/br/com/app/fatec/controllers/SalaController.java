package br.com.app.fatec.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.fatec.entities.Sala;
import br.com.app.fatec.repositories.SalaRepository;
import br.com.app.fatec.entities.delivery.Local;

@RestController
@RequestMapping("/sala")
public class SalaController {
	
	@Autowired
	private SalaRepository repository;
	
	@GetMapping
	public List<Local> findAll(){
		List<Sala> sala = repository.findByAtivoTrue();
		
		return sala.stream().map(s ->{
			Local local = new Local();
			
			if(s.getPredio() != null) {
				local.setPredioId(s.getPredio().getId());
				local.setDescricaoPredio(s.getPredio().getDescricao());
				local.setDescricaoSala(s.getDescricao());
			}
			
			local.setId(s.getId());
			local.setNumero(s.getNumero());
			
			return local;
		}).collect(Collectors.toList());
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
