package br.com.hamamoto.view.request;

import br.com.hamamoto.model.DocumentType;

public class RestrictedDocumentCreationRequest {

    private DocumentType type;
    private String document;
    private String user;

    public RestrictedDocumentCreationRequest(DocumentType type, String document, String user) {
        this.type = type;
        this.document = document;
        this.user = user;
    }

    public DocumentType getType() {
        return type;
    }

    public String getDocument() {
        return document;
    }

    public String getUser() {
        return user;
    }
}
