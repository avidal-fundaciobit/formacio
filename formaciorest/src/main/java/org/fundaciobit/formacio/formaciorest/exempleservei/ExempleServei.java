package org.fundaciobit.formacio.formaciorest.exempleservei;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.fundaciobit.formacio.formaciorest.pojos.*;

@Path("/exempleservei")
@OpenAPIDefinition(tags = @Tag(name = "FormacioRest", description = "Exemples de Formació REST"))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExempleServei {

    private static final Logger logger = LoggerFactory.getLogger(ExempleServei.class);

    @GET
    @Path("/exempleoperacioget")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "exempleOperacioGet", tags = { "FormacioRest" }, summary = "A test operation GET")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Got it",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExampleResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Server is down!") })
    public ExampleResponse exempleOperacioGet() {
        return new ExampleResponse();
    }

    @POST
    @Path("/exempleoperaciopost")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "exempleOperacioPost", tags = { "FormacioRest" }, summary = "A test operation POST")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Got it",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = ExampleResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Server is down!") })
    public ExampleResponse exempleOperacioPost(ExampleRequest request) {
        logger.info("Server receieved: " + request);

        return new ExampleResponse();
    }
} 