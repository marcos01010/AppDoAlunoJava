package br.com.app.fatec.entities.delivery;

import java.util.Date;

public class ReservaDTO {
	private Long id;
	private Date inicio;
	private Date fim;
	private Integer numeroSala;
	private String nomeSolicitante;
	private Long usuarioID;
	private Long salaID;
	private String descricao;
	
	public ReservaDTO(Long id, Date inicio, Date fim, Integer numeroSala, String nomeSolicitante, Long salaID, String descricao) {
		super();
		this.id = id;
		this.inicio = inicio;
		this.fim = fim;
		this.numeroSala = numeroSala;
		this.nomeSolicitante = nomeSolicitante;
		this.salaID = salaID;
		this.descricao = descricao;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	public Integer getNumeroSala() {
		return numeroSala;
	}
	public void setNumeroSala(Integer numeroSala) {
		this.numeroSala = numeroSala;
	}
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}
	public Long getUsuarioID() {
		return usuarioID;
	}
	public void setUsuarioID(Long usuarioID) {
		this.usuarioID = usuarioID;
	}
	public Long getSalaID() {
		return salaID;
	}
	public void setSalaID(Long salaID) {
		this.salaID = salaID;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
}
