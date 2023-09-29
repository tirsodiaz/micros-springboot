package service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import model.Persona;

@Service
public class ClienteContactosServiceImpl implements ClienteContactosService {

    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080";

    @Override
    @Async // Fundamental para asincrono, si se quita funciona sincrono
    public CompletableFuture<List<Persona>> AltaConsultarAsync(Persona persona) {
//		String postForObject = template.postForObject(url+"/contactos", persona, String.class);
        template.postForLocation(url + "/contactos", persona);
        // ResponseEntity<String> repfe = template.postForEntity(url + "/contactos",
        // persona, String.class);
        Persona[] personas = template.getForObject(url + "/contactos", Persona[].class);
        // ResponseEntity<Persona[]> regfe = template.getForEntity(url + "/contactos",
        // Persona[].class);
        return CompletableFuture.completedFuture(Arrays.asList(personas));
    }

    @Override
    public List<Persona> recuperarContactos() {
        Persona[] personas = template.getForObject(url + "/contactos", Persona[].class);
        return Arrays.asList(personas);
    }

    @Override
    public Persona buscarContacto(String id) {
        Persona persona = template.getForObject(url + "/contactos/" + id, Persona.class);
        return persona;
    }

    @Override
    public void agregarContacto(Persona persona) {
        template.postForLocation(url + "/contactos", persona);
    }

    @Override
    public void actualizarContacto(Persona persona) {
        template.put(url + "/contactos", persona);
    }

    @Override
    public void eliminarContacto(int id) {
        template.delete(url + "/contactos/" + id);

    }

    @Override
    public List<Persona> filtrarEdades(int edad1, int edad2) {
        Persona[] personas = template.getForObject(url + "/contactos", Persona[].class);
        return Arrays.stream(personas).filter(p -> p.getEdad() >= edad1 && p.getEdad() <= edad2)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<List<Persona>> filtrarEdadesWithResponseEntity(int edad1, int edad2) {
        ResponseEntity<Persona[]> personas = template.getForEntity(url + "/contactosresponseentity", Persona[].class);
        List<Persona> listaPersonas = Arrays.stream(personas.getBody())
                .filter(p -> p.getEdad() >= edad1 && p.getEdad() <= edad2).collect(Collectors.toList());

        HttpHeaders httpHeaders = new HttpHeaders(); // necesario porque headers es readonly, no puede usarse .add()
        httpHeaders.add("totalContactos", personas.getHeaders().get("totalContactos").get(0));
        httpHeaders.add("totalEdades", String.valueOf(listaPersonas.size()));
        return new ResponseEntity<List<Persona>>(listaPersonas, httpHeaders, personas.getStatusCode());
    }

}
