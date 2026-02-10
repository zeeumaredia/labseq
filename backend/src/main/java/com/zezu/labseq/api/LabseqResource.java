package com.zezu.labseq.api;

import com.zezu.labseq.api.dto.LabseqResponse;
import com.zezu.labseq.service.LabseqService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/labseq")
@Produces(MediaType.APPLICATION_JSON)
public class LabseqResource {

    @Inject
    LabseqService service;

    @GET
    @Path("/{n}")
    @Operation(
        summary = "Compute Labseq value",
        description = "Returns the Labseq sequence value for a non-negative integer index n."
    )
    @APIResponses({
        @APIResponse(
            responseCode = "200",
            description = "Labseq value computed successfully",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON,
                schema = @Schema(implementation = LabseqResponse.class)
            )
        ),
        @APIResponse(
            responseCode = "400",
            description = "Invalid input. n must be a non-negative integer."
        ),
        @APIResponse(
            responseCode = "413",
            description = "Input too large. n exceeds the configured MAX_N limit."
        )
    })

    public LabseqResponse get(@Parameter(
            description = "Sequence index (must be >= 0 and <= MAX_N)",
            required = true,
            example = "10"
        )
        @PathParam("n") long n){
        return new LabseqResponse(n, service.valueAt(n).toString());
    }
}
