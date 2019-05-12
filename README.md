# chat-custom-ui

# Purpose

A chatbot is more or less bound to simple text conversations. If you want to create more complex tasks, use input will lead to error prone nonesense.

This PoC should prove, that it is possible - with a man in the middle - to start more complex tasks in a chatwindow than just conversations.

# What to do?

## Build project
```
mvn clean package
```

## Start project with env variables set
```
GOOGLE_APPLICATION_CREDENTIALS=/path/to/credentials
DIALOGFLOW_PROJECT_ID=my-project-name
```

# Configure dialogflow

This project (WIP) listens to an intent called 'agency_info', which should have a parameter 'geo-city' set. If set, the response should be enhanced with informations about wanted agency.

In the future, more custom elements should find their way into custom responses like calculators, check coverage (insurances), ...

But in the end, this is just a PoC :)

# Progress

API already does something (not much). But we should be able to build the first FE task to handle agency information queries (show google maps, opening hours, closes soon?, ...)