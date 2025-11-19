package init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"controller","service"})
public class MicroservicioTutorialesChatgptApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioTutorialesChatgptApplication.class, args);
	}

}
