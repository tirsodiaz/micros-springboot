package service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Async // Fundamental para asincrono, si se quita funciona sincrono
    public CompletableFuture<List<Persona>> altaConsultarAsync(Persona persona) {
        template.postForLocation(url + "/contactos", persona);
        String postForObject = template.postForObject(url + "/contactos", persona, String.class);
        Persona[] personas = template.getForObject(url + "/contactos", Persona[].class);
        ResponseEntity<Persona[]> rPersonas = template.getForEntity(url + "/contactosresponseentity", Persona[].class);
        return CompletableFuture.completedFuture(Arrays.asList(personas));  //rPersonas.getBody();
    }

    @Override
    public List<Persona> consultarContactos() {
        Persona[] personas = template.getForObject(url + "/contactos", Persona[].class);
        return Arrays.asList(personas);
    }

    @Override
    public List<Persona> filtrarEdades(int edad1, int edad2) {
        Persona[] personas = template.getForObject(url + "/contactos", Persona[].class);
        return Arrays.stream(personas).filter(p -> p.getEdad() >= edad1 && p.getEdad() <= edad2)
                .collect(Collectors.toList());
    }

}
