package com.zberg.sample.chatbot.repositories.coredata;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class Agency {
    private String agenturNr;
    private String agenturName;
    private String strasse;
    private String hausnummer;
    private int plz;
    private String ort;
    private String telefon;
    private String fax;
    private String email;
    private String generalAgenturNr;
    private String generalAgenturName;
    private double latitude;
    private double longitude;
    private List<OpeningHours> zeiten = new ArrayList<>();

    public String getAgenturNr() {
        return agenturNr;
    }

    public void setAgenturNr(String agenturNr) {
        this.agenturNr = agenturNr;
    }

    public String getAgenturName() {
        return agenturName;
    }

    public void setAgenturName(String agenturName) {
        this.agenturName = agenturName;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGeneralAgenturNr() {
        return generalAgenturNr;
    }

    public void setGeneralAgenturNr(String generalAgenturNr) {
        this.generalAgenturNr = generalAgenturNr;
    }

    public String getGeneralAgenturName() {
        return generalAgenturName;
    }

    public void setGeneralAgenturName(String generalAgenturName) {
        this.generalAgenturName = generalAgenturName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<OpeningHours> getZeiten() {
        return zeiten;
    }

    public void setZeiten(List<OpeningHours> zeiten) {
        this.zeiten = zeiten;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("agenturNr", agenturNr)
                .append("agenturName", agenturName)
                .append("strasse", strasse)
                .append("hausnummer", hausnummer)
                .append("plz", plz)
                .append("ort", ort)
                .append("telefon", telefon)
                .append("fax", fax)
                .append("email", email)
                .append("generalAgenturNr", generalAgenturNr)
                .append("generalAgenturName", generalAgenturName)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .append("zeiten", zeiten)
                .toString();
    }
}
