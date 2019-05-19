package com.zberg.sample.chatbot.service.response;

import com.zberg.sample.chatbot.service.chat.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseHandlerService {

    private final List<ResponseHandler> responseHandlers;

    public ResponseHandlerService(final List<ResponseHandler> responseHandlers) {

        this.responseHandlers = responseHandlers;
    }


    public ResponseHandler getResponseHandlerFor(final Response chatResponse) {

        return responseHandlers.stream().filter(rh -> rh.handles(chatResponse)).findFirst().orElse(new DefaultResponseHandler());

    }

}
