package org.fundaciobit.formacio.formaciorest.app;

import javax.ws.rs.ApplicationPath;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@ApplicationPath("/api")
@OpenAPIDefinition(
        info = @Info(
                title = "API REST de Formacio",
                description = "Conjunt de Serveis REST de proves",
                version = "1.0.0"),
        servers = { @Server(url = "http://localhost:8080/formaciorest/api") })
public class Application extends javax.ws.rs.core.Application {

}