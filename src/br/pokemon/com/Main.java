package br.pokemon.com;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.HashMap;
//import Principal.Pokemon_Principal;
import java.util.Map;
import java.util.Set;

public class Main {

	public static ArrayList<Pokemon> pokemonLista = new ArrayList<Pokemon>();
	public static ArrayList<Pokemon> pokemonListaXML = new ArrayList<Pokemon>();
	public static ArrayList<Pokemon> pokemonListaCSV = new ArrayList<Pokemon>();
	public static ArrayList<Pokemon> pokemonListaSQL = new ArrayList<Pokemon>();
	public static HashMap<String, String> mapTipo = new HashMap();
	public static HashMap<String, String> mapFraqueza = new HashMap();
	public static HashMap<String, String> mapTipoPokemon = new HashMap();
	public static HashMap<String, String> mapHabilidadePokemon = new HashMap();
	public static HashMap<String, String> mapHabilidade = new HashMap();
	public static HashMap<String, String> mapCategoria = new HashMap();
	public static HashMap<String, String> mapSexo = new HashMap();

	public static Connection getConnPokeBase() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager
				.getConnection("jdbc:sqlserver://10.135.0.53\\sqledutsi;"
						+ "user=aluno;" + "password=aluno;"
						+ "database=pokebase");
		return conn;

	}

	public static Connection getConnPokedexInsert() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager
				.getConnection("jdbc:sqlserver://10.135.0.53\\sqledutsi;"
						+ "user=aluno;" + "password=aluno;"
						+ "database=pokedex");
		return conn;

	}

	public static void extractHabDesc(String[] column, String str,
			HashMap<String, String> mapAtribu) {

		for (int i = 0; i < column.length; i++) {
			//

			String valor = column[i].trim();
			if (mapAtribu.get(column[i]) != valor)
				mapAtribu.put(column[i].trim(), column[i].trim());
			mapAtribu.put(column[i].trim(), column[i].trim());

		}

	}

	public static void extractColumn(String[] column,
			HashMap<String, String> mapAtribu) {

		for (int i = 0; i < column.length; i++) {
			//

			String valor = column[i].trim();
			if (mapAtribu.get(column[i]) != valor)
				mapAtribu.put(column[i].trim(), column[i].trim());

		}

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

				extractColumn(poke.getTipo(), mapTipo);
				extractColumn(poke.getFraqueza(), mapFraqueza);
				extractColumn(poke.getTipo(), mapTipoPokemon);
				extractColumn(poke.getHabilidade(), mapHabilidade);
				// extractColumn(poke.getCategoria(), mapCategoria);

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

				extractColumn(poke.getTipo(), mapTipo);
				extractColumn(poke.getFraqueza(), mapFraqueza);
				extractColumn(poke.getTipo(), mapTipoPokemon);
				extractColumn(poke.getHabilidade(), mapHabilidade);
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

				extractColumn(poke.getTipo(), mapTipo);
				extractColumn(poke.getFraqueza(), mapFraqueza);
				extractColumn(poke.getTipo(), mapTipoPokemon);
				extractColumn(poke.getHabilidade(), mapHabilidade);
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
			System.out.println("- altura:  " + p.get(i).getAltura());
			System.out.println("- Peso:  " + p.get(i).getPeso());

			System.out.println("- Habilidade: ");

			for (int j = 0; j < p.get(i).getHabilidade().length; j++) {

				System.out.println(p.get(i).getHabilidade()[j].trim());
			}
			System.out.println("- Tipo:       ");
			for (int j = 0; j < p.get(i).getTipo().length; j++) {
				System.out.println("  " + p.get(i).getTipo()[j].trim());
			}
			System.out.println("- Fraqueza:   ");
			for (int j = 0; j < p.get(i).getFraqueza().length; j++) {
				System.out.println("  " + p.get(i).getFraqueza()[j].trim());
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
			String[] habilidadeSplite = habilidades.split(",");
			pok.setHabilidade(habilidadeSplite);
			String fraquezas = rs.getString(10);
			String[] fraquezaSplite = fraquezas.split(",");
			pok.setFraqueza(fraquezaSplite);

			String tipo = rs.getString(9);
			String[] tp = tipo.split(",");
			pok.setTipo(tp);

			pok.setSexo(rs.getString(8));
			pok.setEvoluide(rs.getString(11));
			pokemonListaSQL.add(pok);

			extractColumn(pok.getTipo(), mapTipo);
			extractColumn(pok.getFraqueza(), mapFraqueza);
			extractColumn(pok.getTipo(), mapTipoPokemon);
			extractColumn(pok.getHabilidade(), mapHabilidade);
		}
		connection.close();
	}

	public static void Load3() throws Exception {
		Connection c = getConnPokedexInsert();

		PreparedStatement ps = c
				.prepareStatement("INSERT INTO habilidade(nmFraqueza) values(?)");
		Set<String> chaves = mapFraqueza.keySet();
		for (String fraq : chaves) {
			ps.setString(1, fraq);
			ps.execute();
		}
	}

	public static void Load2() throws Exception {
		Connection c = getConnPokedexInsert();

		PreparedStatement ps = c
				.prepareStatement("INSERT INTO habilidade(nmHabilidade) values(?)");
		Set<String> chaves = mapHabilidade.keySet();
		for (String hab : chaves) {
			ps.setString(1, hab);
			ps.execute();
		}
	}

	public static void Load() throws Exception {
		Connection c = getConnPokedexInsert();

		PreparedStatement ps = c
				.prepareStatement("INSERT INTO TIPO(nmTipo) values(?)");
		Set<String> chaves = mapTipo.keySet();
		for (String tipo : chaves) {
			ps.setString(1, tipo);
			ps.execute();
		}

	}

	public static void main(String[] args) {

		extractXML();
		extractJson();

		try {
			extractPokebase(getConnPokeBase(), pokemonListaSQL);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Load2();
			System.out.println("piei");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// exibeDados(pokemonListaSQL);

	}

}
