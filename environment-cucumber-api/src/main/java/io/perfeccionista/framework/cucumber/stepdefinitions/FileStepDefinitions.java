package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.ru.Дано;
import io.perfeccionista.framework.assertions.FileAssertions;
import io.perfeccionista.framework.cucumber.parameters.DurationParameter;
import io.perfeccionista.framework.cucumber.parameters.ValueStringParameter;

import java.nio.file.Paths;

import static io.perfeccionista.framework.utils.FileUtils.deleteFileIgnoreExceptions;

public class FileStepDefinitions implements CucumberStepDefinitions {

    /**
     *
     * @param filePath -
     */
    @Дано("файл {stringValue} существует")
    @Given("file {stringValue} exists")
    public void fileExists(ValueStringParameter filePath) {
        FileAssertions.fileExists(filePath.getNotNullProcessedValue());
    }

    /**
     *
     * @param filePath -
     */
    @Дано("файл {stringValue} существует. Продолжительность {duration}")
    @Given("file {stringValue} exists. Duration {duration}")
    public void fileExistsWithTimeout(ValueStringParameter filePath, DurationParameter duration) {
        FileAssertions.fileExistsWithTimeout(filePath.getNotNullProcessedValue(), duration.getDuration());
    }

    /**
     *
     * @param filePath -
     */
    @Дано("файл {stringValue} отсутствует")
    @Given("file {stringValue} is not exist")
    public void fileMissing(ValueStringParameter filePath) {
        FileAssertions.fileMissing(filePath.getNotNullProcessedValue());
    }

    /**
     *
     * @param filePath -
     */
    @Дано("файл {stringValue} отсутствует. Продолжительность {duration}")
    @Given("file {stringValue} is not exist. Duration {duration}")
    public void fileMissingWithTimeout(ValueStringParameter filePath, DurationParameter duration) {
        FileAssertions.fileMissingWithTimeout(filePath.getNotNullProcessedValue(), duration.getDuration());
    }

    /**
     *
     * @param filePath -
     */
    @Дано("пользователь удаляет файл {stringValue}")
    @Given("user deletes file {stringValue}")
    public void deleteFile(ValueStringParameter filePath) {
        deleteFileIgnoreExceptions(Paths.get(filePath.getProcessedValue()));
    }

}
