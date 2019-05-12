package com.zberg.sample.chatbot.api.models;

import java.util.List;

public class ChatResponse {
    private ResponseType type;
    private List<String> text;
    private String action;
    private String intent;

    public ChatResponse() {
        // default
    }

    public ChatResponse(final ResponseType type, final List<String> text, final String action, final String intent) {
        this.type = type;
        this.text = text;
        this.action = action;
        this.intent = intent;
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    @Override
    public String toString() {
        return "ChatResponse{" +
                "type=" + type +
                ", text='" + text + '\'' +
                ", action='" + action + '\'' +
                ", intent='" + intent + '\'' +
                '}';
    }
}
