package br.com.hamamoto.view.endpoint;

import br.com.hamamoto.service.RestrictedDocumentService;
import br.com.hamamoto.view.request.RestrictedDocumentCreationRequest;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Optional;

@Path("/rs/restricted-documents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestrictedDocumentEndpoint {

    private final RestrictedDocumentService restrictedDocumentService;

    public RestrictedDocumentEndpoint(RestrictedDocumentService restrictedDocumentService) {
        this.restrictedDocumentService = restrictedDocumentService;
    }

    @POST
    public Response save(@Valid RestrictedDocumentCreationRequest request, @Context UriInfo uriInfo) {
        var persistedRestrictedDocument = restrictedDocumentService.save(request);

        var uriBuilder = uriInfo.getAbsolutePathBuilder().path(persistedRestrictedDocument.getId().toHexString());
        return Response.created(uriBuilder.build())
                .entity(persistedRestrictedDocument)
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        restrictedDocumentService.delete(id);

        return Response.noContent().build();
    }

    @GET
    @Path("document/{document}")
    public Response getByDocument(@PathParam("document") String document) {
        return Response.ok(restrictedDocumentService.findByDocument(document)).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") String id, @Context UriInfo uriInfo) {
        return Response.ok(restrictedDocumentService.findById(id)).build();
    }
}
