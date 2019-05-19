package com.zberg.sample.chatbot.service.chat;

public interface ChatService {

    Response sendMessage(String text, String sessionId, String language) throws ChatException;

}
