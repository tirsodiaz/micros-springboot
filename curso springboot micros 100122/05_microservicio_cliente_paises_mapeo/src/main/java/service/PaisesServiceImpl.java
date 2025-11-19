package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import model.NombrePais;
import model.Pais;

@Service
public class PaisesServiceImpl implements PaisesService {
	String url = "https://restcountries.com/v3/all?fields=name,capital,flag,population,area,languages,region";
	              
	@Autowired
	RestTemplate template;

	@Override
	public List<Pais> obtenerPaises() {
		
		ObjectMapper mapper = new ObjectMapper();			
		List<Pais> paises = new ArrayList<>();		
		try {
			//si json solo con lo que me interesa
			String json = "[{\"name\":{\"common\":\"Ireland\",\"official\":\"Republic of Ireland\"},\"capital\":[\"Dublin\"],\"languages\":{\"eng\":\"English\",\"gle\":\"Irish\"},\"area\":70273.0,\"flag\":\"🇮🇪\",\"population\":4994724}]";
			paises = mapper.readValue(json, new TypeReference<List<Pais>>() {});
			
			//si json con lo que me interesa y lo que no
			json = "[{\"name\":{\"common\":\"Ireland\",\"official\":\"Republic of Ireland\",\"nativeName\":{\"eng\":{\"official\":\"Republic of Ireland\",\"common\":\"Ireland\"},\"gle\":{\"official\":\"Poblacht nahÉ ireann\",\"common\":\"Éire\"}}},\"capital\":[\"Dublin\"],\"languages\":{\"eng\":\"English\",\"gle\":\"Irish\"},\"area\":70273.0,\"flag\":\"🇮🇪\",\"population\":4994724}]";
			json = template.getForObject(url, String.class);
			mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //evitamos usar en clase Pais @JsonIgnoreProperties(ignoreUnknown = true)
			paises.clear();
			paises = mapper.readValue(json, new TypeReference<List<Pais>>() {});
			
			paises.clear();
			mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			JsonNode root = mapper.readTree(json);   //ArrayNode arrayNode = (ArrayNode) maper.readTree(json); //ArrayNode es subclase de JsonNode
			for (JsonNode country : root) {          //for (JsonNode country : arrayNode) {
	            String common = country.path("name").path("common").asText();
	            String official = country.path("name").path("official").asText();
	            String capital = country.path("capital").path(0).asText(); // evitamos excepcion con path() si no hay capital o lista vacía, mucho mejor que get("capital").get(0)
	            List<String> capitalList = new ArrayList<String>();
	            capitalList.add(capital);
	            String flag = country.path("flag").asText();
	            long population = country.path("population").asLong();
	            double area = country.path("area").asDouble();
	            JsonNode languagesNode = country.path("languages");
	            Map<String, String> languagesMap = new HashMap<String, String>();	               
	            Iterator<Entry<String, JsonNode>> fields = languagesNode.fields();
	            while (fields.hasNext()) {  // Recorrer pares clave-valor     
	                Map.Entry<String, JsonNode> entry = fields.next();
	                String code = entry.getKey();          			// ej: "eng", "gle"
	                String language = entry.getValue().asText(); 	// ej: "English", "Irish"
	                languagesMap.put(code, language);
	            }            
	            paises.add(new Pais(new NombrePais(common, official), capitalList, languagesMap, area, flag, population));
			}
		
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return paises;
		} 
		
	@Override
	public List<Pais> filtrarPaises(String name) {
		// recupera los paises cuyo nombre contiene la combinacion de caracteres recibida
		return obtenerPaises().stream().filter(p -> p.getName().getCommon().contains(name)).collect(Collectors.toList());
	}
}
