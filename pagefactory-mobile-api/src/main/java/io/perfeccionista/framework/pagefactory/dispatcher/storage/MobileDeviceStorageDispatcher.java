package io.perfeccionista.framework.pagefactory.dispatcher.storage;

import org.jetbrains.annotations.NotNull;

public interface MobileDeviceStorageDispatcher {

    MobileDeviceStorageDispatcher sendFileToDevice(@NotNull String fromFilePath, @NotNull String toFilePath);

    MobileDeviceStorageDispatcher saveFileFromDevice(@NotNull String fromFilePath, @NotNull String toFilePath);

    byte[] getFileFromDevice(@NotNull String fromFilePath);

    /**
     *
     * @param fromFolderPath path to directory
     * @param toFilePath     zip-file
     */
    MobileDeviceStorageDispatcher saveFolderFromDevice(@NotNull String fromFolderPath, @NotNull String toFilePath);

    byte[] getFolderFromDevice(@NotNull String fromFolderPath);

}
