package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
@ComponentScan(basePackages = "controller")
@SpringBootApplication
public class MicroservicioClienteFichaProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioClienteFichaProductoApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate template() {
		return new RestTemplate();
	}

}
