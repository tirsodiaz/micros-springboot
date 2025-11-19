package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"controller","service"})
public class MicroservicioEmpleadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioEmpleadosApplication.class, args);
	}

}
