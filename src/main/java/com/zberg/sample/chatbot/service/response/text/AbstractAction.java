package com.zberg.sample.chatbot.service.response.text;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AbstractAction extends AbstractResponse {

    private final Action action;


    AbstractAction(final Action action){
        super(ResponseType.ACTION);
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("action", action)
                .toString();
    }
}
