package br.com.app.fatec.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.app.fatec.entities.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long>{

	List<Sala> findByAtivoTrue();

	Sala findByAtivoTrueAndId(long id);

}
