package br.com.app.fatec.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public br.com.app.fatec.entities.delivery.Usuario findAll(){
		List<Usuario> u = repository.findAll();
		return u.stream().map(usuario1 ->{
			return new br.com.app.fatec.entities.delivery.Usuario(
					usuario1.getId(),
					usuario1.getNome(),
					usuario1.getSobreNome(),
					usuario1.getPerfil().getId(),
					usuario1.getHashChamada(),
					usuario1.getRa()
					);
		}).collect(Collectors.toList()).get(0);
	}
	
	@PostMapping
	public br.com.app.fatec.entities.delivery.Usuario findById(Long id) {
		Usuario u = repository.findById(id).orElse(null);
		return new br.com.app.fatec.entities.delivery.Usuario(
				u.getId(),
				u.getNome(),
				u.getSobreNome(),
				u.getPerfil().getId(),
				u.getHashChamada(),
				u.getRa()
				);
	}
	
	@PostMapping("/novo")
	public br.com.app.fatec.entities.delivery.Usuario save(HttpServletResponse response, @RequestBody Usuario usuario){
		List<Usuario> u = null;
		try {
			u = repository.findByRa(usuario.getRa());
			if(repository.findByRa(usuario.getRa()).isEmpty()) {
				u = List.of(repository.save(usuario));
			}
		}catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		
		if(u == null) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		
		return u.stream().map(usuario1 ->{
			return new br.com.app.fatec.entities.delivery.Usuario(
					usuario1.getId(),
					usuario1.getNome(),
					usuario1.getSobreNome(),
					usuario1.getPerfil().getId(),
					usuario1.getHashChamada(),
					usuario1.getRa()
					);
		}).collect(Collectors.toList()).get(0);		
	}
	
	@PostMapping("/alterar")
	public br.com.app.fatec.entities.delivery.Usuario alterar(@RequestBody Usuario usuario){
		Usuario u = repository.save(usuario);
		return new br.com.app.fatec.entities.delivery.Usuario(
				u.getId(),
				u.getNome(),
				u.getSobreNome(),
				u.getPerfil().getId(),
				u.getHashChamada(),
				u.getRa()
				);
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
