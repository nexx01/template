package example.perfomance.src;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration
public class RouteConfig {

    @Bean
    public RouterFunction<?> routerFunction(HelloController controller) {
        return route(GET("/api/perf"),controller::ping);
    }

}
