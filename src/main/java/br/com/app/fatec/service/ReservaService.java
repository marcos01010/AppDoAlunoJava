package br.com.app.fatec.service;

import br.com.app.fatec.entities.Reserva;
import br.com.app.fatec.repositories.ReservaRespository;
import br.com.app.fatec.repositories.SalaRepository;
import br.com.app.fatec.repositories.UsuarioRepository;

public class ReservaService {
	public static boolean validarReserva(Reserva reserva, ReservaRespository reservaRespository, 
			SalaRepository salaRepository, UsuarioRepository usuarioRepository) {
		if(reserva.getInicio() == null || reserva.getFim() == null 
				|| reserva.getSala() == null || reserva.getUsuario() == null) {
			return false;
		}
		
		boolean usuario = usuarioRepository.findById(reserva.getUsuario().getId()).isPresent();
		boolean sala = salaRepository.findById(reserva.getSala().getId()).isPresent();
		boolean horario = reservaRespository
				.findByInicioBetweenAndFimBetween(reserva.getInicio(), reserva.getFim(),
						reserva.getInicio(), reserva.getFim()).isEmpty();
		
		return usuario && sala && horario;
	}
}
