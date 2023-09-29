package com.boot.ejemplo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {
    // http://localhost:4000/myservice/saludo
    @GetMapping(value = "saludo", produces = MediaType.TEXT_PLAIN_VALUE)
    public String saludo() {
        return "Microservicio Spring boot";
    }

    // http://localhost:4000/myservice/saludo/Tirso
    @GetMapping(value = "saludo/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String saludo(@PathVariable("name") String n) {
        return "Bienvenido Sr/a " + n;
    }

}
