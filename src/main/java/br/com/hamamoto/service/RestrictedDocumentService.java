package br.com.hamamoto.service;

import br.com.hamamoto.entity.RestrictedDocument;
import br.com.hamamoto.infrastructure.exception.DomainException;
import br.com.hamamoto.repository.RestrictedDocumentRepository;
import br.com.hamamoto.view.request.RestrictedDocumentCreationRequest;

import javax.enterprise.context.ApplicationScoped;

import static br.com.hamamoto.infrastructure.exception.DomainExceptionMessage.DOCUMENT_ALREADY_RESTRICTED;
import static br.com.hamamoto.infrastructure.exception.DomainExceptionMessage.RESTRICTED_DOCUMENT_NOT_FOUND;

@ApplicationScoped
public class RestrictedDocumentService {

    private final RestrictedDocumentRepository restrictedDocumentRepository;

    public RestrictedDocumentService(RestrictedDocumentRepository restrictedDocumentRepository) {
        this.restrictedDocumentRepository = restrictedDocumentRepository;
    }

    public RestrictedDocument save(RestrictedDocumentCreationRequest request) {
        restrictedDocumentRepository.findByDocument(request.getDocument())
                .ifPresent(restrictedDocument -> {
                    throw new DomainException(DOCUMENT_ALREADY_RESTRICTED);
                });

        return restrictedDocumentRepository.save(toEntity(request));
    }

    public void delete(String id) {
        findById(id);

        restrictedDocumentRepository.delete(id);
    }

    public RestrictedDocument findById(String id) {
        return restrictedDocumentRepository.findById(id)
                .orElseThrow(() -> new DomainException(RESTRICTED_DOCUMENT_NOT_FOUND));
    }

    private RestrictedDocument toEntity(RestrictedDocumentCreationRequest request) {
        var restrictedDocument = new RestrictedDocument();

        restrictedDocument.setDocument(request.getDocument());
        restrictedDocument.setDocumentType(request.getType());
        restrictedDocument.setUser(request.getUser());

        return restrictedDocument;
    }

}
