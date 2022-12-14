package br.com.app.fatec.entities.delivery;

public class Local {
	private Long id;
	private Integer numero;
	private Long predioId;
	private String descricaoPredio;
	private String descricaoSala;
	
	public Local(Long id, Integer numero, Long predioId, String descricaoPredio, String descricaoSala) {
		super();
		this.id = id;
		this.numero = numero;
		this.predioId = predioId;
		this.descricaoPredio = descricaoPredio;
		this.descricaoSala = descricaoSala;
	}
	
	public Local() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Long getPredioId() {
		return predioId;
	}

	public void setPredioId(Long predioId) {
		this.predioId = predioId;
	}

	public String getDescricaoPredio() {
		return descricaoPredio;
	}

	public void setDescricaoPredio(String descricaoPredio) {
		this.descricaoPredio = descricaoPredio;
	}

	public String getDescricaoSala() {
		return descricaoSala;
	}

	public void setDescricaoSala(String descricaoSala) {
		this.descricaoSala = descricaoSala;
	}
	
}
