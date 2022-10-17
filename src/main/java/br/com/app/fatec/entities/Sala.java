package br.com.app.fatec.entities;

import java.util.List;

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
public class Sala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int numero;
	
	private int capacidade;
	
	private boolean ativo;
	
	@ManyToOne
	private Predio predio;	
	
	@JoinTable(
			name = "equipamento_sala",
			joinColumns = @JoinColumn(name = "sala_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "equipamento_id", referencedColumnName = "id")
			)
	@ManyToMany
	@JsonIgnoreProperties("salas")
	private List<Equipamento> equipamentos;
	
	public Sala() {
	}
	
	public Sala(Long id) {
		this.id = id;
	}

	public Sala(int numero, int capacidade, boolean ativo) {
		super();
		this.numero = numero;
		this.capacidade = capacidade;
		this.ativo = ativo;
	}

	public Sala(int numero, int capacidade, boolean ativo, Predio predio, List<Equipamento> equipamentos) {
		super();
		this.numero = numero;
		this.capacidade = capacidade;
		this.ativo = ativo;
		this.predio = predio;
		this.equipamentos = equipamentos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Predio getPredio() {
		return predio;
	}

	public void setPredio(Predio predio) {
		this.predio = predio;
	}

	public List<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}
}
