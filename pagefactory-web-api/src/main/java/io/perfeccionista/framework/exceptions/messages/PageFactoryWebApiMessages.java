package io.perfeccionista.framework.exceptions.messages;

public enum PageFactoryWebApiMessages implements Messages {

    CONTEXT_LIMITER_RETURN_MORE_THAN_ONE_SEARCH_CONTEXT("Context limiter return more than one search context"),
    CONTEXT_LIMITER_RETURN_NO_ONE_SEARCH_CONTEXT("Context limiter return no one search context"),
    ;

    private String key;

    PageFactoryWebApiMessages(String key) {
        this.key = key;
    }

    @Override
    public String getErrorMessage() {
        return key;
    }

}
