package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "controller")
@SpringBootApplication
public class MicroservicioFichaProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioFichaProductoApplication.class, args);
	}

}
