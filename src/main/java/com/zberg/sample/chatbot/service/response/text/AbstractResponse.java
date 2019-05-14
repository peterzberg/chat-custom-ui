package com.zberg.sample.chatbot.service.response.text;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AbstractResponse {
    private final ResponseType responseType;


    AbstractResponse(ResponseType responseType) {
        Validate.notNull(responseType, "responseType must not be null");
        this.responseType = responseType;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("responseType", responseType)
                .toString();
    }
}
