package io.perfeccionista.framework.pagefactory.dispatcher.configuration;

/**
 * Таймауты в миллисекундах
 * Они завязаны по сути на типы таймаутов селениума.
 * TODO: Сделать универсальный интерфейс для установки таймаутов
 */
public class WebBrowserTimeouts {

    private long operationTimeout = 30_000;
    private long sessionTimeout = 60_000;

    public long getOperationTimeout() {
        return operationTimeout;
    }

    public long getSessionTimeout() {
        return sessionTimeout;
    }

    public WebBrowserTimeouts setOperationTimeout(long operationTimeout) {
        this.operationTimeout = operationTimeout;
        return this;
    }

    public WebBrowserTimeouts setSessionTimeout(long sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
        return this;
    }

}
