package br.com.app.fatec.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.fatec.entities.Predio;
import br.com.app.fatec.repositories.PredioRepository;

@RestController
@RequestMapping("/predio")
public class PredioController {
	
	@Autowired
	private PredioRepository repository;
	
	@GetMapping
	public List<Predio> findAll(){
		return repository.findAll();
	}
	
	@PostMapping
	public Predio findById(@RequestParam long id) {
		return repository.findById(id).orElse(null);
	}
	
	@PostMapping("/novo")
	public Predio insert(@RequestParam String d) {
		return repository.save(new Predio(d));
	}
	
	@PostMapping("/alterar")
	public Predio alterar(Predio predio) {
		if (predio.getId() != null && predio.getId() != 0L) {
			return repository.save(predio);
		}else {
			return null;
		}
	}
	
	@DeleteMapping
	public boolean deletar(@RequestParam long id) {
		try {
			repository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
