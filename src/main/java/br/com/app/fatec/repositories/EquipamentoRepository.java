package br.com.app.fatec.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.fatec.entities.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
	public List<Equipamento> findByAtivoTrue();
	public Optional<Equipamento> findByAtivoTrueAndId(long id);
}
