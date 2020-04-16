package br.com.hamamoto.view.endpoint;

import br.com.hamamoto.BaseTest;
import br.com.hamamoto.model.DocumentType;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

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
                .statusCode(200)
                .body("id", notNullValue())
                .body("documentType", is(DocumentType.CPF.toString()))
                .body("document", is("98243609059"))
                .body("user", is("fooBar"));
    }

}