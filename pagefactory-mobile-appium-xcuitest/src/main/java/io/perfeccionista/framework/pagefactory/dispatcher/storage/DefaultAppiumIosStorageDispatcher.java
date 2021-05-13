package io.perfeccionista.framework.pagefactory.dispatcher.storage;

import io.appium.java_client.AppiumDriver;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DefaultAppiumIosStorageDispatcher implements MobileDeviceStorageDispatcher {

    protected final Environment environment;
    protected final AppiumDriver<?> instance;
    protected final MobileExceptionMapper exceptionMapper;

    public DefaultAppiumIosStorageDispatcher(Environment environment, AppiumDriver<?> instance, MobileExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public MobileDeviceStorageDispatcher sendFileToDevice(@NotNull String fromFilePath, @NotNull String toFilePath) {
        return null;
    }

    @Override
    public MobileDeviceStorageDispatcher saveFileFromDevice(@NotNull String fromFilePath, @NotNull String toFilePath) {
        return null;
    }

    @Override
    public byte[] getFileFromDevice(@NotNull String fromFilePath) {
        return new byte[0];
    }

    @Override
    public MobileDeviceStorageDispatcher saveFolderFromDevice(@NotNull String fromFolderPath, @NotNull String toFilePath) {
        return null;
    }

    @Override
    public byte[] getFolderFromDevice(@NotNull String fromFolderPath) {
        return new byte[0];
    }

    @Override
    @Nullable
    public String getClipboard() {
        return null;
    }

    @Override
    public void setClipboard(@NotNull String value) {

    }
}
