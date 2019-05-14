package com.zberg.sample.chatbot.service.coverage;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CoverageService {

    private static final Set<String> COVERED_SYNONYMS = new HashSet<>();

    static {
        COVERED_SYNONYMS.add("brille");
        COVERED_SYNONYMS.add("massage");
    }

    private static final List<String> COVERAGE_SENTENCES = new ArrayList<>();

    static {
        COVERAGE_SENTENCES.add("Ja, %s ist gedeckt.");
        COVERAGE_SENTENCES.add("Natürlich ist %s gedeckt.");
        COVERAGE_SENTENCES.add("Laut deiner Police ist %s gedeckt");
    }

    private static final List<String> NON_COVERAGE_SENTENCES = new ArrayList<>();

    static {
        NON_COVERAGE_SENTENCES.add("Nein, %s ist leider nicht gedeckt.");
        NON_COVERAGE_SENTENCES.add("Pfff, %s ist nicht gedeckt.");
        NON_COVERAGE_SENTENCES.add("Laut deiner Police ist %s nicht gedeckt");
    }


    public List<String> getCoverage(final String... synonyms) {
        final List<String> response = new ArrayList<>(synonyms.length);
        for (String synonym : synonyms) {
            final List<String> sentences;
            if (COVERED_SYNONYMS.contains(synonym.toLowerCase())) {
                sentences = COVERAGE_SENTENCES;
            } else {
                sentences = NON_COVERAGE_SENTENCES;
            }
            int index = new Random().nextInt(sentences.size());
            final String sentence = String.format(sentences.get(index), synonym);
            response.add(sentence);
        }
        return response;
    }
}
