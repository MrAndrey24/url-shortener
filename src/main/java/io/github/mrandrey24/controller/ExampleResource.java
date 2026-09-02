package io.github.mrandrey24.controller;

import io.github.mrandrey24.controller.dto.CreateRequest;
import io.github.mrandrey24.controller.dto.UpdateRequest;
import io.github.mrandrey24.controller.dto.UrlResponse;
import io.github.mrandrey24.service.UrlService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/api/v1")
public class ExampleResource {

    @Inject
    UrlService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UrlResponse save(CreateRequest request) {

        var dto = service.createUrl(request.url());

        return new UrlResponse(
                dto.url,
                dto.shortCode,
                dto.createdAt,
                dto.updatedAt,
                dto.accessCount

        );

    }

    @PUT()
    @Path("/{shortCode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UrlResponse update(@PathParam("shortCode") String shortCode, UpdateRequest request) {

        var dto = service.updateUrl(shortCode, request.url());

        return new UrlResponse(
                dto.url,
                dto.shortCode,
                dto.createdAt,
                dto.updatedAt,
                dto.accessCount
        );

    }

    @GET
    @Path("/{shortCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UrlResponse retrieveOriginalUrl(@PathParam("shortCode") String shortCode) {

        var dto = service.findByCode(shortCode);

        return new UrlResponse(
                dto.url,
                dto.shortCode,
                dto.createdAt,
                dto.updatedAt,
                dto.accessCount
        );

    }


    @DELETE
    @Path("/{shortCode}")
    @Produces(MediaType.TEXT_PLAIN)
    public void delete(@PathParam("shortCode") String code) {

        service.deleteByCode(code);

    }
}
