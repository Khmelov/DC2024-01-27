package by.bsuir.publisher.controller;

import by.bsuir.publisher.model.dto.request.UserRequestTo;
import by.bsuir.publisher.model.dto.response.UserResponseTo;

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
