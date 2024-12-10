package pl.sm.apigateway.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.*;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.Rendering;

@Configuration
public class GatewayRouteLocator {
    @Bean
    public RouterFunction<ServerResponse> indexRouterFunction() {
        return RouterFunctions.route(RequestPredicates.path("/"),
            request -> ServerResponse.ok().render("index",
                Rendering.view("index")
                    .build()
                    .modelAttributes())
        );
    }

    @Bean
    public RouterFunction<ServerResponse> notFoundRouterFunction() {
        return RouterFunctions.route(RequestPredicates.path("/404"),
            request -> ServerResponse.notFound().build()
        );
    }
}
