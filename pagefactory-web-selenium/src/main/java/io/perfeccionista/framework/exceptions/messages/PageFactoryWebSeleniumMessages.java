package io.perfeccionista.framework.exceptions.messages;

public enum PageFactoryWebSeleniumMessages implements Messages {

    WEB_DRIVER_NOT_AVAILABLE("WebDriver instance not available"),
    WEB_DRIVER_INITIALIZATION_FAILED("WebDriver initialization failed"),
    WEB_DRIVER_INSTANCE_NOT_STARTED("WebDriver instance not started"),
    WEB_DRIVER_BINARY_NOT_DECLARED("WebDriver binary file not declared for %s os"),
    WEB_DRIVER_INTERNAL_ERROR("WebDriver internal error"),

    WEB_ELEMENT_CLICK_INTERCEPTED("Click on element %s was intercepted by another element"),
    WEB_ELEMENT_NOT_INTRACTABLE("Element %s is not intractable"),
    WEB_ELEMENT_IS_STALE("Element %s has stale WebElement reference"),


    WEB_LOCATOR_RESULT_CONTAIN_DUPLICATE_INDEXES("WebLocator result contain duplicate indexes"),
    INCORRECT_WEB_DRIVER_URL("Incorrect WebDriver url: %s"),
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
