package com.zberg.sample.chatbot.service.response;

import com.zberg.sample.chatbot.service.chat.Response;
import com.zberg.sample.chatbot.service.response.text.AbstractResponse;
import com.zberg.sample.chatbot.service.response.text.TextResponse;
import org.springframework.stereotype.Service;


@Service
public class DefaultResponseHandler implements ResponseHandler {

    @Override
    public boolean handles(Response chatResponse) {
        return false; // should be fallback
    }

    @Override
    public AbstractResponse handleResponse(Response response) {
        final TextResponse textResponse = new TextResponse();
        textResponse.setText(response.getText());
        return textResponse;
    }
}
