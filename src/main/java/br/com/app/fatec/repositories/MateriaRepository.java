package br.com.app.fatec.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.app.fatec.entities.Materia;
import br.com.app.fatec.entities.Turno;
import br.com.app.fatec.entities.Usuario;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
	List<Materia> findBySiglaAndTurno(String sigla, Turno turno);
	List<Materia> findBySiglaIn(List<String> siglas);
	List<Materia> findByProfessor(Usuario professor);
	
}
