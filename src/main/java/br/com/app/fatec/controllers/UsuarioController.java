package br.com.app.fatec.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.fatec.entities.Usuario;
import br.com.app.fatec.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository repository;
	
	@GetMapping
	public List<Usuario> findAll(){
		return repository.findAll();
	}
	
	@PostMapping
	public Usuario findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	@PostMapping("/novo")
	public Usuario save(@RequestBody Usuario usuario){
		return repository.save(usuario);
	}
	
	@PostMapping("/alterar")
	public Usuario alterar(@RequestBody Usuario usuario){
		return repository.save(usuario);
	}
	
	@DeleteMapping
	public boolean delete(Long id) {
		try {
			repository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
