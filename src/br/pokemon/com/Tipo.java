package br.pokemon.com;

public class Tipo {
	String nmTipo;
	String codTipo;
	
	public void setCodTipo(String codTipo) {
		this.codTipo = codTipo;
	}

	public String getCodTipo() {
		return codTipo;
	}

	public String getNomeDesc() {
		return nmTipo;
	}

	public void setNomeDesc(String nomeDesc) {
		this.nmTipo = nomeDesc;
	}
}
