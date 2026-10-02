package io.github.mrandrey24.controller;

import io.github.mrandrey24.controller.dto.CreateRequest;
import io.github.mrandrey24.controller.dto.UpdateRequest;
import io.github.mrandrey24.controller.dto.UrlResponse;
import io.github.mrandrey24.controller.dto.UrlResponseStats;
import io.github.mrandrey24.service.UrlService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/api/v1")
public class ExampleResource {

    @Inject
    UrlService service;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UrlResponse save(@RequestBody @Valid CreateRequest request) {

        var dto = service.createUrl(request.url());

        return new UrlResponse(
                dto.url,
                dto.shortCode,
                dto.createdAt,
                dto.updatedAt
        );

    }

    @PUT()
    @Path("/{shortCode}")
    @Consumes(MediaType.APPLICATION_JSON)
    public UrlResponse update(@PathParam("shortCode") String shortCode, @RequestBody @Valid UpdateRequest request) {

        var dto = service.updateUrl(shortCode, request.url());

        return new UrlResponse(
                dto.url,
                dto.shortCode,
                dto.createdAt,
                dto.updatedAt
        );

    }

    @GET
    @Path("/{shortCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public UrlResponse retrieveOriginalUrl(@PathParam("shortCode") String shortCode) {

        var dto = service.findByCode(shortCode);

        return new UrlResponse(
                dto.url,
                dto.shortCode,
                dto.createdAt,
                dto.updatedAt
        );

    }

    @GET
    @Path("/stats/{shortCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public UrlResponseStats retrieveUrlStats(@PathParam("shortCode") String shortCode) {
        var dto = service.findByCode(shortCode);

        return new UrlResponseStats(
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
    public String delete(@PathParam("shortCode") String code) {

        service.deleteByCode(code);

        return "Successfully deleted";

    }
}
