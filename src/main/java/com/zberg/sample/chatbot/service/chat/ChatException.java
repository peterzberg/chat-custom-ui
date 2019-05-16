package com.zberg.sample.chatbot.service.chat;

public class ChatException extends Exception {

    public ChatException(final String message, final Throwable t) {
        super(message, t);
    }
}
