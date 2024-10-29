package by.bsuir.publisherservice.controller;

import by.bsuir.publisherservice.dto.request.TagRequestTo;
import by.bsuir.publisherservice.dto.response.TagResponseTo;

public class TagControllerTest extends RestControllerTest<TagRequestTo, TagResponseTo> {

    @Override
    protected TagRequestTo getRequestTo() {
        return new TagRequestTo(null, "tag" + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE));
    }

    @Override
    protected TagRequestTo getUpdateRequestTo(TagRequestTo originalRequest, Long updateEntityId) {
        return new TagRequestTo(updateEntityId,
                                originalRequest.name() + RANDOM_NUMBER_GENERATOR.nextInt(Integer.MAX_VALUE));
    }

    @Override
    protected String getRequestsMappingPath() {
        return "/tags";
    }
}
