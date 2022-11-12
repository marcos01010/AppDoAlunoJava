package br.com.app.fatec.repositories;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.app.fatec.entities.Aceno;

public interface AcenoRepository extends JpaRepository<Aceno, Long> {
	@Query("select distinct a from Aceno a "
			+ "join a.atividade at "
			+ "join at.materia m "
			+ "join m.alunos al "
			+ "where al.id = :id and a.data >= :data")
	List<Aceno> find(Long id, Date data);
}
