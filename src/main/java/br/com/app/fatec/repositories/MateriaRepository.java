package br.com.app.fatec.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.app.fatec.entities.Materia;
import br.com.app.fatec.entities.Usuario;

public interface MateriaRepository extends JpaRepository<Materia, Long> {
	List<Materia> findBySigla(String sigla);
	List<Materia> findBySiglaIn(List<String> siglas);
//	@Query("select m from Materia m "
//			+ "join m.professor p "
//			+ "where p = :professor")
	List<Materia> findByProfessor(Usuario professor);
	
}
