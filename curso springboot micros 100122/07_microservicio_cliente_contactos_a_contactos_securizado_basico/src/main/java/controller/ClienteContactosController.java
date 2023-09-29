package controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import model.Persona;

@RestController
public class ClienteContactosController {
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8080";

    @GetMapping(value = "/clientecontactos/{nombre}/{email}/{edad}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> altaPersona(@PathVariable("nombre") String nombre, @PathVariable("email") String email,
            @PathVariable("edad") int edad) {
        Persona persona = new Persona(nombre, email, edad);
//        try {
        template.postForLocation(url + "/contactos", persona);
//        } catch (HttpStatusCodeException e2) {
//            e2.getResponseBodyAsString();
//        } catch (RestClientException e) {
//            e.getMessage();
//        } catch (Exception e3) {
//            e3.printStackTrace();
//        }
        Persona[] personas = null;
        personas = template.getForObject(url + "/contactos", Persona[].class);
        return Arrays.asList(personas);
    }

    @GetMapping(value = "/clientecontactos/{edad1}/{edad2}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> buscarEdades(@PathVariable("edad1") int edad1, @PathVariable("edad2") int edad2) {
        Persona[] personas = template.getForObject(url + "/contactos", Persona[].class);
        return Arrays.stream(personas).filter(p -> p.getEdad() >= edad1 && p.getEdad() <= edad2)
                .collect(Collectors.toList());
    }

}
