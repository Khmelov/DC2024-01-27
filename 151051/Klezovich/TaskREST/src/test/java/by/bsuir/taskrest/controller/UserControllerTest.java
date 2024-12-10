package by.bsuir.taskrest.controller;

import by.bsuir.taskrest.model.dto.request.UserRequestTo;
import by.bsuir.taskrest.model.dto.response.UserResponseTo;

public class UserControllerTest extends AbstractControllerTest<UserRequestTo, UserResponseTo> {

    @Override
    protected String getRequestsMappingPath() {
        return "/users";
    }

    @Override
    protected UserRequestTo getRequestTo() {
        return new UserRequestTo(null,
                "login"     + random.nextInt(),
                "password"  + random.nextInt(),
                "firstname" + random.nextInt(),
                "lastname"  + random.nextInt());
    }

    @Override
    protected UserRequestTo getUpdatedRequestTo(UserRequestTo requestTo, Long entityId) {
        return new UserRequestTo(
                entityId,
                requestTo.login()     + random.nextInt(),
                requestTo.password()  + random.nextInt(),
                requestTo.firstname() + random.nextInt(),
                requestTo.lastname()  + random.nextInt());
    }
}
