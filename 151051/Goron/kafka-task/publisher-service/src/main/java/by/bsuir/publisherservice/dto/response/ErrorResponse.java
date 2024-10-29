package by.bsuir.publisherservice.dto.response;

import lombok.Builder;

@Builder
public record ErrorResponse(String errorMessage, Integer errorCode) {
    public static class ErrorResponseBuilder {
        public ErrorResponseBuilder httpStatusCode(Integer httpStatusCode) {
            this.errorCode = httpStatusCode * 100;
            return this;
        }

        public ErrorResponseBuilder restTaskCode(Integer restTaskCode) {
            this.errorCode += restTaskCode;
            return this;
        }
    }
}
