

import java.util.ArrayList;

public class Livro {
	
	private String codAcervo;
	private String tipoMaterial;
	private String titulo;
	private String classificacao;
	private String ISBN;
	private int qtd;
	
	private ArrayList<Autor> autores = new ArrayList<Autor>();

	public String getCodAcervo() {
		return codAcervo;
	}

	public void setCodAcervo(String codAcervo) {
		this.codAcervo = codAcervo;
	}

	public String getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(String tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public ArrayList<Autor> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<Autor> autores) {
		this.autores = autores;
	}
	
	
	
	

}
