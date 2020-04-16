package br.com.hamamoto.view.request;

import br.com.hamamoto.model.DocumentType;

public class RestrictedDocumentCreationRequest {

    private DocumentType type;
    private String document;
    private String user;

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
