package br.com.app.fatec.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.fatec.entities.Perfil;
import br.com.app.fatec.repositories.PerfilRepository;

@RestController
@RequestMapping("/perfil")
public class PerfilController {
	
	@Autowired
	PerfilRepository repository;
	
	@GetMapping
	public List<Perfil> findAll(){
		return repository.findAll();
	}
	
	@PostMapping
	public Perfil findById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	@PostMapping("/novo")
	public Perfil save(String d) {
		return repository.save(new Perfil(d));		
	}
	
	@PostMapping("/alterar")
	public Perfil alterar(Perfil perfil) {
		if (perfil.getId() != null && perfil.getId() != 0L) {
			return repository.save(perfil);
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
