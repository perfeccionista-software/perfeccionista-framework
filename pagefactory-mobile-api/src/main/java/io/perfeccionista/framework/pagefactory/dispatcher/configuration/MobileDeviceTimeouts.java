package io.perfeccionista.framework.pagefactory.dispatcher.configuration;

/**
 * Таймауты в миллисекундах
 * Они завязаны по сути на типы таймаутов селениума.
 * TODO: Сделать универсальный интерфейс для установки таймаутов
 */
public class MobileDeviceTimeouts {

    private long operationTimeout = 30_000;
    private long sessionTimeout = 45_000;

    public MobileDeviceTimeouts setOperationTimeout(long operationTimeout) {
        this.operationTimeout = operationTimeout;
        return this;
    }

    public MobileDeviceTimeouts setSessionTimeout(long sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
        return this;
    }

    public long getOperationTimeout() {
        return operationTimeout;
    }

    public long getSessionTimeout() {
        return sessionTimeout;
    }

}
