package com.zberg.sample.chatbot.service.response;

import com.zberg.sample.chatbot.service.chat.Response;
import com.zberg.sample.chatbot.service.coverage.CoverageService;
import com.zberg.sample.chatbot.service.response.text.AbstractResponse;
import com.zberg.sample.chatbot.service.response.text.TextResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CoverageCheckResponseHandler implements ResponseHandler {

    private final CoverageService coverageService;

    public CoverageCheckResponseHandler(final CoverageService coverageService) {


        this.coverageService = coverageService;
    }

    @Override
    public boolean handles(final Response chatResponse) {

        return "check_coverage".equalsIgnoreCase(chatResponse.getIntent())
                && StringUtils.isNotEmpty(chatResponse.getParameters().get("coverage_synonyms"));
    }

    @Override
    public AbstractResponse handleResponse(final Response response) {

        final String synonymParam = response.getParameters().get("coverage_synonyms");
        final String[] synonyms = synonymParam.split(",");
        final TextResponse textResponse = new TextResponse();
        textResponse.setText(coverageService.getCoverage(synonyms));
        return textResponse;
    }

}
