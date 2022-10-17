package br.com.app.fatec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.fatec.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByHashChamada(String hashChamada);
}
