package com.zezu.labseq.api;

import com.zezu.labseq.api.dto.LabseqResponse;
import com.zezu.labseq.service.LabseqService;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/labseq")
@Produces(MediaType.APPLICATION_JSON)
public class LabseqResource {

    @Inject
    LabseqService service;

    @GET
    @Path("/{n}")
    public LabseqResponse get(@PathParam("n") long n) {
        if (n < 0) {
            throw new BadRequestException("Imput number non-negative Integer.");
        }
        
        return new LabseqResponse(n, service.valueAt(n).toString());
    }
}
