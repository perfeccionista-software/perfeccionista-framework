package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.WebClickAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebFileDownloadAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebFileUploadAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebGetTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebImageElement;
import io.perfeccionista.framework.pagefactory.elements.methods.WebInputTextAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.WebIsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.elements.options.DragAndDropOptions;
import org.jetbrains.annotations.NotNull;

public interface WebNode extends WebBlock<WebNode>, WebImageElement<WebNode>,
        WebClickAvailable<WebNode>, WebGetTextAvailable, WebInputTextAvailable<WebNode>,
        WebIsEnabledAvailable, WebIsSelectedAvailable, WebFileDownloadAvailable, WebFileUploadAvailable<WebNode> {

    WebNode dragAndDropTo(@NotNull WebNode targetNode);
    WebNode dragAndDropTo(@NotNull DragAndDropOptions options, @NotNull WebNode targetNode);

//    static WebNode node(@NotNull WebSelectorHolder rootSelector) {
//        return WebPageService.getInstance().createWebNode(rootSelector);
//    }

}
