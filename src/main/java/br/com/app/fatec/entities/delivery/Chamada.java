package br.com.app.fatec.entities.delivery;

public class Chamada {
	private Long id;
	private String sigla;
	private String materia;
	private Long professorID;
	private String data;
	private String nomeProfessor;
	
	public Chamada(Long id, String sigla, String materia, Long professorID, String nomeProfessor) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.materia = materia;
		this.professorID = professorID;
		this.nomeProfessor = nomeProfessor;
	}
	
	public String getNomeProfessor() {
		return nomeProfessor;
	}
	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Long getProfessorID() {
		return professorID;
	}
	public void setProfessorID(Long professorID) {
		this.professorID = professorID;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}	
}
