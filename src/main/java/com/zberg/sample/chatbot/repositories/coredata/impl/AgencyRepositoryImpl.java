package com.zberg.sample.chatbot.repositories.coredata.impl;

import com.zberg.sample.chatbot.repositories.coredata.Agency;
import com.zberg.sample.chatbot.repositories.coredata.AgencyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
public class AgencyRepositoryImpl implements AgencyRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(AgencyRepositoryImpl.class);

    private final String coreDataAccessorUrl;

    public AgencyRepositoryImpl(@Value("${core.data.accessor.url}") final String coreDataAccessorUrl) {

        this.coreDataAccessorUrl = coreDataAccessorUrl;
    }

    @Override
    public Optional<Agency> getAgencyByCityName(final String cityName) {

        LOGGER.info("Looking up an agency for city '{}'", cityName);
        final RestTemplate template = new RestTemplate();
        final String path = coreDataAccessorUrl + "/search?search={search}";
        LOGGER.debug("Using endpoint '{}' for query", coreDataAccessorUrl);
        final ResponseEntity<AgencySearch[]> responseEntity = template.exchange(path, HttpMethod.GET, null, AgencySearch[].class, cityName);

        final AgencySearch[] agencies = responseEntity.getBody();

        if (agencies != null && agencies.length > 0) {
            return getAgencyByNr(agencies[0].getAgenturNr());
        }
        return Optional.empty();
    }

    private Optional<Agency> getAgencyByNr(final String agencyNr) {

        final RestTemplate template = new RestTemplate();
        try {
            final Agency agency = template.getForObject(coreDataAccessorUrl + "/" + agencyNr, Agency.class);
            return Optional.ofNullable(agency);
        } catch (Exception e) {
            return Optional.empty();
        }

    }
}
