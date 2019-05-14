package com.zberg.sample.chatbot.service.response.text;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AddressChangeResponse extends AbstractAction {
    private String street;
    private String number;
    private String zip;
    private String city;

    public AddressChangeResponse() {
        super(Action.ADDRESS_CHANGE);
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("street", street)
                .append("number", number)
                .append("zip", zip)
                .append("city", city)
                .toString();
    }
}
