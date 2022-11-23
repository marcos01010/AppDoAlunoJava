package br.com.app.fatec.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.fatec.entities.Reserva;
import br.com.app.fatec.entities.Sala;

public interface ReservaRespository extends JpaRepository<Reserva, Long>{
	List<Reserva> findBySalaIsAndInicioBetweenOrSalaIsAndFimBetween(Sala sala, Date comecaInicio, Date comecafim,
			Sala sala2, Date terminaInicio, Date terminaFim);
}
