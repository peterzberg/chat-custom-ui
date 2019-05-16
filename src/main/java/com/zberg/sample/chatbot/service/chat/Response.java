package com.zberg.sample.chatbot.service.chat;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Map;

public class Response {
    private String intent;
    private List<String> text;
    private Map<String, String> parameters;
    private boolean allRequiredParamsSet;
    private String currentSlot;

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

    public boolean isAllRequiredParamsSet() {
        return allRequiredParamsSet;
    }

    public void setAllRequiredParamsSet(boolean allRequiredParamsSet) {
        this.allRequiredParamsSet = allRequiredParamsSet;
    }

    public void setCurrentSlot(String currentSlot) {

        this.currentSlot = currentSlot;
    }

    public String getCurrentSlot() {
        return currentSlot;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("intent", intent)
                .append("text", text)
                .append("parameters", parameters)
                .append("allRequiredParamsSet", allRequiredParamsSet)
                .append("currentSlot", currentSlot)
                .toString();
    }
}
