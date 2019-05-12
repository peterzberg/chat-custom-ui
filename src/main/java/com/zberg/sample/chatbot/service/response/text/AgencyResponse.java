package com.zberg.sample.chatbot.service.response.text;

import com.zberg.sample.chatbot.api.models.ResponseType;
import com.zberg.sample.chatbot.repositories.coredata.Agency;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AgencyResponse extends AbstractResponse {

    private Agency agency;

    public AgencyResponse() {
        super(ResponseType.AGENCY_INFO);
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("agency", agency)
                .toString();
    }
}
