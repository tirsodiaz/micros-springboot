package inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = {"controller"})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate template() {
        RestTemplate template = new RestTemplate();
        BasicAuthenticationInterceptor basicAuthenticationInterceptor;
        basicAuthenticationInterceptor = new BasicAuthenticationInterceptor("user1", "user1");
        basicAuthenticationInterceptor = new BasicAuthenticationInterceptor("admin", "admin");
        //basicAuthenticationInterceptor = new BasicAuthenticationInterceptor("user5", "user5");
        template.getInterceptors().add(basicAuthenticationInterceptor);
        return template;
    }
}
