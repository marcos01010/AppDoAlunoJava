package br.com.app.fatec.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Chamada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date data;
	
	@ManyToOne
	private Usuario professor;
		
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "usuario_chamada",
			joinColumns = @JoinColumn(name = "chamada_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id")
			)
	@JsonIgnoreProperties("alunos")
	private List<Usuario> alunos;
	
	@OneToOne
	private Atividade atividade;

	public Chamada(Date data, Usuario professor, List<Usuario> alunos, Atividade atividade) {
		this.data = data;
		this.professor = professor;
		this.alunos = alunos;
		this.atividade = atividade;
	}

	public Chamada() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getProfessor() {
		return professor;
	}

	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}

	public List<Usuario> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Usuario> alunos) {
		this.alunos = alunos;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	
}
