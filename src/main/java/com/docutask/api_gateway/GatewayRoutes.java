package com.docutask.api_gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.web.servlet.function.RequestPredicates.path;

@Configuration
public class GatewayRoutes {

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {
        return route("user-service")
                .route(path("/api/v1/users"), http())
                .route(path("/api/v1/users/**"), http())
                .filter(lb("user-service"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> vehicleServiceRoute() {
        return route("document-service")
                .route(path("/api/v1/documents"), http())
                .route(path("/api/v1/documents/**"), http())
                .filter(lb("document-service"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> maintenanceServiceRoute() {
        return route("task-service")
                .route(path("/api/v1/tasks"), http())
                .route(path("/api/v1/tasks/**"), http())
                .filter(lb("task-service"))
                .build();
    }
}