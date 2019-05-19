package com.zberg.sample.chatbot.service.chat.impl;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.cloud.dialogflow.v2.*;
import com.zberg.sample.chatbot.security.GoogleCredentialsProvider;
import com.zberg.sample.chatbot.service.chat.ChatException;
import com.zberg.sample.chatbot.service.chat.ChatService;
import com.zberg.sample.chatbot.service.chat.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DialogFlowChatServiceImpl implements ChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DialogFlowChatServiceImpl.class);
    private final String projectId;

    public DialogFlowChatServiceImpl(final Environment env) {

        projectId = env.getProperty("DIALOGFLOW_PROJECT_ID");
    }


    @Override
    public Response sendMessage(final String text, final String sessionId, final String language) throws ChatException {

        try {
            return sendMessageInternal(text, sessionId, language);
        } catch (final IOException e) {
            throw new ChatException("Error while calling dialogflow", e);
        }
    }

    private Response sendMessageInternal(final String text, final String sessionId, final String language) throws IOException {

        final CredentialsProvider credentialsProvider = FixedCredentialsProvider.create(GoogleCredentialsProvider.get());
        final SessionsSettings sessionSettings = SessionsSettings.newBuilder().setCredentialsProvider(credentialsProvider).build();
        try (final SessionsClient sessionsClient = SessionsClient.create(sessionSettings)) {
            LOGGER.info("Goint to send message to dialogflow: {}", text);
            final SessionName session = SessionName.of(projectId, sessionId);
            final TextInput textInput = TextInput.newBuilder().setText(text).setLanguageCode(language).build();
            final QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
            final DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
            LOGGER.info("Response received: {}", response);
            final QueryResult queryResult = response.getQueryResult();
            LOGGER.info("Detected intent: {}", queryResult.getIntent());
            final Response chatResponse = DialogflowResponseMapper.toResponse(response);
            LOGGER.info("Mapped response to {}", chatResponse);
            return chatResponse;
        }
    }

}
