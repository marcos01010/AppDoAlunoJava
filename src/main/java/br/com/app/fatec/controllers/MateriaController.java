package br.com.app.fatec.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.fatec.entities.Materia;
import br.com.app.fatec.entities.Turno;
import br.com.app.fatec.entities.Usuario;
import br.com.app.fatec.repositories.MateriaRepository;
import br.com.app.fatec.repositories.TurnoRepository;
import br.com.app.fatec.repositories.UsuarioRepository;

@RestController
@RequestMapping("/materia")
public class MateriaController {
	
	@Autowired
	MateriaRepository repository;
	@Autowired
	TurnoRepository turnoRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<Materia> findAll(){
		return repository.findAll();
	}
	
	@PostMapping("/assumida")
	public List<br.com.app.fatec.entities.delivery.Materia> findByHash(HttpServletResponse response,@RequestBody String hashChamada){
		try {
			if (hashChamada == null || hashChamada == "") {
				response.getWriter().println("Erro: Professor inválido.");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				return null;				
			}
			
			Usuario professor = usuarioRepository.findByHashChamada(hashChamada.replace("\"", "")).get(0);
			
			if(professor == null) {
				response.getWriter().println("Erro: Professor inválido.");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				return null;				
			}
			
			return repository.findByProfessor(professor).stream()
					.map(m ->{
						return new br.com.app.fatec.entities.delivery.Materia(
								m.getSigla(),
								m.getDescricao(),
								m.getProfessor().getId(),
								m.getProfessor().getNome(),
								m.getTurno().getId());
					}).collect(Collectors.toList());		
		} catch (IOException e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return null;
		}
	}
	
	@PostMapping
	public Materia findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	@PostMapping("/novo")
	public Materia insert(String s, String d, Long t) {
		try {
			Turno turno = turnoRepository.findById(t).orElseThrow();
			return repository.save(new Materia(s, d, turno));
		} catch (Exception e) {
			return null;
		}
	}
	
	@PostMapping("/atualizar")
	public boolean insert(@RequestBody List<Materia> materias) {
		try{
			Usuario u = usuarioRepository.findById(materias.get(0).getAlunos().get(0).getId()).get();
			materias.forEach(m -> {
				Turno turno = turnoRepository.findById(m.getTurno().getId()).get();
				List<Materia> materiasBanco = repository.findBySiglaAndTurno(m.getSigla(),turno);
				Materia materia = null;
				
				if(!materiasBanco.isEmpty()) {
					materia = materiasBanco.get(0);
				}
				
				if(materia != null) {
					materia.getAlunos().addAll(List.of(u));
					u.getMaterias().addAll(List.of(materia));
					usuarioRepository.save(u);
				}else {
					repository.save(m);
				}				
			});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@PatchMapping
	public boolean assumirMateria(HttpServletResponse response,@RequestBody Materia materia) {
		try {
			Usuario professor = usuarioRepository
					.findByHashChamada(materia.getProfessor().getHashChamada()).get(0);
			Turno turno = turnoRepository.findById(materia.getTurno().getId()).get();
			Materia materiaBanco = repository.findBySiglaAndTurno(materia.getSigla(),turno).get(0);
			
			if(professor == null || materiaBanco == null) {
				response.getWriter().println("Erro: Não foi completar a operação.");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				return false;
			}
			
			materiaBanco.setProfessor(professor);
			repository.save(materiaBanco);
			response.setStatus(HttpStatus.OK.value());
			return true;
			
		} catch (IOException e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return false;
		}
		
	}
	
	@PostMapping("/alterar")
	public Materia alterar(Materia materia) {
		if (materia.getId() != null && materia.getId() != 0L) {
			return repository.save(materia);
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
