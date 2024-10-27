package com.proyecto.Proyecto.PApiProduct;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import java.time.Duration;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Resilience4jConfig {
    
    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        // Crea una configuración personalizada para el Circuit Breaker
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .slidingWindowSize(10)                              //Indica cuantos fallos usara para ver el promedio de errores
                .failureRateThreshold(50)                           //Si el porcentaje de fallo es de la mitad, el sistema se abre
                .waitDurationInOpenState(Duration.ZERO.ofSeconds(10)) // Se mantendrá abierto por 10 segundos antes de volver a intentarlo
                .recordExceptions(Exception.class)                    //Se registran las escepciones para considerar que una llamada fallo
                .ignoreExceptions(IllegalArgumentException.class)     //Se declaran las excepciones por ignorar  
                .build();

        // Crea un registro de Circuit Breakers
        return CircuitBreakerRegistry.of(Map.of("productService", config));    //Se registra la configuracion del circuitbreaker como externalService
    }

    @Bean                                                       //Registra el circuit breaker creado y se usara en las llamadas externas
    public CircuitBreaker myServiceCircuitBreaker(CircuitBreakerRegistry circuitBreakerRegistry) {
        return circuitBreakerRegistry.circuitBreaker("productService");
    }
    
}
