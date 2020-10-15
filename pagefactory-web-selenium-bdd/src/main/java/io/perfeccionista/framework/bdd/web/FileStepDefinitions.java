package io.perfeccionista.framework.bdd.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.EnvironmentAvailable;
import io.perfeccionista.framework.bdd.parameters.DurationParameter;
import io.perfeccionista.framework.bdd.parameters.ValueStringParameter;

import java.nio.file.Path;

import static io.perfeccionista.framework.invocation.wrappers.CheckInvocationWrapper.runCheck;
import static io.perfeccionista.framework.utils.FileUtils.*;
import static io.perfeccionista.framework.utils.FileUtils.deleteIgnoreExceptions;

public class FileStepDefinitions implements EnvironmentAvailable {

    /**
     *
     * @param filePath -
     */
    @Given("file {stringValue} exists")
    @Given("файл {stringValue} существует")
    public void fileExists(ValueStringParameter filePath) {
        runCheck(getEnvironment(), () -> shouldExist(Path.of(filePath.getProcessedValue())));
    }

    /**
     *
     * @param filePath -
     */
    @Given("file {stringValue} exists. Check time {duration}")
    @Given("файл {stringValue} существует. Время проверки {duration}")
    public void fileExistsWithTimeout(ValueStringParameter filePath, DurationParameter duration) {
        runCheck(getEnvironment(), () -> {
            shouldExist(Path.of(filePath.getProcessedValue()));
        }, duration.getDuration());
    }

    /**
     *
     * @param filePath -
     */
    @Given("file {stringValue} not exists")
    @Given("файл {stringValue} отсутствует")
    public void fileNotExists(ValueStringParameter filePath) {
        runCheck(getEnvironment(), () -> shouldBeMissing(Path.of(filePath.getProcessedValue())));
    }

    /**
     *
     * @param filePath -
     */
    @Given("file {stringValue} not exists. Check time {duration}")
    @Given("файл {stringValue} отсутствует. Время проверки {duration}")
    public void fileNotExistsWithTimeout(ValueStringParameter filePath, DurationParameter duration) {
        runCheck(getEnvironment(), () -> {
            shouldBeMissing(Path.of(filePath.getProcessedValue()));
        }, duration.getDuration());
    }

    /**
     *
     * @param filePath -
     */
    @Given("user deletes file {stringValue}")
    @Given("пользователь удаляет файл {stringValue}")
    public void deleteFile(ValueStringParameter filePath) {
        deleteIgnoreExceptions(Path.of(filePath.getProcessedValue()));
    }

}
