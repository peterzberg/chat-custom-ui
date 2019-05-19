package com.zberg.sample.chatbot.repositories.coredata;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class OpeningHours {

    private String wochentag;
    private String vormittagVon;
    private String vormittagBis;
    private String nachmittagVon;
    private String nachmittagBis;

    public String getWochentag() {

        return wochentag;
    }

    public void setWochentag(final String wochentag) {

        this.wochentag = wochentag;
    }

    public String getVormittagVon() {

        return vormittagVon;
    }

    public void setVormittagVon(final String vormittagVon) {

        this.vormittagVon = vormittagVon;
    }

    public String getVormittagBis() {

        return vormittagBis;
    }

    public void setVormittagBis(final String vormittagBis) {

        this.vormittagBis = vormittagBis;
    }

    public String getNachmittagVon() {

        return nachmittagVon;
    }

    public void setNachmittagVon(final String nachmittagVon) {

        this.nachmittagVon = nachmittagVon;
    }

    public String getNachmittagBis() {

        return nachmittagBis;
    }

    public void setNachmittagBis(final String nachmittagBis) {

        this.nachmittagBis = nachmittagBis;
    }

    @Override
    public String toString() {

        return new ToStringBuilder(this)
                .append("wochentag", wochentag)
                .append("vormittagVon", vormittagVon)
                .append("vormittagBis", vormittagBis)
                .append("nachmittagVon", nachmittagVon)
                .append("nachmittagBis", nachmittagBis)
                .toString();
    }

}
