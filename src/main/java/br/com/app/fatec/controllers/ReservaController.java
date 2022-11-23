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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.fatec.entities.Reserva;
import br.com.app.fatec.entities.delivery.ReservaDTO;
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
	public List<ReservaDTO> findAll(){
		List<Reserva> reserva = repository.findAll();
		
		return reserva.stream().map(r -> {
			return new ReservaDTO(r.getId(), 
					r.getInicio(),
					r.getFim(), 
					r.getSala().getNumero(), 
					r.getUsuario().getNomeCompleto(), 
					r.getSala().getId(),
					r.getSala().getDescricao());
		}).collect(Collectors.toList());		
	}
	
	@PostMapping
	public Reserva findById(@RequestParam Long id) {
		return repository.findById(id).orElse(null);
	}
	
	@PostMapping("/novo")
	public boolean insert(HttpServletResponse response, @RequestBody ReservaDTO reserva) {
		try {
			if(ReservaService.validarReserva(reserva, repository, salaRepository, usuarioRepository)) {
				Reserva r = repository.save(ReservaService.getReservaValidada());
				if(r != null) {
					response.setStatus(HttpStatus.OK.value());
					return true;
				}else {
					response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
					return false;
				}
			}else {
				return false;
			}
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
			return false;
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
