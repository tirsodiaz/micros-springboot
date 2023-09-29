package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import model.Pais;

@Service
public class PaisesServiceImpl implements PaisesService {
	String url = "https://restcountries.com/v2/all";
	@Autowired
	RestTemplate template;

	@Override
	public List<Pais> obtenerPaises() {
		String resultado = template.getForObject(url, String.class);
		ObjectMapper maper = new ObjectMapper();
		List<Pais> paises = new ArrayList<>();
		ArrayNode array;
		try {
			// obtenemos ArrayJson con los datos de la respuesta
			array = (ArrayNode) maper.readTree(resultado);
			for (Object ob : array) {
				// obtenemos el objeto Json y extraemos las propiedades que nos interesan
				ObjectNode objectNode = (ObjectNode) ob;
				paises.add(new Pais(objectNode.get("name").asText(),
									null, // objectNodeJson.get("capital").asText(),
									objectNode.get("population").asInt(),
									objectNode.get("flag").asText()
						)
				);

			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return paises;
	}

	@Override
	public List<Pais> filtrarPaises(String name) {
		// recupera los paises cuyo nombre contiene la combinacion de caracteres recibida
		return obtenerPaises().stream().filter(p -> p.getNombre().contains(name)).collect(Collectors.toList());
	}
}
