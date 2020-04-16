package br.com.hamamoto.repository;

import br.com.hamamoto.entity.RestrictedDocument;
import br.com.hamamoto.infrastructure.RestrictedDocumentPanacheRepository;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class RestrictedDocumentRepository {

    private final RestrictedDocumentPanacheRepository restrictedDocumentPanacheRepository;

    public RestrictedDocumentRepository(RestrictedDocumentPanacheRepository restrictedDocumentPanacheRepository) {
        this.restrictedDocumentPanacheRepository = restrictedDocumentPanacheRepository;
    }

    public Optional<RestrictedDocument> findByDocument(String document) {
        return Optional.ofNullable(restrictedDocumentPanacheRepository.find("document", document).firstResult());
    }

    public RestrictedDocument save(RestrictedDocument restrictedDocument) {
        restrictedDocumentPanacheRepository.persist(restrictedDocument);
        return  restrictedDocument;
    }

    public void delete(String id) {
        restrictedDocumentPanacheRepository.delete("_id", new ObjectId(id));
    }

    public Optional<RestrictedDocument> findById(String id) {
        try {
            return Optional.ofNullable(restrictedDocumentPanacheRepository.findById(new ObjectId(id)));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

}
