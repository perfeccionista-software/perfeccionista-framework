package io.perfeccionista.framework.pagefactory.dispatcher.storage;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;
import io.perfeccionista.framework.pagefactory.dispatcher.driver.AndroidEspressoDriver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DefaultAppiumAndroidStorageDispatcher implements MobileDeviceStorageDispatcher {

    protected final Environment environment;
    protected final AndroidEspressoDriver instance;
    protected final MobileExceptionMapper exceptionMapper;

    public DefaultAppiumAndroidStorageDispatcher(Environment environment, AndroidEspressoDriver instance, MobileExceptionMapper exceptionMapper) {
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
        return instance.getClipboardText();
    }

    @Override
    public void setClipboard(@NotNull String value) {
        instance.setClipboardText(value);
    }
}
