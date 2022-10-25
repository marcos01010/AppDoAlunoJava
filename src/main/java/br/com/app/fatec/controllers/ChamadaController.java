package br.com.app.fatec.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.fatec.entities.Atividade;
import br.com.app.fatec.entities.Chamada;
import br.com.app.fatec.entities.Materia;
import br.com.app.fatec.entities.Usuario;
import br.com.app.fatec.repositories.AtividadeRespository;
import br.com.app.fatec.repositories.ChamadaRepository;
import br.com.app.fatec.repositories.MateriaRepository;
import br.com.app.fatec.repositories.UsuarioRepository;
import br.com.app.fatec.service.ChamadaService;

@RequestMapping("/chamada")
@RestController
public class ChamadaController {
	
	@Autowired
	ChamadaRepository repository;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	MateriaRepository materiaRepository;
	@Autowired
	AtividadeRespository atividadeRespository;
	
	@PostMapping("/presente")
	public List<Usuario> buscarPresentes(HttpServletResponse response, Long chamadaID){
		try {
			if(chamadaID == null) {
				return null;
			}
			
			Optional<Chamada> chamada = repository.findById(chamadaID);		
			if(chamada.isEmpty()) {
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				return null;
			}
			
			if(chamada.get().getAlunos().isEmpty()) {
				response.setStatus(HttpStatus.NO_CONTENT.value());
			}
			
			return chamada.get().getAlunos();
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
			return null;
		}		
	}
	
	@PostMapping("/ativas")
	public List<Chamada> findAll(HttpServletResponse response, @RequestBody List<String> siglas){
		try {
			if(siglas != null && siglas.size() > 0) {
				Calendar calendar = Calendar.getInstance();
				List<Chamada> chamadas = repository.find(siglas,calendar.getTime());
				
				if(chamadas.size() == 0) {
					response.setStatus(HttpStatus.NO_CONTENT.value());
					return null;
				}
				return chamadas;
			}
			
			response.getWriter().println("Erro: Sem matérias para listar.");
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return null;
		}
	}
	
	@PostMapping("/ativas/professor")
	public List<Chamada> findAll(HttpServletResponse response, String hashChamada){
		try {
			Usuario professor = usuarioRepository.findByHashChamada(hashChamada);
			
			if(professor != null) {
				List<Chamada> chamadas = repository.findByProfessor(professor);
				if(chamadas.size() == 0) {
					response.setStatus(HttpStatus.NO_CONTENT.value());
					return null;
				}
				return chamadas;
			}
			
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return null;
		}
	}
	
	@PostMapping
	public boolean insert(HttpServletResponse response, @RequestBody Chamada chamada) {
		response.setCharacterEncoding("UTF-8");
		try {
			if(ChamadaService.validarChamada(chamada)) {
				Usuario professor = usuarioRepository.findByHashChamada(chamada.getProfessor().getHashChamada());
				Atividade novaAtividade = null;
				Materia novaMateria = materiaRepository
						.findBySigla(chamada.getAtividade().getMateria().getSigla()).get(0);
				boolean isAtividadeCadastrada = false;
				boolean isMateriaCadastrada = novaMateria != null;
				boolean isProfessor = professor != null;
				
				if(!isProfessor) {
					response.getWriter().println("Erro: Não foi autenticar professor.");
					response.setStatus(HttpStatus.BAD_REQUEST.value());
					return false;
				}
				
				if(!isMateriaCadastrada) {
					novaMateria = materiaRepository.save(chamada.getAtividade().getMateria());
					if(novaMateria == null) {
						response.getWriter().println("Erro: Não foi possível salvar a matéria.");
						response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
						return false;
					}
				}				
				
				if(chamada.getAtividade().getId() != null) {
					isAtividadeCadastrada = atividadeRespository
							.findById(chamada.getAtividade().getId())
							.isPresent();					
				}
				
				 
				if(!isAtividadeCadastrada) {
					Calendar calendar = Calendar.getInstance();
					novaAtividade = atividadeRespository.save(new Atividade(null,novaMateria,calendar.getTime()));
					chamada.setAtividade(novaAtividade);
					if(novaAtividade == null) {
						response.getWriter().println("Erro: Não foi possível salvar a atividade.");
						response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
						return false;
					}
				}				
				
				chamada.setProfessor(professor);
				Chamada novaChamada = repository.save(chamada);
				
				if(novaChamada != null) {
					response.setStatus(HttpStatus.CREATED.value());
					return true;					
				}else {
					response.getWriter().println("Erro: Não foi possível abrir a chamada.");
					response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
					return false;
				}			
			}
			
			response.setStatus(HttpStatus.BAD_REQUEST.value());				
			response.getWriter().println("Erro: " + ChamadaService.getCausa());
			return false;
		} catch (IOException e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return false;
		}
	}
	
	@PostMapping("/resposta")
	public Chamada resposta(Long c, Long u) {
		if(c == null || u == null) {
			return null;
		}
		
		Optional<Chamada> chamada = repository.findById(c);
		Optional<Usuario> usuario = usuarioRepository.findById(u);
		
		if(chamada.isPresent() && usuario.isPresent()) {
			chamada.get().getAlunos().addAll(List.of(usuario.get()));
		}else {
			return null;
		}
		
		return repository.save(chamada.get());
	}
	
	@DeleteMapping
	public boolean deletar(@RequestParam long id) {
		try {
			repository.deleteById(id);		
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
