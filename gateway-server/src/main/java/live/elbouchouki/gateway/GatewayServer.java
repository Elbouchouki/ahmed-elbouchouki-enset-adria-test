package live.elbouchouki.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServer {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServer.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        r -> r.path("/eureka")
                                .filters(f -> f.setPath("/"))
                                .uri("http://localhost:8761")
                )
                .route(
                        r -> r.path("/eureka/**")
                                .uri("http://localhost:8761")
                )
                .build();
    }
}
//                .route(
//                        r -> r.path("/api/v1/elements/**")
//                                .or()
//                                .path("/element-service/docs")
//                                .filters(f -> f.rewritePath("/element-service/docs", "/v3/api-docs"))
//                                .uri("lb://element-service")
//                )
