package inicio;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

@Configuration
public class Resilience4jConfig {
    CircuitBreakerConfig config = CircuitBreakerConfig.custom()
            // .slidingWindowType(SlidingWindowType.COUNT_BASED) //defecto es COUNT_BASED
            .slidingWindowSize(6)
            // .failureRateThreshold(50) //defecto es 50% La solicitud 4 al microservicio no
            // se haria por estado abierto
            .waitDurationInOpenState(Duration.ofMillis(40000)) // tiempo estado abierto
            .build();

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
        // configuracion global
//      return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//          .circuitBreakerConfig(config)
//          .build());
        // configuracion especifica para un determinado circuit breaker
        return factory -> factory.configure(builder -> builder.circuitBreakerConfig(config).build(), "circuit1");
    }
}
