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
	public static ArrayList<Pokemon> pokemonGeral = new ArrayList<Pokemon>();

	public static HashMap<String, String> mapTipo = new HashMap();
	public static HashMap<String, String> mapFraqueza = new HashMap();
	public static HashMap<String, String> mapTipoPokemon = new HashMap();
	public static HashMap<String, String> mapHabilidade = new HashMap();
	public static HashMap<String, String> mapCategoria = new HashMap();
	public static HashMap<String, String> mapSexo = new HashMap();
	public static HashMap<String, String> espelhoBanco = new HashMap();

	public static Connection getConnPokeBase() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager
				.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Pokebase;integratedSecurity=true");
		System.out.println("POKEBASE");
		return conn;

	}

	public static Connection getConnPokedexInsert() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager
				.getConnection("jdbc:sqlserver://localhost:1433;databaseName=pokedex;integratedSecurity=true");
		// System.out.println("POKEDEX");
		return conn;

	}

	public static void extractColumn(String[] column, HashMap<String, String> mapAtribu) {
		for (int i = 0; i < column.length; i++) {
			String valor = column[i].trim();
			if (mapAtribu.get(column[i]) != valor)
				mapAtribu.put(column[i].trim(), column[i].trim());
		}
	}

	public static void extractColumn(String column, HashMap<String, String> mapAtribu) {
		mapAtribu.put(column, column);

	}

	public static void extractJson() {
		// JSON
		System.out.println("*************************");
		System.out.println("Lendo Arquivo JSON");
		System.out.println("*************************");

		try {
			ObjectMapper mapper = new ObjectMapper();
			Pokemon[] obj = mapper.readValue(new File("./pokemon.json"), Pokemon[].class);

			for (Pokemon pokemon : obj) {
				Pokemon poke = new Pokemon();
				poke.setNumero(pokemon.getNumero().replace(".0", ""));
				poke.setNome(pokemon.getNome());
				poke.setDescricao(pokemon.getDescricao());

				if (pokemon.getAltura() == null) {
					poke.setAltura("0.0");
				} else {
					poke.setAltura(pokemon.getAltura().replace("m", "").replace(",", ".").trim());
				}

				// poke.setAltura(pokemon.getAltura().replace("m",
				// "").replace(",", ".").trim());

				poke.setPeso(pokemon.getPeso().replace("Kg", "").replace(",", ".").trim());
				poke.setSexo(pokemon.getSexo());
				poke.setCategoria(pokemon.getCategoria());
				poke.setHabilidade(pokemon.getHabilidade());
				poke.setTipo(pokemon.getTipo());
				poke.setFraqueza(pokemon.getFraqueza());

				extractColumn(poke.getTipo(), mapTipo);
				extractColumn(poke.getFraqueza(), mapFraqueza);
				extractColumn(poke.getTipo(), mapTipoPokemon);
				extractColumn(poke.getHabilidade(), mapHabilidade);
				extractColumn(poke.getSexo(), mapSexo);
				extractColumn(poke.getCategoria(), mapCategoria);

				pokemonLista.add(poke);
				pokemonGeral.add(poke);

			}
			System.out.println(pokemonLista.size() + "Registro da lista JSON");
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
			Pokemon[] obj = xmlMapper.readValue(new File("./pokemon.xml"), Pokemon[].class);
			for (Pokemon pokemon : obj) {
				Pokemon poke = new Pokemon();
				poke.setNumero(pokemon.getNumero().replace(".0", ""));
				poke.setNome(pokemon.getNome());
				poke.setDescricao(pokemon.getDescricao());
				if (pokemon.getAltura() == null) {
					poke.setAltura("0.0");
				} else {
					poke.setAltura(pokemon.getAltura().replace("m", "").replace(",", ".").trim());
				}
				poke.setPeso(pokemon.getPeso().replace("Kg", "").replace(",", ".").trim());
				poke.setSexo(pokemon.getSexo());
				poke.setCategoria(pokemon.getCategoria());
				poke.setHabilidade(pokemon.getHabilidade());
				poke.setTipo(pokemon.getTipo());
				poke.setFraqueza(pokemon.getFraqueza());

				extractColumn(poke.getTipo(), mapTipo);
				extractColumn(poke.getFraqueza(), mapFraqueza);
				extractColumn(poke.getTipo(), mapTipoPokemon);
				extractColumn(poke.getHabilidade(), mapHabilidade);
				extractColumn(poke.getSexo(), mapSexo);
				extractColumn(poke.getCategoria(), mapCategoria);
				pokemonListaXML.add(poke);
				pokemonGeral.add(poke);
			}
			System.out.println(pokemonListaXML.size() + "Registros da lista xml");

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

			CsvSchema schema = CsvSchema.emptySchema().withColumnSeparator(';').withHeader().withoutQuoteChar();
			File csvFile = new File("./pokebase.csv");
			MappingIterator<Pokemon> it = mapper.readerFor(Pokemon.class).with(schema).readValues(csvFile);
			while (it.hasNext()) {
				Pokemon csv = it.next();
				Pokemon poke = new Pokemon();
				poke.setNumero(csv.getNumero().replace(".0", ""));
				poke.setNome(csv.getNome());
				poke.setDescricao(csv.getDescricao());
				poke.setEvoluide(csv.getEvoluide().replace(".0", ""));
				if (csv.getAltura() == null) {
					poke.setAltura("0.0");
				} else {
					poke.setAltura(csv.getAltura().replace("m", "").replace(",", ".").trim());
				}
				poke.setPeso(csv.getPeso().replace("Kg", "").replace(",", ".").trim());
				poke.setSexo(csv.getSexo());
				poke.setCategoria(csv.getCategoria());
				poke.setHabilidade(csv.getHabilidade());
				poke.setTipo(csv.getTipo());
				poke.setFraqueza(csv.getFraqueza());

				extractColumn(poke.getTipo(), mapTipo);
				extractColumn(poke.getFraqueza(), mapFraqueza);
				extractColumn(poke.getTipo(), mapTipoPokemon);
				extractColumn(poke.getHabilidade(), mapHabilidade);
				extractColumn(poke.getSexo(), mapSexo);
				extractColumn(poke.getCategoria(), mapCategoria);

				pokemonListaCSV.add(poke);
				pokemonGeral.add(poke);

			}
			System.out.println("registros da lista csv" + pokemonListaCSV.size());

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
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------------------");
		}
	}

	public static void extractPokebase(Connection connection, ArrayList<Pokemon> pokemon) throws Exception {
		PreparedStatement ps = connection.prepareStatement(
				"SELECT [Numero Pokedex], Nome, Descrição, Altura, Categoria, Peso, Habilidades, Sexo, Tipo, Fraquezas, [Evolui De] FROM PokeBase WHERE [Numero Pokedex] >= 20 AND [Numero Pokedex] < 30");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Pokemon pok = new Pokemon();
			pok.setNumero(rs.getString(1).replace(".0", ""));
			pok.setNome(rs.getString(2));
			pok.setDescricao(rs.getString(3));
			// pok.setAltura(rs.getString(4));

			if (rs.getString(4) == null) {
				pok.setAltura("0.0");
			} else {
				pok.setAltura(rs.getString(4).replace("m", "").replace(",", ".").trim());
			}

			pok.setCategoria(rs.getString(5));
			pok.setPeso(rs.getString(6).replace("Kg", "").replace(",", ".").trim());
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
			if (rs.getString(11) == null) {
				pok.setEvoluide(null);
			} else {
				pok.setEvoluide(rs.getString(11).replace(".0", ""));
			}
			pokemonListaSQL.add(pok);
			pokemonGeral.add(pok);

			extractColumn(pok.getCategoria(), mapCategoria);
			extractColumn(pok.getTipo(), mapTipo);
			extractColumn(pok.getFraqueza(), mapFraqueza);
			extractColumn(pok.getTipo(), mapTipoPokemon);
			extractColumn(pok.getHabilidade(), mapHabilidade);

		}
		System.out.println(pokemonListaSQL.size() + "Registros da lista");

		connection.close();
	}

	public static void loadCat() throws Exception {
		Connection c = getConnPokedexInsert();

		String sql = "if not exists (select nmCategoria from Categoria where nmCategoria= ?) INSERT INTO Categoria(nmCategoria) values(?)";
		PreparedStatement ps;
		Set<String> chaves = mapCategoria.keySet();
		ps = c.prepareStatement(sql);
		for (String cat : chaves) {

			ps.setString(1, cat);
			ps.setString(2, cat);
			ps.execute();
		}
		System.out.println("Categoria OK ");
		c.close();

	}

	public static void LoadHab() throws Exception {
		Connection c = getConnPokedexInsert();
		PreparedStatement ps;
		Set<String> chaves = mapHabilidade.keySet();
		String sql = "if not exists (select nmHabilidade from habilidade where nmHabilidade= ?) INSERT INTO habilidade(nmHabilidade) values(?)";
		ps = c.prepareStatement(sql);
		for (String hab : chaves) {

			ps.setString(1, hab);
			ps.setString(2, hab);
			ps.execute();
		}
		System.out.println("Habilidades OK");
		c.close();
	}

	public static void LoadTipo() throws Exception {
		Connection c = getConnPokedexInsert();
		String sql = "if not exists (select nmTipo from TIPO where nmTipo= ?) INSERT INTO TIPO(nmTipo) values(?)";
		PreparedStatement ps;
		Set<String> chaves = mapTipo.keySet();
		ps = c.prepareStatement(sql);
		for (String tipo : chaves) {

			ps.setString(1, tipo);
			ps.setString(2, tipo);
			ps.execute();
		}
		System.out.println("Tipos OK");
		c.close();
	}

	public static void loadSexo() throws Exception {
		Connection c = getConnPokedexInsert();
		String sql = "if not exists (select tipoSexo from SEXO where tipoSexo= ?) INSERT INTO SEXO(tipoSexo) values(?)";
		PreparedStatement ps;
		ps = c.prepareStatement(sql);
		Set<String> chaves = mapSexo.keySet();
		for (String sexo : chaves) {

			ps.setString(1, sexo);
			ps.setString(2, sexo);
			ps.execute();

		}
		System.out.println("Sexo OK");
		c.close();
	}

	public static int getSexo(String parm) throws Exception {
		Connection c = getConnPokedexInsert();
		String sql = "SELECT sexo from SEXO WHERE tipoSexo = ?";
		PreparedStatement ps;
		ps = c.prepareStatement(sql);
		ps.setString(1, parm);
		ResultSet rs = ps.executeQuery();
		int ret = 0;
		if (rs.next()) {
			ret = rs.getInt("sexo");
		}
		c.close();
		return ret;
	}

	public static int getCategoria(String parm) {
		try {
			Connection c = getConnPokedexInsert();
			String sql = "SELECT codCategoria from CATEGORIA WHERE nmCategoria = ?";
			PreparedStatement ps;
			ps = c.prepareStatement(sql);
			ps.setString(1, parm);
			ResultSet rs = ps.executeQuery();
			int ret = 0;
			if (rs.next()) {
				ret = rs.getInt("codCategoria");
			}
			c.close();
			return ret;

		} catch (Exception ex) {
			System.out.println(ex);

			return 0;
		}
	}

	public static void preencheEvoluiDe() {
		try {
			Connection c = getConnPokedexInsert();
			String sql = " UPDATE Pokemon SET evoluide = (Select top 1 codpokemon from pokemon where nome =?) where codpokemon = ?";
			PreparedStatement ps;
			ps = c.prepareStatement(sql);
			for (Pokemon pokemon : pokemonGeral) {

				ps.setInt(2, Integer.parseInt(pokemon.getNumero().replace(".0", "")));
				ps.setString(1, pokemon.getEvoluide());

				ps.execute();

			}
			c.close();
			System.out.println("EvoluiDe Alterado !!!");

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public static void loadPokemon() throws Exception {

		Connection c = getConnPokedexInsert();
		String sql = "if not exists (select codPokemon from Pokemon where codPokemon= ?) "
				+ "INSERT INTO Pokemon(codPokemon,nome,descricao,altura,peso,sexo,codCategoria) values(?,?,?,?,?,?,?)";
		PreparedStatement ps;
		// Set<String> chaves = mapSexo.keySet();
		int cont = 0;
		ps = c.prepareStatement(sql);

		for (int i = 0; i < pokemonGeral.size(); i++) {
			ps.setInt(1, Integer.parseInt(pokemonGeral.get(i).getNumero().replace(".0", "")));
			ps.setInt(2, Integer.parseInt(pokemonGeral.get(i).getNumero().replace(".0", "")));
			ps.setString(3, pokemonGeral.get(i).getNome().trim());
			ps.setString(4, pokemonGeral.get(i).getDescricao());
			ps.setDouble(5, Double.parseDouble(pokemonGeral.get(i).getAltura()));
			ps.setDouble(6, Double.parseDouble(
					pokemonGeral.get(i).getPeso().replace("Kg", "").replace(",", ".").replace("kg", "").trim()));
			ps.setInt(7, getSexo(pokemonGeral.get(i).getSexo()));
			ps.setInt(8, getCategoria(pokemonGeral.get(i).getCategoria()));
			ps.execute();
			cont++;

		}

		c.close();
		System.out.println("Pokemon OK, " + cont + " inseridos");

	}

	public static void main(String[] args) throws Exception {

		// EXTRACT
		extractCsv();
		extractJson();
		extractXML();
		extractPokebase(getConnPokeBase(), pokemonListaSQL);
		//
		// // LOAD TABELAS QUE SÃO FK
		LoadHab();
		LoadTipo();
		loadSexo();
		loadCat();

		loadPokemon();
		preencheEvoluiDe();
//		for (Pokemon string : pokemonGeral) {
//			System.out.println(string.getNome() + " " + string.getNumero() + "-" + string.getEvoluide());
//		}

		System.out.println(pokemonGeral.size());

	}

}
