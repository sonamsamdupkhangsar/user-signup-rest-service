package me.sonam.user;

import me.sonam.user.handler.UserHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class Router {
    private static final Logger LOG = LoggerFactory.getLogger(Router.class);

    @Bean
    public RouterFunction<ServerResponse> route(UserHandler handler) {
        LOG.info("building router function");
        return RouterFunctions
                .route(POST("/users").and(accept(MediaType.APPLICATION_JSON)), handler::signupUser)
                .andRoute(PUT("/users").and(accept(MediaType.APPLICATION_JSON)), handler::update)
                .andRoute(PUT("/users/photo").and(accept(MediaType.APPLICATION_JSON)), handler::updateProfilePhoto)
                .andRoute(GET("/users/names/{firstName}/{lastName}").and(accept(MediaType.APPLICATION_JSON)), handler::findMatchingFirstNameAndLastName)
                .andRoute(GET("/users/ids/{ids}").and(accept(MediaType.APPLICATION_JSON)), handler::getBatchOfUserById)
                .andRoute(GET("/users/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::getUserById)
                .andRoute(GET("/users/authentication-id/{authenticationId}").and(accept(MediaType.APPLICATION_JSON)), handler::getUserByAuthId)
                .andRoute(GET("/users/profile/authentication-id/{authenticationId}").and(accept(MediaType.APPLICATION_JSON)), handler::getUserByAuthIdProfileSearch)
                .andRoute(PUT("/users/{authenticationId}/active").and(accept(MediaType.APPLICATION_JSON)), handler::activateUser)
                .andRoute(DELETE("/users").and(accept(MediaType.APPLICATION_JSON)), handler::deleteMyAccount);

    }
}
