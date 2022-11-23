package br.com.app.fatec.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 16)
	private String nome;
	
	@Column(length = 32)
	private String sobreNome;
	
	private String hashChamada;
	
	private Long ra;
	
	@ManyToOne
	private Status status;
	
	@ManyToOne
	private Perfil perfil;
	
	@ManyToMany(mappedBy = "alunos")
	@JsonIgnoreProperties("chamadas")
	private List<Chamada> chamadas;
	
	@ManyToMany
	@JoinTable(
			name = "usuario_materia",
			joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "materia_id", referencedColumnName = "id")
			)
	@JsonIgnoreProperties("materias")
	private List<Materia> materias;

	public Usuario(String nome, String sobreNome, Long ra, Status status, Perfil perfil) {
		super();
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.ra = ra;
		this.status = status;
		this.perfil = perfil;
	}

	public Usuario() {
	}
	
	public Usuario(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public Long getRa() {
		return ra;
	}

	public void setRa(Long ra) {
		this.ra = ra;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Chamada> getChamadas() {
		return chamadas;
	}

	public void setChamadas(List<Chamada> chamadas) {
		this.chamadas = chamadas;
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public String getHashChamada() {
		return hashChamada;
	}

	public void setHashChamada(String hashChamada) {
		this.hashChamada = hashChamada;
	}
	
	public String getNomeCompleto() {
		return nome + " " + sobreNome;
	}	
}
