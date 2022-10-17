package br.com.app.fatec.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.fatec.entities.Status;
import br.com.app.fatec.repositories.StatusRepository;

@RestController
@RequestMapping("/status")
public class StatusController {
	
	@Autowired
	StatusRepository repository;
	
	@GetMapping
	public List<Status> findAll(){
		return repository.findAll();
	}
	
	@PostMapping
	public Status findById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	@PostMapping("/novo")
	public Status save(String d) {
		return repository.save(new Status(d));		
	}
	
	@PostMapping("/alterar")
	public Status alterar(Status status) {
		if (status.getId() != null && status.getId() != 0L) {
			return repository.save(status);
		}else {
			return null;
		}
	}
	
	@DeleteMapping
	public boolean delete(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
