package br.com.app.fatec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.fatec.entities.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{

}
