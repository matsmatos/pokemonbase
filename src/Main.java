import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import br.pokemon.com.Pokemon;

public class Main {

	public static ArrayList<Pokemon> pokemonLista = new ArrayList<Pokemon>();
	public static ArrayList<Pokemon> pokemonListaXML = new ArrayList<Pokemon>();

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

			Pokemon[] obj = xmlMapper.readValue(new File("./pokemon.xml"), Pokemon[].class);
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

	public static void exibeDados(ArrayList<Pokemon> p) {
		for (int i = 0; i < p.size(); i++) {
			System.out.println("- Nome:       " + p.get(i).getNome());
			System.out.println("- Descrição:  " + p.get(i).getDescricao());
			System.out.println("- Categoria:  " + p.get(i).getCategoria());
			System.out.println("- Habilidade: ");
			for (int j = 0; j < p.get(i).getHabilidade().size(); j++) {
				System.out.println("  "+p.get(i).getHabilidade().get(j).trim());
			}
			System.out.println("- Tipo:       ");
			for (int j = 0; j < p.get(i).getTipo().size(); j++) {
				System.out.println("  "+p.get(i).getTipo().get(j).trim());
			}
			System.out.println("- Fraqueza:   ");
			for (int j = 0; j < p.get(i).getFraqueza().size(); j++) {
				System.out.println("  "+p.get(i).getFraqueza().get(j).trim());
			}
			System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
		}
	}

	public static void main(String[] args) {

	//	extractXML();
		extractJson();
		exibeDados(pokemonLista);
	}

}
