package com.zberg.sample.chatbot.security;

import com.google.auth.oauth2.ServiceAccountCredentials;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class GoogleCredentialsProvider {

    private static ServiceAccountCredentials credentials;

    private GoogleCredentialsProvider() {
    }

    public static ServiceAccountCredentials get() {
        if (null == credentials) {
            credentials = loadCredentials();
        }
        return credentials;
    }

    private static ServiceAccountCredentials loadCredentials() {
        final String credentialsJson = System.getenv("DIALOGFLOW_CREDENTIALS");
        if (null == credentialsJson) {
            throw new IllegalStateException("Environment variable 'DIALOGFLOW_CREDENTIALS' is not set");
        }
        try {
            return ServiceAccountCredentials.fromStream(new ByteArrayInputStream(credentialsJson.getBytes(Charset.forName("UTF-8"))));
        } catch (IOException e) {
            throw new IllegalStateException("could not read credentials");
        }
    }

}
