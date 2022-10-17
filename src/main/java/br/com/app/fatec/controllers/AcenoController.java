package br.com.app.fatec.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

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
import br.com.app.fatec.repositories.AcenoRepository;
import br.com.app.fatec.service.AcenoService;

@RequestMapping("/aceno")
@RestController
public class AcenoController {
	
	@Autowired
	private AcenoRepository repository;
	
	@GetMapping
	private List<Aceno> find(HttpServletResponse response, Long id){
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
				return acenos;
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
	
	@PostMapping
	private Aceno novo(HttpServletResponse response, @RequestBody Aceno aceno) {
		try {
			response.setCharacterEncoding("UTF-8");
			
			if(AcenoService.validarAceno(aceno)) {
				aceno.setData(Calendar.getInstance().getTime());
				Aceno a = repository.save(aceno);
				if(a != null) {
					return a;
				}else {
					response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());				
					response.getWriter().println("Erro: Não foi	completar a operação.");
					return null;
				}
			}
			
			response.setStatus(HttpStatus.BAD_REQUEST.value());				
			response.getWriter().println("Erro: " + AcenoService.getCausa());
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return null;
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
