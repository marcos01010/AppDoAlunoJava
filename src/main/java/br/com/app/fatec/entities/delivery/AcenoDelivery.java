package br.com.app.fatec.entities.delivery;

public class AcenoDelivery {
	private Local local;
	private String nomeUsuario;
	private String descricao;
	private String nomeMateria;
	private Integer confirmacoes;
	private Long id;
	
	public Local getLocal() {
		return local;
	}
	public void setLocal(Local local) {
		this.local = local;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNomeMateria() {
		return nomeMateria;
	}
	public void setNomeMateria(String nomeMateria) {
		this.nomeMateria = nomeMateria;
	}
	
	public AcenoDelivery(Local local, String nomeUsuario, String descricao, String nomeMateria) {
		super();
		this.local = local;
		this.nomeUsuario = nomeUsuario;
		this.descricao = descricao;
		this.nomeMateria = nomeMateria;
	}
	public AcenoDelivery() {
	}
	public Integer getConfirmacoes() {
		return confirmacoes;
	}
	public void setConfirmacoes(Integer confirmacoes) {
		this.confirmacoes = confirmacoes;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
