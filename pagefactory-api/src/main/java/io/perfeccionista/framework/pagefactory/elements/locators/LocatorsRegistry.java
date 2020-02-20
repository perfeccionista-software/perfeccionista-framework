package io.perfeccionista.framework.pagefactory.elements.locators;

import io.perfeccionista.framework.attachment.Attachment;
import io.perfeccionista.framework.attachment.StringAttachmentEntry;
import io.perfeccionista.framework.exceptions.LocatorNotDeclaredException;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryMessages.LOCATOR_NOT_DECLARED;

public class LocatorsRegistry {

    protected final Map<String, LocatorHolder> locators;

    private LocatorsRegistry(Map<String, LocatorHolder> locators) {
        this.locators = locators;
    }

    public static LocatorsRegistry of(Map<String, LocatorHolder> locators) {
        return new LocatorsRegistry(locators);
    }

    public Optional<LocatorHolder> getOptionalLocator(String locatorName) {
        return Optional.ofNullable(locators.get(locatorName));
    }

    public LocatorHolder getLocator(String locatorName) {
        return Optional.ofNullable(locators.get(locatorName))
                .orElseThrow(() -> new LocatorNotDeclaredException(LOCATOR_NOT_DECLARED.getMessage(locatorName))
                        .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", this.toString()))));
    }

    public Stream<Entry<String, LocatorHolder>> stream() {
        return locators.entrySet().stream();
    }

    public void forEach(BiConsumer<String, LocatorHolder> consumer) {
        locators.forEach(consumer);
    }

}
