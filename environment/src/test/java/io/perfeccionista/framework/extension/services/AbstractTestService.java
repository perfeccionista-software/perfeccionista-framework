package io.perfeccionista.framework.extension.services;

import io.perfeccionista.framework.Environment;
import org.jetbrains.annotations.NotNull;
import io.perfeccionista.framework.service.Service;
import io.perfeccionista.framework.service.ServiceConfiguration;

public class AbstractTestService implements Service {

    private long initializationTime = 0;

    protected boolean initialized = false;
    protected boolean beforeTestExecuted = false;
    protected boolean afterTestExecuted = false;

    @Override
    public void init(@NotNull Environment environment, @NotNull ServiceConfiguration configuration) {
        initialized = true;
        initializationTime = System.nanoTime();
    }

    @Override
    public void beforeTest() {
        beforeTestExecuted = true;
    }

    @Override
    public void afterTest() {
        afterTestExecuted = true;
    }

    public long getInitializationTime() {
        return initializationTime;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public boolean isBeforeTestExecuted() {
        return beforeTestExecuted;
    }

    public boolean isAfterTestExecuted() {
        return afterTestExecuted;
    }

}
