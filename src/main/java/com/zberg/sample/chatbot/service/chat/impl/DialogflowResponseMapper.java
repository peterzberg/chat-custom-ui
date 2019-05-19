package com.zberg.sample.chatbot.service.chat.impl;

import com.google.cloud.dialogflow.v2.Context;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.protobuf.ListValue;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import com.zberg.sample.chatbot.service.chat.Response;

import java.util.*;
import java.util.stream.Collectors;

final class DialogflowResponseMapper {

    private DialogflowResponseMapper() {
        //
    }

    static Response toResponse(final DetectIntentResponse detectIntentResponse) {

        final QueryResult queryResult = detectIntentResponse.getQueryResult();
        final Response chatResponse = new Response();
        chatResponse.setIntent(queryResult.getIntent().getDisplayName());
        final Map<String, String> paramMap = extractParameters(queryResult.getParameters());
        final String currentSlot = determineCurrentSlot(queryResult.getParameters(), queryResult);
        chatResponse.setParameters(paramMap);
        chatResponse.setCurrentSlot(currentSlot);
        chatResponse.setAllRequiredParamsSet(queryResult.getAllRequiredParamsPresent());
        final List<String> messages = queryResult.getFulfillmentMessagesList().stream()
                .flatMap(m -> m.getText().getTextList().stream())
                .collect(Collectors.toList());
        chatResponse.setText(messages);
        return chatResponse;
    }

    private static String determineCurrentSlot(final Struct parameters, final QueryResult queryResult) {

        final Map<String, Value> fieldsMap = parameters.getFieldsMap();
        if (null != fieldsMap && !fieldsMap.isEmpty()) {
            final List<Context> outputContextsList = queryResult.getOutputContextsList();
            final Optional<String> currentParam = outputContextsList.stream()
                    .filter(c -> hasParamInName(c, fieldsMap))
                    .map(DialogflowResponseMapper::extractParameterNameFromContext)
                    .findFirst();
            return currentParam.orElse(null);
        }
        return null;
    }

    private static String extractParameterNameFromContext(final Context context) {

        final String[] params = context.getName().split("dialog_params_");
        return params[1];
    }

    private static boolean hasParamInName(final Context context, final Map<String, Value> fieldsMap) {

        final String name = context.getName();
        return fieldsMap.keySet().stream().anyMatch(name::endsWith);
    }

    private static Map<String, String> extractParameters(final Struct parameters) {

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
