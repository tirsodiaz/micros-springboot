package service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import model.Persona;

@Service
public class ClienteContactosServiceImpl implements ClienteContactosService {

    @Autowired
    RestTemplate restTemplate;

    String url = "http://localhost:8080";

    @Override   
    public ResponseEntity<?> altaConsultar(Persona persona) {
   
    	Persona[] personas = null;
    	try {
    	 // String postForObject = template.postForObject(url+"/contactos", persona, String.class);
	        restTemplate.postForLocation(url + "/contactos", persona);
	     // ResponseEntity<String> repfe = template.postForEntity(url + "/contactos", persona, String.class);
	        personas = restTemplate.getForObject(url + "/contactos", Persona[].class);
	        ResponseEntity<Persona[]> regfe = restTemplate.getForEntity(url + "/contactos",Persona[].class);
	        //return regfe;
	        personas = regfe.getBody();
	        return ResponseEntity.status(regfe.getStatusCode())
	        		.body(Arrays.asList(personas));
    	} catch (HttpClientErrorException ex) {
            return ResponseEntity.status(ex.getStatusCode())
            		.body(ex.getResponseBodyAsString());
    	} catch (ResourceAccessException e) {
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    	}        
        
    }
    @Override
    //public ResponseEntity<String> agregarContacto(Persona persona) throws Exception {	
    public ResponseEntity<?> agregarContacto(Persona persona) {

    	ResponseEntity<String> postForEntity = null;
    	
    	try {
    		postForEntity = restTemplate.postForEntity(url + "/contactos", persona, String.class);
    		return postForEntity; //return ResponseEntity.created("Contacto creado");
    	} catch (HttpClientErrorException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBodyAsString());
        } catch (ResourceAccessException e) {
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    	}
    }
    
    public ResponseEntity<?> altaUsuario(Persona usuario) {
        try {
            ResponseEntity<Persona> respuesta = restTemplate.postForEntity(url+ "/usuarios/", usuario, Persona.class);
            return ResponseEntity.status(respuesta.getStatusCode()).body(respuesta.getBody());
        } catch (HttpClientErrorException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBodyAsString());
        } catch (ResourceAccessException e) {
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    	}
    }

    @Override
    public ResponseEntity<?> modificarUsuario(Persona usuario) {
        try {
            restTemplate.put(url+ "/usuarios/", usuario);
            return ResponseEntity.ok("Usuario modificado");
        } catch (HttpClientErrorException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBodyAsString());
        } catch (ResourceAccessException e) {
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    	}
    }

    @Override
    public ResponseEntity<?> bajaUsuario(Long id) {
        try {
            restTemplate.delete(url+ "/usuarios/" + "/" + id);
            return ResponseEntity.noContent().build();
        } catch (HttpClientErrorException ex) {
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBodyAsString());
        } catch (ResourceAccessException e) {
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    	}
    }
    
    
    @Override
    public void agregarContactoo(Persona persona) {
        restTemplate.postForLocation(url + "/contactos", persona);       
    }
    
    @Override
    public List<Persona> recuperarContactos() {
        Persona[] personas = restTemplate.getForObject(url + "/contactos", Persona[].class);
        return Arrays.asList(personas);
    }

    @Override
    public Persona buscarContacto(String id) {
        Persona persona = restTemplate.getForObject(url + "/contactos/" + id, Persona.class);
        return persona;
    }
    
    

    @Override
    public void actualizarContacto(Persona persona) {
        restTemplate.put(url + "/contactos", persona);
    }

    @Override
    public ResponseEntity<?> eliminarContacto(int id) {
    	try {
	        restTemplate.delete(url + "/contactos/" + id);
	        return ResponseEntity.noContent().build();
	    } catch (HttpClientErrorException ex) {
	        return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBodyAsString());
	    } catch (ResourceAccessException e) {
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
   

    @Override
    public List<Persona> filtrarEdadess(int edad1, int edad2) {
        Persona[] personas = restTemplate.getForObject(url + "/contactoss", Persona[].class);
        return Arrays.stream(personas).filter(p -> p.getEdad() >= edad1 && p.getEdad() <= edad2)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<List<Persona>> filtrarEdades(int edad1, int edad2) {
        ResponseEntity<Persona[]> personas = restTemplate.getForEntity(url + "/contactos", Persona[].class);
        //Al no capturar excepción RunTimeException se mantiene la misma
        List<Persona> listaPersonas = Arrays.stream(personas.getBody())
                .filter(p -> p.getEdad() >= edad1 && p.getEdad() <= edad2).collect(Collectors.toList());

        HttpHeaders httpHeaders = new HttpHeaders(); // necesario porque headers es readonly, no puede usarse .add()
        httpHeaders.add("totalContactos", personas.getHeaders().get("totalContactos").get(0));
        httpHeaders.add("totalEdades", String.valueOf(listaPersonas.size()));
        return new ResponseEntity<List<Persona>>(listaPersonas, httpHeaders, personas.getStatusCode());
    }    

}
