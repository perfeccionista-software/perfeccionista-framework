package io.perfeccionista.framework.pagefactory.dispatcher.executor;

import io.appium.java_client.android.AndroidElement;
import io.perfeccionista.framework.exceptions.LocatorProcessing;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AndroidLocatorProcessingResult {

    private final Map<Integer, AndroidElement> elements;
    private final RuntimeException exception;

    private AndroidLocatorProcessingResult(Map<Integer, AndroidElement> elements, RuntimeException exception) {
        this.elements = elements;
        this.exception = exception;
    }

    public static AndroidLocatorProcessingResult of(@NotNull AndroidElement element) {
        Map<Integer, AndroidElement> elementsMap = new HashMap<>();
        elementsMap.put(-1, element);
        return new AndroidLocatorProcessingResult(elementsMap, null);
    }

    public static AndroidLocatorProcessingResult of(@NotNull Map<Integer, AndroidElement> elements) {
        return new AndroidLocatorProcessingResult(elements, null);
    }

    public static AndroidLocatorProcessingResult of(@NotNull Map<Integer, AndroidElement> elements,  @NotNull RuntimeException exception) {
        return new AndroidLocatorProcessingResult(elements, exception);
    }

    public static AndroidLocatorProcessingResult empty() {
        return new AndroidLocatorProcessingResult(new HashMap<>(), null);
    }

    public Map<Integer, AndroidElement> getElements() {
        return elements;
    }

    public AndroidElement getElement() {
        return elements.entrySet().stream()
                .findFirst()
                .orElseThrow(() -> LocatorProcessing.exception("Last search result element is null"))
                .getValue();
    }

    public RuntimeException getException() {
        return exception;
    }

    public boolean isSuccess() {
        return Objects.isNull(exception);
    }

    public boolean isSingle() {
        return elements.size() <= 1;
    }

    public int size() {
        return elements.size();
    }

}
