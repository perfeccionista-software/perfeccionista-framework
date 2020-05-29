package io.perfeccionista.framework.exceptions.messages;

public enum PageFactoryWebSeleniumMessages implements Messages {

    WEB_DRIVER_INSTANCE_NOT_STARTED("WebDriver instance not started"),
    WEB_DRIVER_BINARY_NOT_DECLARED("WebDriver binary file not declared for %s os"),
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
