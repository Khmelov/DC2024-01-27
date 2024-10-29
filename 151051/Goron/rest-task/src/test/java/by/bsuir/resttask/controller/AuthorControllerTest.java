package by.bsuir.resttask.controller;

import by.bsuir.resttask.dto.request.AuthorRequestTo;
import by.bsuir.resttask.dto.response.AuthorResponseTo;

public class AuthorControllerTest extends RestControllerTest<AuthorRequestTo, AuthorResponseTo> {

    @Override
    protected AuthorRequestTo getRequestTo() {
        return new AuthorRequestTo(null,
                                   "login"     + RANDOM_NUMBER_GENERATOR.nextInt(),
                                   "password"  + RANDOM_NUMBER_GENERATOR.nextInt(),
                                   "firstname" + RANDOM_NUMBER_GENERATOR.nextInt(),
                                   "lastname"  + RANDOM_NUMBER_GENERATOR.nextInt());
    }

    @Override
    protected AuthorRequestTo getUpdateRequestTo(AuthorRequestTo originalRequest, Long updateEntityId) {
        return new AuthorRequestTo(
                    updateEntityId,
                    originalRequest.login()     + RANDOM_NUMBER_GENERATOR.nextInt(),
                    originalRequest.password()  + RANDOM_NUMBER_GENERATOR.nextInt(),
                    originalRequest.firstname() + RANDOM_NUMBER_GENERATOR.nextInt(),
                    originalRequest.lastname()  + RANDOM_NUMBER_GENERATOR.nextInt());
    }

    @Override
    protected String getRequestsMappingPath() {
        return "/authors";
    }
}
