package br.com.fiap.resource.to;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UltimaCeiaTO {

	private int id;
	private String nomePrato;
	private String receita;
	private int kcal;
	private Integer voto;

	public UltimaCeiaTO() {
	}

	public UltimaCeiaTO(int id, String nomePrato, String receita, int kcal, Integer voto) {
		super();
		this.id = id;
		this.nomePrato = nomePrato;
		this.receita = receita;
		this.kcal = kcal;
		this.voto = voto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomePrato() {
		return nomePrato;
	}

	public void setNomePrato(String nomePrato) {
		this.nomePrato = nomePrato;
	}

	public String getReceita() {
		return receita;
	}

	public void setReceita(String receita) {
		this.receita = receita;
	}

	public Integer getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public Integer getVoto() {
		return voto;
	}

	public void setVoto(Integer voto) {
		this.voto = voto;
	}

	@Override
	public String toString() {
		return "UltimaCeiaTO [id=" + id + ", nomePrato=" + nomePrato + ", receita=" + receita + ", kcal=" + kcal
				+ ", voto=" + voto + "]";
	}

}
