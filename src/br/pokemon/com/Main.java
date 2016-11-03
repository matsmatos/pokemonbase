package br.pokemon.com;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

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
	public static HashMap<String, String> mapHabilidade = new HashMap();
	public static HashMap<String, String> mapCategoria = new HashMap();
	public static HashMap<String, String> mapSexo = new HashMap();

	// public static Connection getConnPokeBase() throws Exception {
	// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	// Connection conn = DriverManager
	// .getConnection("jdbc:sqlserver://localhost:1433;databaseName=Pokebase;integratedSecurity=true");
	// return conn;
	//
	// }
	//
	// public static Connection getConnPokedexInsert() throws Exception {
	// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	// Connection conn = DriverManager
	// .getConnection("jdbc:sqlserver://localhost:1433;databaseName=pokedex;integratedSecurity=true");
	// return conn;
	//
	// }

	public static Connection getConnPokeBase() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:sqlserver://10.135.0.53\\sqledutsi;" + "user=aluno;" + "password=aluno;" + "database=pokebase");
		return conn;

	}

	public static Connection getConnPokedexInsert() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:sqlserver://10.135.0.53\\sqledutsi;" + "user=aluno;" + "password=aluno;" + "database=pokedex");
		return conn;

	}

	public static void extractColumn(String[] column, HashMap<String, String> mapAtribu) {
		for (int i = 0; i < column.length; i++) {
			String valor = column[i].trim().replace("[", "").replace("]", "");
			if (mapAtribu.get(column[i]) != valor)
				mapAtribu.put(valor, valor);
		}
	}

	public static void extractColumn(String column, HashMap<String, String> mapAtribu) {
		mapAtribu.put(column.trim(), column.trim());

	}

	public static void extractJson() {
		// JSON

		System.out.println("Lendo Arquivo JSON");

		try {
			ObjectMapper mapper = new ObjectMapper();
			Pokemon[] obj = mapper.readValue(new File("./pokemon.json"), Pokemon[].class);
			for (Pokemon pokemon : obj) {
				Pokemon poke = new Pokemon();
				poke.setNumero(pokemon.getNumero().replace(".0", "").trim());
				poke.setNome(pokemon.getNome().trim());
				poke.setDescricao(pokemon.getDescricao().trim());
				if (pokemon.getAltura() == null) {
					poke.setAltura("0.0");
				} else {
					poke.setAltura(pokemon.getAltura().replace("m", "").replace(",", ".").trim());
				}
				poke.setPeso(pokemon.getPeso().replace("Kg", "").replace(",", ".").trim());
				poke.setSexo(pokemon.getSexo().trim());
				poke.setCategoria(pokemon.getCategoria().trim());
				poke.setHabilidade(pokemon.getHabilidade());
				poke.setTipo(pokemon.getTipo());
				poke.setFraqueza(pokemon.getFraqueza());
				String auxTp = Arrays.toString(poke.getTipo());
				String auxTipo[] = auxTp.split(",");
				String auxFra = Arrays.toString(poke.getFraqueza());
				String auxFraq[] = auxFra.split(",");
				String auxHab = Arrays.toString(poke.getHabilidade());
				String auxHb[] = auxHab.split(",");
				extractColumn(auxTipo, mapTipo);
				extractColumn(auxFraq, mapFraqueza);
				extractColumn(auxHb, mapHabilidade);
				extractColumn(poke.getSexo(), mapSexo);
				extractColumn(poke.getCategoria(), mapCategoria);
				pokemonLista.add(poke);
				pokemonGeral.add(poke);
			}

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void extractXML() {
		// exemplo de leitura de XML
		System.out.println("Lendo Arquivo XML");
		try {
			ObjectMapper xmlMapper = new XmlMapper();
			Pokemon[] obj = xmlMapper.readValue(new File("./pokemon.xml"), Pokemon[].class);
			for (Pokemon pokemon : obj) {
				Pokemon poke = new Pokemon();
				poke.setNumero(pokemon.getNumero().replace(".0", "").trim());
				poke.setNome(pokemon.getNome().trim());
				poke.setDescricao(pokemon.getDescricao().trim());
				if (pokemon.getAltura() == null) {
					poke.setAltura("0.0");
				} else {
					poke.setAltura(pokemon.getAltura().replace("m", "").replace(",", ".").trim());
				}
				poke.setPeso(pokemon.getPeso().replace("Kg", "").replace(",", ".").trim());
				poke.setSexo(pokemon.getSexo().trim());
				poke.setCategoria(pokemon.getCategoria().trim());
				poke.setHabilidade(pokemon.getHabilidade());
				poke.setTipo(pokemon.getTipo());
				poke.setFraqueza(pokemon.getFraqueza());
				String auxTp = Arrays.toString(poke.getTipo());
				String auxTipo[] = auxTp.split(",");
				String auxFra = Arrays.toString(poke.getFraqueza());
				String auxFraq[] = auxFra.split(",");
				String auxHab = Arrays.toString(poke.getHabilidade());
				String auxHb[] = auxHab.split(",");
				extractColumn(auxTipo, mapTipo);
				extractColumn(auxFraq, mapFraqueza);
				extractColumn(auxHb, mapHabilidade);
				extractColumn(poke.getSexo(), mapSexo);
				extractColumn(poke.getCategoria(), mapCategoria);
				extractColumn(poke.getCategoria(), mapCategoria);
				pokemonListaXML.add(poke);
				pokemonGeral.add(poke);
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
		System.out.println("Lendo CSV");
		try {
			CsvMapper mapper = new CsvMapper();
			mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
			CsvSchema schema = CsvSchema.emptySchema().withColumnSeparator(';').withHeader().withoutQuoteChar();
			File csvFile = new File("./pokebase.csv");
			MappingIterator<Pokemon> it = mapper.readerFor(Pokemon.class).with(schema).readValues(csvFile);
			while (it.hasNext()) {
				Pokemon csv = it.next();
				Pokemon poke = new Pokemon();
				poke.setNumero(csv.getNumero().replace(".0", "").trim());
				poke.setNome(csv.getNome().trim());
				poke.setDescricao(csv.getDescricao().trim());
				poke.setEvoluide(csv.getEvoluide().replace(".0", "").trim());
				if (csv.getAltura() == null) {
					poke.setAltura("0.0");
				} else {
					poke.setAltura(csv.getAltura().replace("m", "").replace(",", ".").trim());
				}
				poke.setPeso(csv.getPeso().replace("Kg", "").replace(",", ".").trim());
				poke.setSexo(csv.getSexo().trim());
				poke.setCategoria(csv.getCategoria().trim());
				poke.setHabilidade(csv.getHabilidade());
				poke.setTipo(csv.getTipo());
				poke.setFraqueza(csv.getFraqueza());
				String auxTp = Arrays.toString(poke.getTipo());
				String auxTipo[] = auxTp.split(",");
				String auxFra = Arrays.toString(poke.getFraqueza());
				String auxFraq[] = auxFra.split(",");
				String auxHab = Arrays.toString(poke.getHabilidade());
				String auxHb[] = auxHab.split(",");
				extractColumn(auxTipo, mapTipo);
				extractColumn(auxFraq, mapFraqueza);
				extractColumn(auxHb, mapHabilidade);
				extractColumn(poke.getSexo(), mapSexo);
				extractColumn(poke.getCategoria(), mapCategoria);
				extractColumn(poke.getCategoria(), mapCategoria);
				pokemonListaCSV.add(poke);
				pokemonGeral.add(poke);
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void extractPokebase(Connection connection, ArrayList<Pokemon> pokemon) throws Exception {
		System.out.println("Lendo POKEBASE");
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
				pok.setEvoluide(rs.getString(11).replace(".0", "").trim());
			}
			pokemonListaSQL.add(pok);
			pokemonGeral.add(pok);
			String auxTp = Arrays.toString(pok.getTipo());
			String auxTipo[] = auxTp.split(",");
			String auxFra = Arrays.toString(pok.getFraqueza());
			String auxFraq[] = auxFra.split(",");
			String auxHab = Arrays.toString(pok.getHabilidade());
			String auxHb[] = auxHab.split(",");
			extractColumn(auxTipo, mapTipo);
			extractColumn(auxFraq, mapFraqueza);
			extractColumn(auxHb, mapHabilidade);
			extractColumn(pok.getSexo(), mapSexo);
			extractColumn(pok.getCategoria(), mapCategoria);
			extractColumn(pok.getCategoria(), mapCategoria);
		}
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
			ps.setString(4, pokemonGeral.get(i).getDescricao().trim());
			ps.setDouble(5, Double.parseDouble(pokemonGeral.get(i).getAltura()));
			ps.setDouble(6, Double.parseDouble(
					pokemonGeral.get(i).getPeso().replace("Kg", "").replace(",", ".").replace("kg", "").trim()));
			ps.setInt(7, getSexo(pokemonGeral.get(i).getSexo()));
			ps.setInt(8, getCategoria(pokemonGeral.get(i).getCategoria()));
			ps.execute();
			cont++;
		}
		c.close();

	}

	public static int getCodTipo(String parm) throws Exception {
		int ret = 0;
		Connection c = getConnPokedexInsert();
		String sql = "SELECT codtipo FROM  tipo where [nmTipo] = '" + parm.trim() + "'";
		PreparedStatement ps;
		ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			ret = rs.getInt("codtipo");
		}
		c.close();
		return ret;
	}

	public static int getCodPokemon(String parm) throws Exception {
		int ret = 0;
		Connection c = getConnPokedexInsert();
		String sql = "SELECT codPokemon FROM Pokemon  where [codPokemon] = " + parm;
		PreparedStatement ps;
		ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			ret = rs.getInt("codPokemon");
		}
		c.close();
		return ret;

	}

	public static int getCodHabilidade(String parm) throws Exception {
		int ret = 0;
		Connection c = getConnPokedexInsert();
		String sql = "SELECT codHabilidade FROM  Habilidade where nmHabilidade = '" + parm.trim() + "'";
		PreparedStatement ps;
		ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			ret = rs.getInt("codHabilidade");
		}
		c.close();
		return ret;
	}

	public static void loadTipoPokemon() throws Exception {
		Connection c = getConnPokedexInsert();
		String sql = "if not exists (select codPokemon,codtipo from TIPOPOKEMON where codPokemon= ? AND codtipo=? ) INSERT INTO TIPOPOKEMON(codPokemon,codtipo) values(?,?)";

		PreparedStatement ps;
		ps = c.prepareStatement(sql);

		for (Pokemon pokemon : pokemonGeral) {
			pokemon.getNumero();
			String aux0 = Arrays.toString(pokemon.getTipo());
			aux0 = aux0.replace("[", "").replace("]", "");
			String aux1[] = aux0.split(",");
			for (int i = 0; i < aux1.length; i++) {
				ps.setInt(1, Integer.parseInt(pokemon.getNumero()));
				ps.setInt(2, getCodTipo(aux1[i]));
				ps.setInt(3, Integer.parseInt(pokemon.getNumero()));
				ps.setInt(4, getCodTipo(aux1[i]));
				ps.execute();
			}
		}
		System.out.println("tipoPokemon OK !");
		c.close();
	}

	public static void loadHabPokemon() throws Exception {
		Connection c = getConnPokedexInsert();
		String sql = "if not exists (select codPokemon,[codHabilidade] from [HabilidadePokemon] where codPokemon= ? AND [codHabilidade]=? ) INSERT INTO HABILIDADEPOKEMON(codPokemon,[codHabilidade]) values(?,?)";

		PreparedStatement ps;
		ps = c.prepareStatement(sql);

		for (Pokemon pokemon : pokemonGeral) {
			pokemon.getNumero();
			String aux0 = Arrays.toString(pokemon.getHabilidade());
			aux0 = aux0.replace("[", "").replace("]", "");
			String aux1[] = aux0.split(",");
			for (int i = 0; i < aux1.length; i++) {
				ps.setInt(1, Integer.parseInt(pokemon.getNumero()));
				ps.setInt(2, getCodHabilidade(aux1[i]));
				ps.setInt(3, Integer.parseInt(pokemon.getNumero()));
				ps.setInt(4, getCodHabilidade(aux1[i]));
				ps.execute();
			}
		}
		System.out.println("HabilidadePokemon OK !");
		c.close();
	}

	public static void loadFraqueza() throws Exception {
		Connection c = getConnPokedexInsert();
		String sql = "if not exists (select codPokemon,[codTipo] from [Fraqueza] where codPokemon= ? AND [codTipo]=? ) INSERT INTO Fraqueza(codPokemon,[codTipo]) values(?,?)";

		PreparedStatement ps;
		ps = c.prepareStatement(sql);

		for (Pokemon pokemon : pokemonGeral) {
			pokemon.getNumero();
			String aux0 = Arrays.toString(pokemon.getTipo());
			aux0 = aux0.replace("[", "").replace("]", "");
			String aux1[] = aux0.split(",");
			for (int i = 0; i < aux1.length; i++) {
				ps.setInt(1, Integer.parseInt(pokemon.getNumero()));
				ps.setInt(2, getCodTipo(aux1[i]));
				ps.setInt(3, Integer.parseInt(pokemon.getNumero()));
				ps.setInt(4, getCodTipo(aux1[i]));
				ps.execute();
			}
		}
		System.out.println("Fraqueza OK !");
		c.close();
	}

	public static void main(String[] args) throws Exception {

		// EXTRACT
		extractCsv();
		extractJson();
		extractXML();
		extractPokebase(getConnPokeBase(), pokemonListaSQL);

		// LOAD TABELAS QUE SÃO FK
		LoadHab();
		LoadTipo();
		loadSexo();
		loadCat();
		// LOAD POKEMON
		loadPokemon();
		// LOAD TABELAS DE RELACIONAMENTO
		preencheEvoluiDe();
		loadTipoPokemon();
		loadHabPokemon();
		loadFraqueza();

		System.out.println("FIM !!!\n");
		System.out.println("Créditos \nMATHEUS MATOS _\\|/_ \nIGOR RUAN\nCAIO VINICIUS");

	}

}
