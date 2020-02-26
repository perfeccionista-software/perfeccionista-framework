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

    /**
     * Во многих сценариях нам необходимо будет модифицировать заданный ранее локатор (добавить индекс),
     * поэтому необходимо возвращать его клон, а не оригинальный инстанс
     * @param locatorName
     * @return
     */
    public Optional<LocatorHolder> getOptionalLocator(String locatorName) {
        LocatorHolder locatorHolder = locators.get(locatorName);
        if (null == locatorHolder) {
            return Optional.empty();
        }
        return Optional.of(locatorHolder.clone());
    }

    public LocatorHolder getLocator(String locatorName) {
        LocatorHolder locatorHolder = locators.get(locatorName);
        if (null == locatorHolder) {
            throw new LocatorNotDeclaredException(LOCATOR_NOT_DECLARED.getMessage(locatorName))
                    .setAttachment(Attachment.of(StringAttachmentEntry.of("Element", this.toString())));
        }
        return locatorHolder.clone();
    }

    public Stream<Entry<String, LocatorHolder>> stream() {
        return locators.entrySet().stream();
    }

    public void forEach(BiConsumer<String, LocatorHolder> consumer) {
        locators.forEach(consumer);
    }

}
