package br.com.app.fatec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.fatec.entities.Predio;

public interface PredioRepository extends JpaRepository<Predio, Long>{

}
