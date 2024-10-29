package by.bsuir.discussionservice.controller;

import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public abstract class RestControllerTest<RQ, RS> {

    protected static final Random RANDOM_NUMBER_GENERATOR = new Random();

    protected abstract RQ getRequestTo();
    protected abstract RQ getUpdateRequestTo(RQ originalRequest, Long updateEntityId);
    protected abstract String getRequestsMappingPath();

    @BeforeAll
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = 24130;
        }
        else {
            RestAssured.port = Integer.valueOf(port);
        }

        String basePath = System.getProperty("server.base");
        if (basePath == null){
            basePath = "/api/v1.0";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if (baseHost == null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;
    }

    @Test
    public void save_ShouldReturn201() {
        RQ request = getRequestTo();
        Response saveResponse = saveRequest(request);
        Long responseEntityId = getResponseId(saveResponse);

        saveResponse.then()
                    .assertThat()
                    .statusCode(HttpStatus.CREATED.value());

        deleteEntity(responseEntityId);
    }

    @Test
    public void getById_ShouldReturn200() {
        RQ request = getRequestTo();
        Response saveResponse = saveRequest(request);
        Long responseEntityId = getResponseId(saveResponse);

        when()
        .get(getRequestsMappingPath() + "/" + responseEntityId)
        .then()
        .assertThat()
        .statusCode(HttpStatus.OK.value());

        deleteEntity(responseEntityId);
    }

    @Test
    public void getAll_ShouldReturn200() {
        List<RQ> requests = List.of(getRequestTo(), getRequestTo());
        List<Response> saveResponses = saveRequests(requests);

        when()
        .get(getRequestsMappingPath())
        .then()
        .assertThat()
        .statusCode(HttpStatus.OK.value());

        deleteEntities(getResponsesId(saveResponses));
    }

    @Test
    public void update_ShouldReturn200() {
        RQ request = getRequestTo();
        Response saveResponse = saveRequest(request);
        Long responseEntityId = getResponseId(saveResponse);
        RQ updateRequest = getUpdateRequestTo(request, responseEntityId);

        given()
        .contentType(ContentType.JSON)
        .body(updateRequest)
        .when()
        .put(getRequestsMappingPath())
        .then()
        .assertThat()
        .statusCode(HttpStatus.OK.value());

        deleteEntity(responseEntityId);
    }

    @Test
    public void delete_ShouldReturn204() {
        RQ request = getRequestTo();
        Response saveResponse = saveRequest(request);
        Long responseEntityId = getResponseId(saveResponse);

        deleteEntity(responseEntityId)
        .then()
        .assertThat()
        .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void getByNonExistentId_ShouldReturn404() {
        RQ request = getRequestTo();
        Response saveResponse = saveRequest(request);
        Long responseEntityId = getResponseId(saveResponse);

        when()
        .get(getRequestsMappingPath() + "/" + (responseEntityId + 1))
        .then()
        .assertThat()
        .statusCode(HttpStatus.NOT_FOUND.value());

        deleteEntity(responseEntityId);
    }

    @Test
    public void updateByNonExistentId_ShouldReturn404() {
        RQ request = getRequestTo();
        Response saveResponse = saveRequest(request);
        Long responseEntityId = getResponseId(saveResponse);
        RQ updateRequest = getUpdateRequestTo(request, responseEntityId + 1);

        given()
        .contentType(ContentType.JSON)
        .body(updateRequest)
        .when()
        .put(getRequestsMappingPath())
        .then()
        .assertThat()
        .statusCode(HttpStatus.NOT_FOUND.value());

        deleteEntity(responseEntityId);
    }

    @Test
    public void deleteByNonExistentId_ShouldReturn404() {
        RQ request = getRequestTo();
        Response saveResponse = saveRequest(request);
        Long responseEntityId = getResponseId(saveResponse);

        deleteEntity(responseEntityId + 1)
        .then()
        .assertThat()
        .statusCode(HttpStatus.NOT_FOUND.value());

        deleteEntity(responseEntityId);
    }

    protected Response saveRequest(RQ request) {
        return given()
               .contentType(ContentType.JSON)
               .body(request)
               .post(getRequestsMappingPath());
    }
    
    protected Response updateRequest(RQ request) {
        return given()
               .contentType(ContentType.JSON)
               .body(request)
               .put(getRequestsMappingPath());
    }

    protected List<Response> saveRequests(List<RQ> requests) {
        List<Response> saveResponses = new ArrayList<>();
        for (var request : requests) {
            saveResponses.add(saveRequest(request));
        }        
        return saveResponses;
    }

    protected Long getResponseId(Response response) {
        return response.jsonPath().getLong("id");
    }

    protected List<Long> getResponsesId(List<Response> responses) {
        List<Long> responsesId = new ArrayList<>();
        for (var response : responses) {
            responsesId.add(getResponseId(response));
        }
        return responsesId;
    }

    protected Response deleteEntity(Long id) {
        return given()
               .delete(getRequestsMappingPath() + "/" + id);
    }

    protected List<Response> deleteEntities(List<Long> entitiesId) {
        List<Response> responses = new ArrayList<>();
        for (var entityId : entitiesId) {
            responses.add(deleteEntity(entityId));
        }        
        return responses;
    }
    
    protected <FK> Response createForeignKeyEntity(FK entity, String controllerPath) {
        return given()
               .contentType(ContentType.JSON)
               .body(entity)
               .post(controllerPath);
    }

    protected static <FK> Response deleteForeignKeyEntity(Long id, String controllerPath) {
        return given()
               .delete(controllerPath + "/" + id);
    }
}
