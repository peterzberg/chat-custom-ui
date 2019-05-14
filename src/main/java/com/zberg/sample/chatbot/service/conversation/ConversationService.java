package com.zberg.sample.chatbot.service.conversation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zberg.sample.chatbot.service.chat.ChatService;
import com.zberg.sample.chatbot.service.chat.Response;
import com.zberg.sample.chatbot.service.response.ResponseHandler;
import com.zberg.sample.chatbot.service.response.ResponseHandlerService;
import com.zberg.sample.chatbot.service.response.text.AbstractResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConversationService {

    private final ChatService chatService;
    private final ResponseHandlerService responseHandlerService;

    public ConversationService(final ChatService chatService, final ResponseHandlerService responseHandlerService) {

        this.chatService = chatService;
        this.responseHandlerService = responseHandlerService;
    }

    public String converse(final String text, final String sessionId, final String language) throws IOException {

        final Response response = chatService.sendMessage(text, sessionId, language);
        final ResponseHandler responseHandler = responseHandlerService.getResponseHandlerFor(response);
        final AbstractResponse answer = responseHandler.handleResponse(response);

        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(answer);
    }

}
