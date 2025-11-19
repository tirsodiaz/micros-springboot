package controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Reserva;
import service.ReservasService;

//@CrossOrigin(origins = "*") //Comentar si Gateway ALLOWED_ORIGIN = "*"; //Descomentar si zuul http://localhost:7000/sreservas/reservas o acceso desde .html http://localhost:10000/reservas
@RestController
public class ReservasController {
    @Autowired
    ReservasService service;

    @PostMapping(value = "reserva/{personas}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void generarReserva(@RequestBody Reserva reserva, @PathVariable("personas") int personas) {
        this.service.realizarReserva(reserva, personas);
    }

    @GetMapping(value = "reservas",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reserva> getReservas() {
        return this.service.getReservas();
    }

    @GetMapping(value = "reservas2",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reserva> getReservas2() {
        return Arrays.asList(new Reserva(1, "1", 1, "xxx", 1));
    }
}
