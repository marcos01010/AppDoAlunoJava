package br.com.app.fatec.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.fatec.entities.Chamada;

public interface ChamadaRepository extends JpaRepository<Chamada, Long>{
	@Query("select c from Chamada c "
			+ "join c.atividade a "
			+ "join a.materia m "
			+ "where m.sigla in :materias and c.data >= :data")
	List<Chamada> find(List<String> materias, Date data);
}
