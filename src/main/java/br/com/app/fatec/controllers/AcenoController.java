package br.com.app.fatec.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.fatec.entities.Aceno;
import br.com.app.fatec.entities.Materia;
import br.com.app.fatec.entities.delivery.AcenoDelivery;
import br.com.app.fatec.entities.delivery.Local;
import br.com.app.fatec.repositories.AcenoRepository;
import br.com.app.fatec.repositories.MateriaRepository;
import br.com.app.fatec.service.AcenoService;

@RequestMapping("/aceno")
@RestController
public class AcenoController {
	
	@Autowired
	private AcenoRepository repository;
	
	@Autowired
	private MateriaRepository materiaRepository;
	
	@GetMapping
	private List<AcenoDelivery> find(HttpServletResponse response, Long id){
		try {
			response.setCharacterEncoding("UTF-8");
			if(id == null || id == 0L) {
				response.setStatus(HttpStatus.BAD_REQUEST.value());				
				response.getWriter().println("Erro: Usuário não informado.");
			    return null;
			}
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MINUTE, -30);
			
			List<Aceno> acenos = repository.find(id, calendar.getTime());
			
			if(acenos.size() > 0) {				
				return acenos.stream().map(a ->{
					Local local = new Local();					
					local.setNumero(a.getSala().getNumero());
					local.setDescricaoPredio(a.getSala().getPredio().getDescricao());
					
					AcenoDelivery acenoDelivery = new AcenoDelivery();
					acenoDelivery.setDescricao(a.getDescricao());
					acenoDelivery.setLocal(local);
					acenoDelivery.setNomeUsuario(a.getUsuario().getNome());
					acenoDelivery.setNomeMateria(a.getAtividade().getMateria().getDescricao());
					acenoDelivery.setConfirmacoes(a.getContator());
					acenoDelivery.setId(a.getId());
					
					return acenoDelivery;
				}).collect(Collectors.toList());
			}else {
				response.getWriter().println("Erro: Ninguém acenou para você ainda.");
				response.setStatus(HttpStatus.NO_CONTENT.value());
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return null;
		}
	}
	
	@PostMapping("/confirmar")
	private void confirmar(Long id) {
		Aceno aceno = repository.findById(id).get();
		aceno.setContator(aceno.getContator() + 1);
		repository.save(aceno);
	}
	
	@PostMapping
	private boolean novo(HttpServletResponse response, @RequestBody Aceno aceno) {
		try {
			response.setCharacterEncoding("UTF-8");
			
			Materia materia = materiaRepository.findBySiglaAndTurno(aceno.getAtividade().getMateria().getSigla(),
					aceno.getAtividade().getMateria().getTurno()).get(0);
			
			aceno.getAtividade().setMateria(materia);
			
			if(AcenoService.validarAceno(aceno)) {
				aceno.setData(Calendar.getInstance().getTime());
				aceno.setContator(0);
				Aceno a = repository.save(aceno);
				if(a != null) {
					return true;
				}else {
					response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());				
					response.getWriter().println("Erro: Não foi	completar a operação.");
					return false;
				}
			}
			
			response.setStatus(HttpStatus.BAD_REQUEST.value());				
			response.getWriter().println("Erro: " + AcenoService.getCausa());
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return false;
		}
	}
	
	@DeleteMapping
	private void deletar(HttpServletResponse response, Long id) {
		try {
			repository.deleteById(id);
			response.setStatus(HttpStatus.OK.value());
		}catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

}
