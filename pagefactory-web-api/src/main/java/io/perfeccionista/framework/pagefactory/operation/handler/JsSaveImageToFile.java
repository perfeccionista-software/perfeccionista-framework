package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.UnsupportedScreenshotMimeType;
import io.perfeccionista.framework.screenshots.JpegScreenshot;
import io.perfeccionista.framework.screenshots.PngScreenshot;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.SCREENSHOT_MIME_TYPE_NOT_SUPPORTED;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsSaveImageToFile implements EndpointHandler<Void> {

    protected String filePath;
    protected String mimeType;
    protected ObjectNode options;

    public JsSaveImageToFile(@NotNull String filePath) {
        this.filePath = filePath;
        this.mimeType = "image/png";
        this.options = createObjectNode();
    }

    public JsSaveImageToFile setMimeType(@NotNull String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public JsSaveImageToFile setOptions(@NotNull ObjectNode options) {
        this.options = options;
        return this;
    }

    @Override
    public Void handle(Object endpoint) {
        String dataUrl = endpoint.toString();
        byte[] raw = java.util.Base64.getDecoder().decode(dataUrl.substring(dataUrl.indexOf(',') + 1));
        String mimeType = dataUrl.substring(dataUrl.indexOf(':') + 1, dataUrl.indexOf(';'));
        switch (mimeType) {
            case "image/png":
                PngScreenshot.from(raw).writeToFile(Path.of(filePath));
                break;
            case "image/jpeg":
                JpegScreenshot.from(raw).writeToFile(Path.of(filePath));
                break;
            default:
                throw UnsupportedScreenshotMimeType.exception(SCREENSHOT_MIME_TYPE_NOT_SUPPORTED.getMessage(mimeType));
        }
        return null;
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode()
                .put("name", "perfeccionista.web.js.GetScreenshot")
                .put("script", "js/GetScreenshot.js");
        options.put("requiredMimeType", mimeType);
        rootNode.set("options", options);
        return rootNode;
    }

}
