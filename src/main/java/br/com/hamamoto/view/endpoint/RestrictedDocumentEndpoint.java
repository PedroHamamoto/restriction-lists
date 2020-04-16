package br.com.hamamoto.view.endpoint;

import br.com.hamamoto.service.RestrictedDocumentService;
import br.com.hamamoto.view.request.RestrictedDocumentCreationRequest;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rs/restricted-documents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestrictedDocumentEndpoint {

    private final RestrictedDocumentService restrictedDocumentService;

    public RestrictedDocumentEndpoint(RestrictedDocumentService restrictedDocumentService) {
        this.restrictedDocumentService = restrictedDocumentService;
    }

    @POST
    public Response save(@Valid RestrictedDocumentCreationRequest request) {
        var persistedRestrictedDocument = restrictedDocumentService.save(request);

        return Response.ok(persistedRestrictedDocument).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        restrictedDocumentService.delete(id);

        return Response.noContent().build();
    }

}
