package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"controller","service","inicio"})
public class MicroservicioClienteEmpleadosCircuitApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioClienteEmpleadosCircuitApplication.class, args);
	}
	
	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}

}
