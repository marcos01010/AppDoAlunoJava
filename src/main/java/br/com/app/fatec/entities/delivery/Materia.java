package br.com.app.fatec.entities.delivery;

public class Materia {
	private String sigla;
	private String descricao;
	private Long professorId;
	private String nomeProfessor;
	private Long turnoID;
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getProfessorId() {
		return professorId;
	}
	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}
	public String getNomeProfessor() {
		return nomeProfessor;
	}
	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}
	public Long getTurnoID() {
		return turnoID;
	}
	public void setTurnoID(Long turnoID) {
		this.turnoID = turnoID;
	}
	public Materia(String sigla, String descricao, Long professorId, String nomeProfessor, Long turnoID) {
		super();
		this.sigla = sigla;
		this.descricao = descricao;
		this.professorId = professorId;
		this.nomeProfessor = nomeProfessor;
		this.turnoID = turnoID;
	}

	
}
