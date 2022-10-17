package br.com.app.fatec.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.fatec.entities.Turno;
import br.com.app.fatec.repositories.TurnoRepository;

@RestController
@RequestMapping("/turno")
public class TurnoController {

	@Autowired
	TurnoRepository repository;
	
	@GetMapping
	public List<Turno> findAll(){
		return repository.findAll();
	}
	
	@PostMapping
	public Turno findById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	@PostMapping("/novo")
	public Turno save(String d) {
		return repository.save(new Turno(d));		
	}
	
	@PostMapping("/alterar")
	public Turno alterar(Turno turno) {
		if (turno.getId() != null && turno.getId() != 0L) {
			return repository.save(turno);
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
