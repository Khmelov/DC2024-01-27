package by.bsuir.resttask.controller;

import by.bsuir.resttask.dto.request.TagRequestTo;
import by.bsuir.resttask.dto.response.TagResponseTo;

public class TagControllerTest extends RestControllerTest<TagRequestTo, TagResponseTo> {

    @Override
    protected TagRequestTo getRequestTo() {
        return new TagRequestTo(null, "tag" + RANDOM_NUMBER_GENERATOR.nextInt());
    }

    @Override
    protected TagRequestTo getUpdateRequestTo(TagRequestTo originalRequest, Long updateEntityId) {
        return new TagRequestTo(updateEntityId,
                                originalRequest.name() + RANDOM_NUMBER_GENERATOR.nextInt());
    }

    @Override
    protected String getRequestsMappingPath() {
        return "/tags";
    }
}
