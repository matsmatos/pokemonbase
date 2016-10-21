package br.pokemon.com;

import java.util.ArrayList;

public class Pokemon {
	String numero;
	String nome;
	String descricao;
	String altura;
	String peso;
	String sexo;
	String categoria;
	ArrayList<String> habilidade = new ArrayList<String>();
	ArrayList<String> tipo = new ArrayList<>();
	ArrayList<String> fraqueza = new ArrayList<>();
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
	public ArrayList<String> getHabilidade() {
		return habilidade;
	}
	public void setHabilidade(ArrayList<String> habilidade) {
		this.habilidade = habilidade;
	}
	public ArrayList<String> getTipo() {
		return tipo;
	}
	public void setTipo(ArrayList<String> tipo) {
		this.tipo = tipo;
	}
	public ArrayList<String> getFraqueza() {
		return fraqueza;
	}
	public void setFraqueza(ArrayList<String> fraqueza) {
		this.fraqueza = fraqueza;
	}
	

}
