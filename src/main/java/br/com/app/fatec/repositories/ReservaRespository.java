package br.com.app.fatec.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.fatec.entities.Reserva;

public interface ReservaRespository extends JpaRepository<Reserva, Long>{
	List<Reserva> findByInicioBetweenAndFimBetween(Date comecaInicio, Date comecafim,
			Date terminaInicio, Date terminaFim);
}
