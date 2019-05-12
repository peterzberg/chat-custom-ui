package com.zberg.sample.chatbot;

import com.zberg.sample.chatbot.repositories.coredata.AgencyRepository;
import com.zberg.sample.chatbot.repositories.coredata.impl.AgencyRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChatCustomUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatCustomUiApplication.class, args);
    }

}
