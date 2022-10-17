package br.com.app.fatec.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class Materia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 16)
	private String sigla;
	
	@Column(length = 64)
	private String descricao;
	
	@ManyToOne
	private Turno turno;
	
	@ManyToOne
	private Usuario professor;
	
	@ManyToMany(mappedBy = "materias")
	@JsonIgnoreProperties("materias")
	private List<Usuario> alunos;

	public Materia() {
	}

	public Materia(String sigla, String descricao, Turno turno) {
		super();
		this.sigla = sigla;
		this.descricao = descricao;
		this.turno = turno;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public List<Usuario> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Usuario> alunos) {
		this.alunos = alunos;
	}

	public Usuario getProfessor() {
		return professor;
	}

	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}

	@Override
	public String toString() {
		return "Materia [id=" + id + ", sigla=" + sigla + ", descricao=" + descricao + ", turno=" + turno + ", alunos="
				+ alunos + "]";
	}
	
	
}
