package com.zberg.sample.chatbot.service.response.text;

import com.zberg.sample.chatbot.api.models.ResponseType;

import java.util.List;

public class TextResponse extends AbstractResponse {
    private List<String> text;

    public TextResponse() {
        super(ResponseType.TEXT);
    }

    public void setText(final List<String> text) {
        this.text = text;
    }

    public List<String> getText() {
        return text;
    }
}
