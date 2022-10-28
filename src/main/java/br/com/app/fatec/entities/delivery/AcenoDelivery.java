package br.com.app.fatec.entities.delivery;

public class AcenoDelivery {
	private Local local;
	private String nomeUsuario;
	private String descricao;
	
	public AcenoDelivery(Local local, String nomeUsuario, String descricao) {
		super();
		this.local = local;
		this.nomeUsuario = nomeUsuario;
		this.descricao = descricao;
	}
	
	public AcenoDelivery() {
	}

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
	
	

}
