package br.com.app.fatec.entities.delivery;

public class Materia {
	private String sigla;
	private String descricao;
	private Usuario professor;
	private String nomeProfessor;
	private Long turnoID;
	
	public Materia(String sigla, String descricao, Usuario professor, String nomeProfessor, Long turnoID) {
		super();
		this.sigla = sigla;
		this.descricao = descricao;
		this.professor = professor;
		this.nomeProfessor = nomeProfessor;
		this.turnoID = turnoID;
	}

	public Materia() {
	}
	
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
	
	public Usuario getProfessor() {
		return professor;
	}

	public void setProfessor(Usuario professor) {
		this.professor = professor;
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
}
