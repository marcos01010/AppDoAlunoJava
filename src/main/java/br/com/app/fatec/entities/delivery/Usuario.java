package br.com.app.fatec.entities.delivery;

public class Usuario {
	private Long id;
	private String nome;
	private String sobreNome;
	private Long perfilID;
	private String hashChamada;
	private Long ra;
	
	public Usuario(Long id, String nome, String sobreNome, Long perfilID, String hashChamada, Long ra) {
		this.id = id;
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.perfilID = perfilID;
		this.hashChamada = hashChamada;
		this.ra = ra;
	}
	
	public Usuario() {}
	
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
	public Long getPerfilID() {
		return perfilID;
	}
	public void setPerfilID(Long perfilID) {
		this.perfilID = perfilID;
	}
	public String getHashChamada() {
		return hashChamada;
	}
	public void setHashChamada(String hashChamada) {
		this.hashChamada = hashChamada;
	}
	public Long getRa() {
		return ra;
	}
	public void setRa(Long ra) {
		this.ra = ra;
	}
}

/*@SerializedName("id") val id: Long,
    @SerializedName("nome") val nome: String,
    @SerializedName("sobreNome") val sobreNome: String,
    @SerializedName("perfil") val perfil: Perfil,
    @SerializedName("hashChamada") var hashChamada:String,
    @SerializedName("ra") var ra:Long)*/