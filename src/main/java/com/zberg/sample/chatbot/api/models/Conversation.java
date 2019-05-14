package com.zberg.sample.chatbot.api.models;

public class Conversation {
    private String id;

    public Conversation() {
        // default
    }

    public Conversation(String id) {

        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id='" + id + '\'' +
                '}';
    }
}
