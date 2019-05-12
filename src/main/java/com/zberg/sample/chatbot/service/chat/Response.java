package com.zberg.sample.chatbot.service.chat;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Map;

public class Response {
    private String intent;
    private List<String> text;
    private Map<String, String> parameters;

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public void setParameters(final Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("intent", intent)
                .append("text", text)
                .append("parameters", parameters)
                .toString();
    }
}
