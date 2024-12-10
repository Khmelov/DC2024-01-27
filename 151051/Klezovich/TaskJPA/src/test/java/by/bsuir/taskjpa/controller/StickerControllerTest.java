package by.bsuir.taskjpa.controller;

import by.bsuir.taskjpa.model.dto.request.StickerRequestTo;
import by.bsuir.taskjpa.model.dto.response.StickerResponseTo;

public class StickerControllerTest extends AbstractControllerTest<StickerRequestTo, StickerResponseTo> {

    @Override
    protected String getRequestsMappingPath() {
        return "/stickers";
    }

    @Override
    protected StickerRequestTo getRequestTo() {
        return new StickerRequestTo(null, "sticker #" + random.nextInt());
    }

    @Override
    protected StickerRequestTo getUpdatedRequestTo(StickerRequestTo requestTo, Long entityId) {
        return new StickerRequestTo(entityId,
                requestTo.name() + random.nextInt());
    }
}
