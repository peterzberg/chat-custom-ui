package com.zberg.sample.chatbot.service.response;

import com.zberg.sample.chatbot.repositories.coredata.Agency;
import com.zberg.sample.chatbot.repositories.coredata.AgencyRepository;
import com.zberg.sample.chatbot.service.ResponseHandler;
import com.zberg.sample.chatbot.service.chat.Response;
import com.zberg.sample.chatbot.service.response.text.AbstractResponse;
import com.zberg.sample.chatbot.service.response.text.AgencyResponse;
import com.zberg.sample.chatbot.service.response.text.TextResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AgencyInfoResponseHandler implements ResponseHandler {

    private final AgencyRepository agencyRepository;

    public AgencyInfoResponseHandler(final AgencyRepository agencyRepository) {

        this.agencyRepository = agencyRepository;
    }

    @Override
    public boolean handles(Response chatResponse) {
        return "agency_info".equalsIgnoreCase(chatResponse.getIntent())
                && StringUtils.isNotEmpty(chatResponse.getParameters().get("geo-city"));
    }

    @Override
    public AbstractResponse handleResponse(Response response) {

        final String cityName = response.getParameters().get("geo-city"); // lookup should be better. for poc ok :)
        final Optional<Agency> agency = agencyRepository.getAgencyByCityName(cityName);

        final AbstractResponse result;

        if (agency.isPresent()) {
            result = createAgencyResponse(agency.get());
        } else {
            result = buildFallbackTextResponse(cityName);
        }
        return result;
    }

    private AbstractResponse createAgencyResponse(final Agency agency) {
        final AgencyResponse agencyResponse = new AgencyResponse();
        agencyResponse.setAgency(agency);
        return agencyResponse;
    }

    private AbstractResponse buildFallbackTextResponse(final String cityName) {
        final String text = "Leider habe ich zu Agentur '" + cityName + "' nichts gefunden."; // TODO: Lookup from resource file by language with multiple options to look smarter
        final TextResponse textResponse = new TextResponse();
        textResponse.setText(Collections.singletonList(text));
        return textResponse;
    }
}
