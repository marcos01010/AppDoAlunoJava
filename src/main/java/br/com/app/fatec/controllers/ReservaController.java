package br.com.app.fatec.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.fatec.entities.Reserva;
import br.com.app.fatec.repositories.ReservaRespository;
import br.com.app.fatec.repositories.SalaRepository;
import br.com.app.fatec.repositories.UsuarioRepository;
import br.com.app.fatec.service.ReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaController {
	
	@Autowired
	ReservaRespository repository;
	@Autowired
	SalaRepository salaRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<Reserva> findAll(){
		return repository.findAll();
	}
	
	@PostMapping
	public Reserva findById(@RequestParam Long id) {
		return repository.findById(id).orElse(null);
	}
	
	@PostMapping("/novo")
	public Reserva insert(@RequestBody Reserva reserva) {
		//Reserva reserva = new Reserva(new Usuario(usuarioID), inicio, fim, new Sala(salaID));
		if(ReservaService.validarReserva(reserva, repository, salaRepository, usuarioRepository)) {
			return repository.save(reserva);
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
