package controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.FichaProducto;

@RestController
public class ProductosController {
    @Value("${eureka.instance.instance-id}")
    String id;

    @GetMapping(value = "ficha/{producto}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FichaProducto producto(@PathVariable("producto") String nombre) {
        System.out.println("Instancia en ejecucion: " + id + " procesando " + nombre);
        return new FichaProducto(nombre, (int) (Math.random() * 5000 + 1));
    }
}
