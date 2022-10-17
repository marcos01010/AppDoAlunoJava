package br.com.app.fatec.service;

import java.util.ArrayList;
import java.util.List;

import br.com.app.fatec.entities.Chamada;

public class ChamadaService {
	private static final String MATERIA_INVALIDA = "Materia inválida";
	private static final String ATIVIDADE_INVALIDA = "Atividade inválida";
	private static final String USUARIO_INVALIDO = "Usuário Inválido";
	private static final String PREPOSICAO_E = "e ";
	
	private static String causa = "";
	public static boolean validarChamada(Chamada chamada) {
		List<String> causas = new ArrayList<>();
		causa = "";
		
		if(chamada.getAtividade() == null) {
			causas.add(ATIVIDADE_INVALIDA);
		}
		
		if(chamada.getAtividade().getMateria() == null ||
				chamada.getAtividade().getMateria().getSigla() == null ||
				chamada.getAtividade().getMateria().getDescricao() == null ||
				chamada.getAtividade().getMateria().getTurno() == null) {
			causas.add(MATERIA_INVALIDA);
			
		}
		
		if(chamada.getProfessor() == null || 
				chamada.getProfessor().getHashChamada() == null ||
				chamada.getProfessor().getPerfil().getId() != 2L) {
			causas.add(USUARIO_INVALIDO);
		}
		
		
		if(causas.size() >= 3) {
			causa += causas.get(0) + ", ";
			causas.stream()
				.skip(1)
				.limit(causas.size() - 3)
				.forEach(s -> causa += s + ", ");
			causa += causas.get(causas.size() - 2) + " " + PREPOSICAO_E + causas.get(causas.size() - 1) + ".";
		}
		
		if(causas.size() == 2) {
			causa += causas.get(0) + " " + PREPOSICAO_E + causas.get(1) + ".";
		}
		
		if(causas.size() == 1) {
			causa += causas.get(0) + ".";
		}
		
		return causa.length() == 0;
	}
	
	public static String getCausa() {
		return causa;
	}

}
