package by.bsuir.discussion.controller;

import by.bsuir.discussion.util.config.ServerConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public abstract class AbstractControllerTest<RequestTo, ResponseTo> {

    @Autowired
    private static ServerConfig serverConfig;
    protected static final Random random = new Random();

    @BeforeAll
    public static void setup() {
        if (serverConfig != null) {
            RestAssured.port = serverConfig.getPort();
            RestAssured.basePath = serverConfig.getServlet().getContextPath();
        } else {
            RestAssured.port = 24130;
            RestAssured.basePath = "/api/v1.0";
        }

        RestAssured.baseURI = "http://127.0.0.1";
    }

    @Test
    public void saveRec() {
        RequestTo request = getRequestTo();
        Response response = saveRequest(request);
        Long entityId = getResponseId(response);

        response.then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        deleteEntity(entityId);
    }

    @Test
    public void findAllRecords() {
        List<RequestTo> requestToList = List.of(getRequestTo(), getRequestTo());
        List<Response> responseList = saveRequestList(requestToList);

        when()
                .get(getRequestsMappingPath())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

        deleteEntityList(getResponseIdList(responseList));
    }

    @Test
    public void findRecById() {
        RequestTo request = getRequestTo();
        Response response = saveRequest(request);
        Long entityId = getResponseId(response);

        when()
                .get(getRequestsMappingPath() + "/" + entityId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

        deleteEntity(entityId);
    }

    @Test
    public void updateRec() {
        RequestTo request = getRequestTo();
        Response response = saveRequest(request);
        Long entityId = getResponseId(response);
        RequestTo updateRequestTo = getUpdatedRequestTo(request, entityId);

        given()
                .contentType(ContentType.JSON)
                .body(updateRequestTo)
                .when()
                .put(getRequestsMappingPath())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

        deleteEntity(entityId);
    }

    @Test
    public void deleteRec() {
        RequestTo request = getRequestTo();
        Response response = saveRequest(request);
        Long entityId = getResponseId(response);

        deleteEntity(entityId)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void findRecByNonExistentId() {
        RequestTo request = getRequestTo();
        Response response = saveRequest(request);
        Long entityId = getResponseId(response);

        when()
                .get(getRequestsMappingPath() + "/" + (entityId + 1))
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());

        deleteEntity(entityId);
    }

    @Test
    public void updateRecByNonExistentId() {
        RequestTo request = getRequestTo();
        Response response = saveRequest(request);
        Long entityId = getResponseId(response);
        RequestTo updateRequestTo = getUpdatedRequestTo(request, entityId + 1);

        given()
                .contentType(ContentType.JSON)
                .body(updateRequestTo)
                .when()
                .put(getRequestsMappingPath())
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());

        deleteEntity(entityId);
    }

    @Test
    public void deleteRecByNonExistentId() {
        RequestTo request = getRequestTo();
        Response response = saveRequest(request);
        Long entityId = getResponseId(response);

        deleteEntity(entityId + 1)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NOT_FOUND.value());

        deleteEntity(entityId);
    }

    protected Response saveRequest(RequestTo requestTo) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestTo)
                .post(getRequestsMappingPath());
    }

    protected List<Response> saveRequestList(List<RequestTo> requestToList) {
        return requestToList.stream()
                .map(this::saveRequest)
                .collect(Collectors.toList());
    }


    protected Long getResponseId(Response response) {
        return response.jsonPath().getLong("id");
    }

    protected List<Long> getResponseIdList(List<Response> responseList) {
        return responseList.stream()
                .map(this::getResponseId)
                .collect(Collectors.toList());
    }

    protected Response deleteEntity(Long id) {
        return given().delete(getRequestsMappingPath() + "/" + id);
    }

    protected List<Response> deleteEntityList(List<Long> entityIdList) {
        return entityIdList.stream()
                .map(this::deleteEntity)
                .collect(Collectors.toList());
    }

    protected <FK> Response createEntityFK(FK entity, String controllerPath) {
        return given()
                .contentType(ContentType.JSON)
                .body(entity)
                .post(controllerPath);
    }

    protected static <FK> Response deleteEntityFK(Long id, String controllerPath) {
        return given().delete(controllerPath + "/" + id);
    }

    protected abstract String getRequestsMappingPath();
    protected abstract RequestTo getRequestTo();
    protected abstract RequestTo getUpdatedRequestTo(RequestTo requestTo, Long entityId);
}
