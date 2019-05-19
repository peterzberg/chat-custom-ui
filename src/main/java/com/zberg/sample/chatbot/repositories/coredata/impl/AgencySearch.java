package com.zberg.sample.chatbot.repositories.coredata.impl;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AgencySearch {

    private String name;
    private String agenturNr;

    public String getName() {

        return name;
    }

    public void setName(final String name) {

        this.name = name;
    }

    public String getAgenturNr() {

        return agenturNr;
    }

    public void setAgenturNr(final String agenturNr) {

        this.agenturNr = agenturNr;
    }

    @Override
    public String toString() {

        return new ToStringBuilder(this)
                .append("name", name)
                .append("agenturNr", agenturNr)
                .toString();
    }

}
