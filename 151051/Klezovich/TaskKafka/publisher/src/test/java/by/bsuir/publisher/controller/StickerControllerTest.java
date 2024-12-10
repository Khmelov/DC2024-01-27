package by.bsuir.publisher.controller;

import by.bsuir.publisher.model.dto.request.StickerRequestTo;
import by.bsuir.publisher.model.dto.response.StickerResponseTo;

public class StickerControllerTest extends AbstractControllerTest<StickerRequestTo, StickerResponseTo> {

    @Override
    protected String getRequestsMappingPath() {
        return "/stickers";
    }

    @Override
    protected StickerRequestTo getRequestTo() {
        return new StickerRequestTo(null, "sticker #" + random.nextInt(Integer.MAX_VALUE));
    }

    @Override
    protected StickerRequestTo getUpdatedRequestTo(StickerRequestTo requestTo, Long entityId) {
        return new StickerRequestTo(entityId,
                requestTo.name() + random.nextInt(Integer.MAX_VALUE));
    }
}
