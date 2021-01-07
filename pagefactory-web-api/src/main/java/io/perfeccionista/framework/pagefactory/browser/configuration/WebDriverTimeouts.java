package io.perfeccionista.framework.pagefactory.browser.configuration;

/**
 * Таймауты в миллисекундах
 */
public class WebDriverTimeouts {

    private long implicitlyTimeout = 0;
    private long pageLoadTimeout = 30_000;
    private long scriptTimeout = 30_000;

    public WebDriverTimeouts() {}

    public WebDriverTimeouts setImplicitlyTimeout(long implicitlyTimeout) {
        this.implicitlyTimeout = implicitlyTimeout;
        return this;
    }

    public WebDriverTimeouts setPageLoadTimeout(long pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
        return this;
    }

    public WebDriverTimeouts setScriptTimeout(long scriptTimeout) {
        this.scriptTimeout = scriptTimeout;
        return this;
    }

    public long getImplicitlyTimeout() {
        return implicitlyTimeout;
    }

    public long getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    public long getScriptTimeout() {
        return scriptTimeout;
    }

}
