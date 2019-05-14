package com.zberg.sample.chatbot.service.chat;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.cloud.dialogflow.v2.*;
import com.google.protobuf.ListValue;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import com.zberg.sample.chatbot.security.GoogleCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatService.class);
    private final String projectId;

    public ChatService(final Environment env) {

        projectId = env.getProperty("DIALOGFLOW_PROJECT_ID");
    }


    public Response sendMessage(final String text, final String sessionId, final String language) throws IOException {
        final CredentialsProvider credentialsProvider = FixedCredentialsProvider.create(GoogleCredentialsProvider.get());
        final SessionsSettings sessionSettings = SessionsSettings.newBuilder().setCredentialsProvider(credentialsProvider).build();
        try (SessionsClient sessionsClient = SessionsClient.create(sessionSettings)) {
            LOGGER.info("Goint to send message to dialogflow: {}", text);
            final SessionName session = SessionName.of(projectId, sessionId);
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
            chatResponse.setAllRequiredParamsSet(queryResult.getAllRequiredParamsPresent());
            final List<String> messages = queryResult.getFulfillmentMessagesList().stream().flatMap(m -> m.getText().getTextList().stream()).collect(Collectors.toList());
            chatResponse.setText(messages);
            return chatResponse;
        }
    }

    private Map<String, String> extractParameters(final Struct parameters) {
        final Map<String, String> result = new HashMap<>();
        if (null != parameters) {
            final Map<String, Value> fieldsMap = parameters.getFieldsMap();
            fieldsMap.forEach((key, value) -> {
                if (value.hasListValue()) {
                    final ListValue listValues = value.getListValue();
                    final List<String> stringValues = new ArrayList<>();
                    for (int i = 0; i < listValues.getValuesCount(); i++) {
                        final Value listValue = listValues.getValues(i);
                        stringValues.add(listValue.getStringValue());
                    }
                    final String paramValue = String.join(",", stringValues);
                    result.put(key, paramValue);
                } else {
                    result.put(key, value.getStringValue());
                }
            });
        }
        return result;
    }
}
