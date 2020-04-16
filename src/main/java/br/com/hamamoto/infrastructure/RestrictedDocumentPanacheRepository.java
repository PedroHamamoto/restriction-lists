package br.com.hamamoto.infrastructure;

import br.com.hamamoto.entity.RestrictedDocument;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class RestrictedDocumentPanacheRepository implements PanacheMongoRepository<RestrictedDocument> {

}
