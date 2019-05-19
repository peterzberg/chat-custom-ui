package com.zberg.sample.chatbot.repositories.coredata;

import java.util.Optional;

public interface AgencyRepository {

    Optional<Agency> getAgencyByCityName(String name);

}
