package br.pokemon.com;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//import Principal.Pokemon_Principal;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Main {

	public static ArrayList<Pokemon> pokemonLista = new ArrayList<Pokemon>();
	public static ArrayList<Pokemon> pokemonListaXML = new ArrayList<Pokemon>();
	public static ArrayList<Pokemon> pokemonListaCSV = new ArrayList<Pokemon>();
	public static ArrayList<Pokemon> pokemonListaSQL = new ArrayList<Pokemon>();

	public static Connection getConnPokeBase() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager
				.getConnection("jdbc:sqlserver://10.135.0.53\\sqledutsi;"
						+ "user=aluno;" + "password=aluno;"
						+ "database=pokebase");
		return conn;

	}

	public static void extractJson() {
		// JSON
		System.out.println("*************************");
		System.out.println("Lendo Arquivo JSON");
		System.out.println("*************************");

		try {
			ObjectMapper mapper = new ObjectMapper();
			Pokemon[] obj = mapper.readValue(new File("./pokemon.json"),
					Pokemon[].class);

			for (Pokemon pokemon : obj) {
				Pokemon poke = new Pokemon();
				poke.setNumero(pokemon.getNumero());
				poke.setNome(pokemon.getNome());
				poke.setDescricao(pokemon.getDescricao());
				poke.setAltura(pokemon.getAltura());
				poke.setPeso(pokemon.getPeso());
				poke.setSexo(pokemon.getSexo());
				poke.setCategoria(pokemon.getCategoria());
				poke.setHabilidade(pokemon.getHabilidade());
				poke.setTipo(pokemon.getTipo());
				poke.setFraqueza(pokemon.getFraqueza());
				pokemonLista.add(poke);
			}
		} catch (

		JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("*************************\n");

	}

	public static void extractXML() {
		// exemplo de leitura de XML
		System.out.println("Lendo Arquivo XML");

		try {
			ObjectMapper xmlMapper = new XmlMapper();

			Pokemon[] obj = xmlMapper.readValue(new File("./pokemon.xml"),
					Pokemon[].class);
			for (Pokemon pokemon : obj) {
				Pokemon poke = new Pokemon();
				poke.setNumero(pokemon.getNumero());
				poke.setNome(pokemon.getNome());
				poke.setDescricao(pokemon.getDescricao());
				poke.setAltura(pokemon.getAltura());
				poke.setPeso(pokemon.getPeso());
				poke.setSexo(pokemon.getSexo());
				poke.setCategoria(pokemon.getCategoria());
				poke.setHabilidade(pokemon.getHabilidade());
				poke.setTipo(pokemon.getTipo());
				poke.setFraqueza(pokemon.getFraqueza());
				pokemonListaXML.add(poke);

			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void extractCsv() {
		try {

			CsvMapper mapper = new CsvMapper();
			mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);

			CsvSchema schema = CsvSchema.emptySchema().withColumnSeparator(';')
					.withHeader().withoutQuoteChar();
			File csvFile = new File("./pokebase.csv");
			MappingIterator<Pokemon> it = mapper.readerFor(Pokemon.class)
					.with(schema).readValues(csvFile);
			while (it.hasNext()) {
				Pokemon csv = it.next();
				Pokemon poke = new Pokemon();
				poke.setNumero(csv.getNumero());
				poke.setNome(csv.getNome());
				poke.setDescricao(csv.getDescricao());
				poke.setEvoluide(csv.getEvoluide());
				poke.setAltura(csv.getAltura());
				poke.setPeso(csv.getPeso());
				poke.setSexo(csv.getSexo());
				poke.setCategoria(csv.getCategoria());
				poke.setHabilidade(csv.getHabilidade());
				poke.setTipo(csv.getTipo());
				poke.setFraqueza(csv.getFraqueza());
				pokemonListaCSV.add(poke);

			}

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void exibeDados(ArrayList<Pokemon> p) {
		for (int i = 0; i < p.size(); i++) {
			System.out.println("- Nome:       " + p.get(i).getNome());
			System.out.println("- Descrição:  " + p.get(i).getDescricao());
			System.out.println("- Categoria:  " + p.get(i).getCategoria());
			System.out.println("- Evolui de:  " + p.get(i).getEvoluide());
			System.out.println("- Habilidade: ");
			for (int j = 0; j < p.get(i).getHabilidade().size(); j++) {
				System.out.println("  "
						+ p.get(i).getHabilidade().get(j).trim());
			}
			System.out.println("- Tipo:       ");
			for (int j = 0; j < p.get(i).getTipo().size(); j++) {
				System.out.println("  " + p.get(i).getTipo().get(j).trim());
			}
			System.out.println("- Fraqueza:   ");
			for (int j = 0; j < p.get(i).getFraqueza().size(); j++) {
				System.out.println("  " + p.get(i).getFraqueza().get(j).trim());
			}
			System.out
					.println("--------------------------------------------------------------------------------------------------------------------------------------");
		}
	}

	public static void extractPokebase(Connection connection,
			ArrayList<Pokemon> pokemon) throws Exception {

		PreparedStatement ps = connection
				.prepareStatement("SELECT [Numero Pokedex], Nome, Descrição, Altura, Categoria, Peso, Habilidades, Sexo, Tipo, Fraquezas, [Evolui De] FROM PokeBase WHERE [Numero Pokedex] >= 20 AND [Numero Pokedex] < 30");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			
			
			

			Pokemon pok = new Pokemon();
			pok.setNumero(rs.getString(1));
			pok.setNome(rs.getString(2));
			pok.setDescricao(rs.getString(3));
			pok.setAltura(rs.getString(4));
			pok.setCategoria(rs.getString(5));
			pok.setPeso(rs.getString(6));

			String habilidades = rs.getString(7);
			if (habilidades != null) {
				String[] habilidadeSplite = habilidades.split(",");
				for (int i = 0; i < habilidadeSplite.length; i++) {
					pok.getHabilidade().add(habilidadeSplite[i]);
				}

			}

			String tipo = rs.getString(9);
			if (tipo != null) {
				String[] tipoSplite = habilidades.split(",");
				for (int i = 0; i < tipoSplite.length; i++) {
					pok.getHabilidade().add(tipoSplite[i]);
				}

			}

			String fraqueza = rs.getString(10);
			if (tipo != null) {
				String[] fraquezaSplite = habilidades.split(",");
				for (int i = 0; i < fraquezaSplite.length; i++) {
					pok.getHabilidade().add(fraquezaSplite[i]);
				}

			}

			pok.setSexo(rs.getString(8));
			pok.setEvoluide(rs.getString(11));
			pokemonListaSQL.add(pok);
			//pokemon.add(pok);
			// System.out.println("-------");
			System.out.println(pok.getNumero() + "- nome: " + pok.getNome() + "-"
					+ pok.getHabilidade());

		}

		connection.close();
	}

	public static void main(String[] args) {

		// extractXML();
		// extractJson();
		
		try {
			extractPokebase(getConnPokeBase(),pokemonListaSQL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		exibeDados(pokemonListaSQL);
		//exibeDados(pokemonListaSQL);

	}

}
