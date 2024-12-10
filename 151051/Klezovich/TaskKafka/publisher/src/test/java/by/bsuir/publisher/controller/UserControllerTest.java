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
                "login"     + random.nextInt(Integer.MAX_VALUE),
                "password"  + random.nextInt(Integer.MAX_VALUE),
                "firstname" + random.nextInt(Integer.MAX_VALUE),
                "lastname"  + random.nextInt(Integer.MAX_VALUE));
    }

    @Override
    protected UserRequestTo getUpdatedRequestTo(UserRequestTo requestTo, Long entityId) {
        return new UserRequestTo(
                entityId,
                requestTo.login()     + random.nextInt(Integer.MAX_VALUE),
                requestTo.password()  + random.nextInt(Integer.MAX_VALUE),
                requestTo.firstname() + random.nextInt(Integer.MAX_VALUE),
                requestTo.lastname()  + random.nextInt(Integer.MAX_VALUE));
    }
}
