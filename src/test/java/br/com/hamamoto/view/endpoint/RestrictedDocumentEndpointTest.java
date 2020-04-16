package br.com.hamamoto.view.endpoint;

import br.com.hamamoto.BaseTest;
import br.com.hamamoto.infrastructure.exception.DomainExceptionMessage;
import br.com.hamamoto.model.DocumentType;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class RestrictedDocumentEndpointTest extends BaseTest {

    @Test
    public void shouldRestrictADocument() throws Exception {
        given().log().everything()
            .contentType(APPLICATION_JSON)
            .body(resource("fixtures/requests/restricted-document/new-restricted.json"))
        .when()
            .post("/rs/restricted-documents")
        .then().log().everything()
            .statusCode(HttpStatus.SC_CREATED)
            .header("location", notNullValue())
            .body("id", notNullValue())
            .body("type", is(DocumentType.CPF.toString()))
            .body("document", is("98243609059"))
            .body("user", is("fooBar"));
    }

    @Test
    public void shouldReturnConflictWhenDocumentIsAlreadyRestricted() throws Exception {
        insertRestrictedDocument("data/restricted-document/existent-document.bson");

        given().log().everything()
            .contentType(APPLICATION_JSON)
            .body(resource("fixtures/requests/restricted-document/existent-document.json"))
        .when()
            .post("/rs/restricted-documents")
        .then().log().everything()
            .statusCode(HttpStatus.SC_CONFLICT)
            .body("code", is(DomainExceptionMessage.DOCUMENT_ALREADY_RESTRICTED.getCode()))
            .body("message", is(DomainExceptionMessage.DOCUMENT_ALREADY_RESTRICTED.getMessage()));
    }

    @Test
    public void shouldReturnRestrictedDocumentById() throws IOException {
        insertRestrictedDocument("data/restricted-document/existent-document.bson");

        given().log().everything()
            .pathParam("id", "507f191e810c19729de860ea")
        .when()
            .get("/rs/restricted-documents/{id}")
        .then().log().everything()
            .statusCode(HttpStatus.SC_OK)
            .body("id", is("507f191e810c19729de860ea"))
            .body("type", is(DocumentType.CPF.toString()))
            .body("document", is("39515268079"))
            .body("createdAt",is("2012-12-19T06:01:17.171"))
            .body("user", is("BizzBuzz"));
    }

    @Test
    public void shouldReturnRestrictedDocumentByDocument() throws IOException {
        insertRestrictedDocument("data/restricted-document/existent-document.bson");

        given().log().everything()
            .pathParam("document", "39515268079")
        .when()
            .get("/rs/restricted-documents/document/{document}")
        .then().log().everything()
            .statusCode(HttpStatus.SC_OK)
            .body("id", is("507f191e810c19729de860ea"))
            .body("type", is(DocumentType.CPF.toString()))
            .body("document", is("39515268079"))
            .body("createdAt",is("2012-12-19T06:01:17.171"))
            .body("user", is("BizzBuzz"));
    }

}