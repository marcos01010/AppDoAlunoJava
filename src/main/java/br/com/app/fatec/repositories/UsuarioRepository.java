package br.com.app.fatec.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.fatec.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	List<Usuario> findByHashChamada(String hashChamada);
	List<Usuario> findByRa(Long ra);
}
