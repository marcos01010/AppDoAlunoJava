package br.com.app.fatec.service;

import br.com.app.fatec.entities.Reserva;
import br.com.app.fatec.entities.Sala;
import br.com.app.fatec.entities.Usuario;
import br.com.app.fatec.entities.delivery.ReservaDTO;
import br.com.app.fatec.repositories.ReservaRespository;
import br.com.app.fatec.repositories.SalaRepository;
import br.com.app.fatec.repositories.UsuarioRepository;

public class ReservaService {
	private static Reserva reservaValidada = null;
	
	public static boolean validarReserva(ReservaDTO reservaDTO, ReservaRespository reservaRespository, 
			SalaRepository salaRepository, UsuarioRepository usuarioRepository) throws Exception {
		Usuario usuario = null;
		Sala sala = null;
		
		if(reservaDTO.getUsuarioID() == null 
				|| reservaDTO.getUsuarioID() == 0L 
				|| reservaDTO.getNumeroSala() == null
				|| reservaDTO.getNumeroSala() == 0
				|| reservaDTO.getInicio() == null
				|| reservaDTO.getFim() == null) {
			return false;
		}
		usuario = usuarioRepository.findById(reservaDTO.getUsuarioID()).get();
		sala = salaRepository.findById(reservaDTO.getSalaID()).get();
		
		if(usuario == null || sala == null) {
			return false;
		}
		
		boolean horario = reservaRespository
				.findBySalaIsAndInicioBetweenOrSalaIsAndFimBetween(sala, reservaDTO.getInicio(), reservaDTO.getFim(),
						sala,reservaDTO.getInicio(), reservaDTO.getFim()).isEmpty();
		
		if (horario) {
			reservaValidada = new Reserva();
			reservaValidada.setFim(reservaDTO.getFim());
			reservaValidada.setInicio(reservaDTO.getInicio());
			reservaValidada.setSala(sala);
			reservaValidada.setUsuario(usuario);
		}
		return horario;
	}
	
	public static Reserva getReservaValidada() {
		return reservaValidada;
	}
}
