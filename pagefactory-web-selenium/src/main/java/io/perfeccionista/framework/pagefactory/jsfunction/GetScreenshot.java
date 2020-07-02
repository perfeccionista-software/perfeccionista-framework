package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.UnsupportedScreenshotMimeTypeException;
import io.perfeccionista.framework.pagefactory.screenshots.JpegScreenshot;
import io.perfeccionista.framework.pagefactory.screenshots.PngScreenshot;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.SCREENSHOT_MIME_TYPE_NOT_SUPPORTED;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

/**
 * TODO: Почему-то сразу после скролла отрисовка скриншота происходит некорректно
 * // TODO: Переписать форматирование на таблицу
 * | Name | Default | Description |<br/>
 * | allowTaint | false | Whether to allow cross-origin images to taint the canvas |<br/>
 * | backgroundColor	#ffffff	Canvas background color, if none is specified in DOM. Set null for transparent
 * canvas	null	Existing canvas element to use as a base for drawing on
 * foreignObjectRendering	false	Whether to use ForeignObject rendering if the browser supports it
 * imageTimeout	15000	Timeout for loading an image (in milliseconds). Set to 0 to disable timeout.
 * ignoreElements	(element) => false	Predicate function which removes the matching elements from the render.
 * logging	true	Enable logging for debug purposes
 * onclone	null	Callback function which is called when the Document has been cloned for rendering, can be used to modify the contents that will be rendered without affecting the original source document.
 * proxy	null	Url to the proxy which is to be used for loading cross-origin images. If left empty, cross-origin images won't be loaded.
 * removeContainer	true	Whether to cleanup the cloned DOM elements html2canvas creates temporarily
 * scale	window.devicePixelRatio	The scale to use for rendering. Defaults to the browsers device pixel ratio.
 * useCORS	false	Whether to attempt to load images from a server using CORS
 * width	Element width	The width of the canvas
 * height	Element height	The height of the canvas
 * x	Element x-offset	Crop canvas x-coordinate
 * y	Element y-offset	Crop canvas y-coordinate
 * scrollX	Element scrollX	The x-scroll position to used when rendering element, (for example if the Element uses position: fixed)
 * scrollY	Element scrollY	The y-scroll position to used when rendering element, (for example if the Element uses position: fixed)
 * windowWidth	Window.innerWidth	Window width to use when rendering Element, which may affect things like Media queries
 * windowHeight	Window.innerHeight	Window height to use when rendering Element, which may affect things like Media queries
 */
public class GetScreenshot implements JsFunction<Screenshot> {

    protected final String requiredMimeType;
    protected final ObjectNode options;

    public GetScreenshot() {
        this.requiredMimeType = "image/png";
        this.options = createObjectNode();
    }

    public GetScreenshot(@NotNull String requiredMimeType) {
        this.requiredMimeType = requiredMimeType;
        this.options = createObjectNode();
    }

    public GetScreenshot(@NotNull String requiredMimeType, @NotNull ObjectNode options) {
        this.requiredMimeType = requiredMimeType;
        this.options = options;
    }

    @Override
    public Function<Object, Screenshot> getConverter() {
        return object -> {
            String dataUrl = object.toString();
            byte[] raw = java.util.Base64.getDecoder().decode(dataUrl.substring(dataUrl.indexOf(',') + 1));
            String mimeType = dataUrl.substring(dataUrl.indexOf(':') + 1, dataUrl.indexOf(';'));
            switch (mimeType) {
                case "image/png":
                    return PngScreenshot.from(raw);
                case "image/jpeg":
                    return JpegScreenshot.from(raw);
                default:
                    throw new UnsupportedScreenshotMimeTypeException(SCREENSHOT_MIME_TYPE_NOT_SUPPORTED.getMessage(mimeType));
            }
        };
    }

    @Override
    public ObjectNode getJsFunctionInvocation() {
        ObjectNode rootNode = createObjectNode()
                .put("name", "perfeccionista.js.selenium.GetScreenshot");
        options.put("requiredMimeType", requiredMimeType);
        rootNode.set("options", options);
        return rootNode;
    }

    @Override
    public String getScriptName() {
        return "perfeccionista.js.selenium.GetScreenshot";
    }

    @Override
    public String getScriptDestination() {
        return "js/GetScreenshot.js";
    }

}

