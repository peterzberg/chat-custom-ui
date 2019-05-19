package com.zberg.sample.chatbot.api.models;

public class Message {

    private String text;

    public String getText() {

        return text;
    }

    public void setText(final String text) {

        this.text = text;
    }

    @Override
    public String toString() {

        return "Message{" +
                "text='" + text + '\'' +
                '}';
    }

}
