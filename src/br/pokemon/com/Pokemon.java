package br.pokemon.com;

import java.util.ArrayList;

public class Pokemon {
	String numero;
	String nome;
	String descricao;
	String evoluide;
	String altura;
	String peso;
	String sexo;
	String categoria;
	String[] habilidade;
	String codhabilidade;
	String codtipo;
	String[] fraqueza;
	String[] tipo;
	ArrayList<String> arrayTipo = new ArrayList<>();
	ArrayList<String> arrayFraqueza = new ArrayList<>();
	ArrayList<String> arrayHab = new ArrayList<>();
 
	
	public String getCodhabilidade() {
		return codhabilidade;
	}
	public void setCodhabilidade(String codhabilidade) {
		this.codhabilidade = codhabilidade;
	}
	public String getCodtipo() {
		return codtipo;
	}
	public void setCodtipo(String codtipo) {
		this.codtipo = codtipo;
	}
	public ArrayList<String> getArrayTipo() {
		return arrayTipo;
	}
	public void setArrayTipo(ArrayList<String> arrayTipo) {
		this.arrayTipo = arrayTipo;
	}
	public ArrayList<String> getArrayFraqueza() {
		return arrayFraqueza;
	}
	public void setArrayFraqueza(ArrayList<String> arrayFraqueza) {
		this.arrayFraqueza = arrayFraqueza;
	}
	public ArrayList<String> getArrayHab() {
		return arrayHab;
	}
	public void setArrayHab(ArrayList<String> arrayHab) {
		this.arrayHab = arrayHab;
	}

	
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String[] getHabilidade() {
		return habilidade;
	}
	public void setHabilidade(String[] habilidade) {
		this.habilidade = habilidade;
	}
	public String[] getTipo() {
		return tipo;
	}
	public void setTipo(String[] tipo) {
		this.tipo = tipo;
	}
	public String[] getFraqueza() {
		return fraqueza;
	}
	public void setFraqueza(String[] fraqueza) {
		this.fraqueza = fraqueza;
	}
	public String getEvoluide() {
		return evoluide;
	}
	public void setEvoluide(String evoluide) {
		this.evoluide = evoluide;
	}
	

}
