package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import io.perfeccionista.framework.exceptions.SeleniumWebElementIncorrectType;
import io.perfeccionista.framework.utils.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

import java.util.List;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryWebSeleniumMessages.WEB_ELEMENT_INCORRECT_TYPE;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;
import static java.util.stream.Collectors.joining;

public class SeleniumUploadFromClasspath implements EndpointHandler<Void> {

    private final List<String> resourcesToUpload;

    public SeleniumUploadFromClasspath(@NotNull List<String> resourcesToUpload) {
        this.resourcesToUpload = resourcesToUpload;
    }

    @Override
    public Void handle(Object endpoint) {
        WebElement webElement = (WebElement) endpoint;

        String tagName = webElement.getTagName();
        if (!"input".equalsIgnoreCase(tagName)) {
            throw SeleniumWebElementIncorrectType.exception(WEB_ELEMENT_INCORRECT_TYPE.getMessage(tagName, "input"));
        }

        String fileNames = resourcesToUpload.stream()
                .map(FileUtils::getRequiredFileFromClasspath)
                .map(path -> path.toAbsolutePath().toString())
                .collect(joining("\n"));

        webElement.sendKeys(fileNames);

        return null;
    }

    @Override
    public @NotNull JsonNode toJson() {
        return createObjectNode()
                .put("name", "perfeccionista.js.selenium.GetWebElement")
                .put("script", "js/GetWebElement.js");
    }

}
