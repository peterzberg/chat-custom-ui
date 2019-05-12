package com.zberg.sample.chatbot.service.chat;

import com.google.cloud.dialogflow.v2.*;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatService.class);
    private static final String DIALOGFLOW_PROJECT_ID = "css-sia-poc";

    public Response sendMessage(final String text, final String sessionId, final String language) throws IOException {
        try (SessionsClient sessionsClient = SessionsClient.create()) {
            LOGGER.info("Goint to send message to dialogflow: {}", text);
            final SessionName session = SessionName.of(DIALOGFLOW_PROJECT_ID, sessionId);
            final TextInput textInput = TextInput.newBuilder().setText(text).setLanguageCode(language).build();
            final QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
            final DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
            LOGGER.info("Response received: {}", response);
            final QueryResult queryResult = response.getQueryResult();
            LOGGER.info("Detected intent: {}", queryResult.getIntent());
            final Response chatResponse = new Response();
            chatResponse.setIntent(queryResult.getIntent().getDisplayName());
            final Map<String, String> paramMap = extractParameters(queryResult.getParameters());
            chatResponse.setParameters(paramMap);

            final List<String> messages = queryResult.getFulfillmentMessagesList().stream().flatMap(m -> m.getText().getTextList().stream()).collect(Collectors.toList());
            chatResponse.setText(messages);
            return chatResponse;
        }
    }

    private Map<String, String> extractParameters(final Struct parameters) {
        final Map<String, String> result = new HashMap<>();
        if (null != parameters){
            final Map<String, Value> fieldsMap = parameters.getFieldsMap();
            fieldsMap.forEach((key, value) -> result.put(key, value.getStringValue()));
        }
        return result;
    }
}
