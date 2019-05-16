package com.zberg.sample.chatbot.service.chat;

public interface ChatService {

    Response sendMessage(final String text, final String sessionId, final String language) throws ChatException;

}
