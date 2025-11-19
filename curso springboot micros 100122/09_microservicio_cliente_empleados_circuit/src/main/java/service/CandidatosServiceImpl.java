package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import model.Candidato;

@Service
public class CandidatosServiceImpl implements CandidatosService {
    @Autowired
    RestTemplate template;

    @Autowired
    CircuitBreakerFactory factory;

    private String url = "http://localhost:8080/";

    @Override
    public List<Candidato> candidatosPuesto(String puesto) {
        CircuitBreaker circuit = factory.create("circuit1");
        return circuit.run(() -> { // si circuito cerrado se llamaria a micro destino
            List<Candidato> candidatos = Arrays.asList(template.getForObject(url + "empleados", Candidato[].class));
            return candidatos.stream().filter(c -> c.getPuesto().equals(puesto)).collect(Collectors.toList());

        }, a -> new ArrayList<Candidato>());// si circuito abierto por fallos en micro destino, no llamariamos
        // devolviendo instantaneamente lista vacia
    }

}
