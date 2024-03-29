package io.perfeccionista.framework.exceptions.messages;

public enum PageFactoryWebSeleniumMessages implements Messages {


    WEB_DRIVER_NOT_AVAILABLE("WebDriver instance not available"),
    WEB_DRIVER_INITIALIZATION_FAILED("WebDriver initialization failed"),
    WEB_DRIVER_INSTANCE_NOT_STARTED("WebDriver instance not started"),
    WEB_DRIVER_BINARY_NOT_DECLARED("WebDriver binary file not declared for '%s' os"),
    WEB_DRIVER_INTERNAL_ERROR("WebDriver internal error"),
    WEB_DRIVER_CAN_NOT_EXECUTE_OPERATION_ON_EMPTY_TAB("WebDriver instance can't execute this operation on empty tab"),
    WEB_DRIVER_CONNECTION_REFUSED("WebDriver's connection refused. Requested resource is not available"),

    WEB_ELEMENT_CLICK_INTERCEPTED("Click on element '%s' was intercepted by another element"),
    ELEMENT_IS_NOT_INTRACTABLE("Element '%s' is not intractable"),
    WEB_ELEMENT_INCORRECT_TYPE("WebElement '%s' has incorrect type. Required type is '%s'"),

    WEB_BROWSER_ENVIRONMENT_IS_STALE("WebBrowser environment is stale. Function 'executeOperation' is not loaded"),
    WEB_ELEMENT_IS_STALE("Element '%s' has stale WebElement reference"),


    WEB_LOCATOR_RESULT_CONTAIN_DUPLICATE_INDEXES("WebLocator result contain duplicate indexes"),

    WEB_DRIVER_CAN_NOT_OPEN_URL_ERROR("WebDriver can't open url: '%s'"),
    INCORRECT_REMOTE_WEB_DRIVER_INSTANCE_URL("Incorrect Remote WebDriver instance url: %s"),
    ;

    private String key;

    PageFactoryWebSeleniumMessages(String key) {
        this.key = key;
    }

    @Override
    public String getErrorMessage() {
        return key;
    }

}
