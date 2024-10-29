package by.bsuir.publisherservice.controller;

import by.bsuir.publisherservice.dto.request.AuthorRequestTo;
import by.bsuir.publisherservice.dto.response.AuthorResponseTo;

public class AuthorControllerTest extends RestControllerTest<AuthorRequestTo, AuthorResponseTo> {

    @Override
    protected AuthorRequestTo getRequestTo() {
        return new AuthorRequestTo(null,
                                   "login"     + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE),
                                   "password"  + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE),
                                   "firstname" + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE),
                                   "lastname"  + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE));
    }

    @Override
    protected AuthorRequestTo getUpdateRequestTo(AuthorRequestTo originalRequest, Long updateEntityId) {
        return new AuthorRequestTo(
                    updateEntityId,
                    originalRequest.login()     + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE),
                    originalRequest.password()  + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE),
                    originalRequest.firstname() + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE),
                    originalRequest.lastname()  + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE));
    }

    @Override
    protected String getRequestsMappingPath() {
        return "/authors";
    }
}
