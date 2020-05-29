package io.perfeccionista.framework.exceptions.messages;

public enum PageFactoryWebApiMessages implements Messages {

    CONTEXT_LIMITER_RETURN_MORE_THAN_ONE_SEARCH_CONTEXT("Context limiter return more than one search context"),
    CONTEXT_LIMITER_RETURN_NO_ONE_SEARCH_CONTEXT("Context limiter return no one search context"),
    WEB_BROWSER_CONFIGURATION_NOT_DECLARED("Web browser configuration with name %s is not declared"),
    NO_ACTIVE_WEB_BROWSER_DISPATCHER_FOUND("No active web browser dispatcher found"),
    NO_WEB_BROWSER_DISPATCHER_WITH_NAME_CREATED("No web browser dispatcher with name %s created"),
    NO_EXCEPTION_MAPPER_BY_CLASS_DECLARED("No exception mapper by class %s declared"),
    WEB_BROWSER_SERVICE_CONFIGURATION_NOT_DECLARED("Web browser service configuration not declared"),
    WEB_LOCATOR_PROCESSING_RESULT_NOT_FOUND("Processing result for locator is not found"),
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
