package com.zberg.sample.chatbot.api;

import com.zberg.sample.chatbot.api.models.Conversation;
import com.zberg.sample.chatbot.api.models.Message;
import com.zberg.sample.chatbot.service.conversation.ConversationService;
import com.zberg.sample.chatbot.service.response.text.AbstractResponse;
import com.zberg.sample.chatbot.service.response.text.AddressChangeResponse;
import com.zberg.sample.chatbot.service.response.text.AgencyResponse;
import com.zberg.sample.chatbot.service.response.text.TextResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController("/api/conversation")
public class ConversationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConversationController.class);

    private final ConversationService conversationService;

    public ConversationController(final ConversationService conversationService) {

        this.conversationService = conversationService;
    }

    @PostMapping(path = "{id}/{lang}/message", produces = "application/json")
    public ResponseEntity<String> sendMessage(@PathVariable("id") final String id, @PathVariable("lang") final String language, @RequestBody final Message message) {

        try {
            return ResponseEntity.ok(conversationService.converse(message.getText(), id, language));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(path = "create")
    public ResponseEntity<Conversation> createConversation() {

        final Conversation newConversation = new Conversation(UUID.randomUUID().toString());
        LOGGER.info("created new conversation: {}", newConversation);
        return ResponseEntity.ok(newConversation);
    }
}
