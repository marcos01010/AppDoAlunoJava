package br.com.app.fatec.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.fatec.entities.Equipamento;
import br.com.app.fatec.repositories.EquipamentoRepository;

@RestController
@RequestMapping("/equipamento")
public class EquipamentoController {

	@Autowired
	private EquipamentoRepository repository;
	
	@GetMapping
	public List<Equipamento> findAll(){
		return repository.findByAtivoTrue();
	}
	
	@PostMapping
	public Equipamento findById(@RequestParam long id) {
		return repository.findByAtivoTrueAndId(id).orElse(null);
	}
	
	@PostMapping("/novo")
	public Equipamento insert(@RequestParam String d) {
		return repository.save(new Equipamento(d));
	}
	
	@PostMapping("/alterar")
	public Equipamento alterar(Equipamento equipamento) {
		return repository.save(equipamento);
	}
	
	@DeleteMapping
	public boolean deletar(@RequestParam long id) {
		try {
			Equipamento equipamento = repository.findById(id).orElseThrow();
			equipamento.setAtivo(false);
			repository.save(equipamento);			
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
}
