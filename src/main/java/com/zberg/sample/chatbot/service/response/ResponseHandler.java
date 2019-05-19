package com.zberg.sample.chatbot.service.response;

import com.zberg.sample.chatbot.service.chat.Response;
import com.zberg.sample.chatbot.service.response.text.AbstractResponse;

public interface ResponseHandler {

    boolean handles(Response chatResponse);

    AbstractResponse handleResponse(Response response);

}
