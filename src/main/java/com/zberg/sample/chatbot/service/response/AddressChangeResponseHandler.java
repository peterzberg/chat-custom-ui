package com.zberg.sample.chatbot.service.response;


import com.zberg.sample.chatbot.service.chat.Response;
import com.zberg.sample.chatbot.service.response.text.AbstractResponse;
import com.zberg.sample.chatbot.service.response.text.AddressChangeResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AddressChangeResponseHandler implements ResponseHandler {

    @Override
    public boolean handles(final Response chatResponse) {

        return "address_change".equalsIgnoreCase(chatResponse.getIntent())
                && chatResponse.isAllRequiredParamsSet();
    }

    @Override
    public AbstractResponse handleResponse(final Response response) {

        final AddressChangeResponse result = new AddressChangeResponse();
        result.setCity(StringUtils.trimToNull(response.getParameters().get("geo-city")));
        result.setZip(StringUtils.trimToNull(response.getParameters().get("zip-code")));
        result.setNumber(StringUtils.trimToNull(response.getParameters().get("number")));
        result.setStreet(StringUtils.trimToNull(response.getParameters().get("address")));
        return result;
    }

}
