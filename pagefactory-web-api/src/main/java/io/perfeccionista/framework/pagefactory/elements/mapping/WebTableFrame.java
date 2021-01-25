package io.perfeccionista.framework.pagefactory.elements.mapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.LocatorNotFound;
import io.perfeccionista.framework.exceptions.MappedBlockNotFound;
import io.perfeccionista.framework.exceptions.attachments.WebElementAttachmentEntry;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.TABLE_ELEMENT_LOCATOR_FOR_COLUMN_BODY_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.TABLE_ELEMENT_LOCATOR_FOR_COLUMN_FOOTER_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.TABLE_ELEMENT_LOCATOR_FOR_COLUMN_HEADER_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.TABLE_MAPPED_BLOCK_FOR_COLUMN_BODY_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.TABLE_MAPPED_BLOCK_FOR_COLUMN_FOOTER_NOT_FOUND;
import static io.perfeccionista.framework.exceptions.messages.PageFactoryApiMessages.TABLE_MAPPED_BLOCK_FOR_COLUMN_HEADER_NOT_FOUND;
import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

// TODO: Добавить методы для получения экземпляра строки заголовка, тела, футера и потом их просто клонить
public class WebTableFrame<T extends WebBlock> implements JsonSerializable {

    private final WebChildElement element;

    private final Set<String> columns;

    private final Map<String, WebLocatorHolder> headerLocators;
    private final Map<String, WebLocatorHolder> bodyLocators;
    private final Map<String, WebLocatorHolder> footerLocators;

    private final Map<String, T> headers;
    private final Map<String, T> body;
    private final Map<String, T> footers;

    public WebTableFrame(@NotNull WebChildElement element,
                         @NotNull Set<String> columns,
                         @NotNull Map<String, WebLocatorHolder> headerLocators,
                         @NotNull Map<String, WebLocatorHolder> bodyLocators,
                         @NotNull Map<String, WebLocatorHolder> footerLocators,
                         @NotNull Map<String, T> headers,
                         @NotNull Map<String, T> body,
                         @NotNull Map<String, T> footers) {
        this.element = element;
        this.columns = columns;
        this.headerLocators = headerLocators;
        this.bodyLocators = bodyLocators;
        this.footerLocators = footerLocators;
        this.headers = headers;
        this.body = body;
        this.footers = footers;
    }

    public Optional<WebLocatorHolder> getHeaderLocator(@NotNull String columnName) {
        return Optional.ofNullable(headerLocators.get(columnName));
    }

    public Optional<WebLocatorHolder> getBodyLocator(@NotNull String columnName) {
        return Optional.ofNullable(bodyLocators.get(columnName));
    }

    public Optional<WebLocatorHolder> getFooterLocator(@NotNull String columnName) {
        return Optional.ofNullable(footerLocators.get(columnName));
    }


    public @NotNull WebLocatorHolder getRequiredHeaderLocator(@NotNull String columnName) {
        return getHeaderLocator(columnName)
                .orElseThrow(() -> LocatorNotFound
                        .exception(TABLE_ELEMENT_LOCATOR_FOR_COLUMN_HEADER_NOT_FOUND.getMessage(columnName))
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)));
    }

    public @NotNull WebLocatorHolder getRequiredBodyLocator(@NotNull String columnName) {
        return getBodyLocator(columnName)
                .orElseThrow(() -> LocatorNotFound
                        .exception(TABLE_ELEMENT_LOCATOR_FOR_COLUMN_BODY_NOT_FOUND.getMessage(columnName))
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)));
    }

    public @NotNull WebLocatorHolder getRequiredFooterLocator(@NotNull String columnName) {
        return getFooterLocator(columnName)
                .orElseThrow(() -> LocatorNotFound
                        .exception(TABLE_ELEMENT_LOCATOR_FOR_COLUMN_FOOTER_NOT_FOUND.getMessage(columnName))
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)));
    }


    public Optional<T> getHeaderMappedBlock(@NotNull String columnName) {
        return Optional.ofNullable(headers.get(columnName));
    }

    public Optional<T> getBodyMappedBlock(@NotNull String columnName) {
        return Optional.ofNullable(body.get(columnName));
    }

    public Optional<T> getFooterMappedBlock(@NotNull String columnName) {
        return Optional.ofNullable(footers.get(columnName));
    }


    public @NotNull T getRequiredHeaderMappedBlock(@NotNull String columnName) {
        return getHeaderMappedBlock(columnName)
                .orElseThrow(() -> MappedBlockNotFound
                        .exception(TABLE_MAPPED_BLOCK_FOR_COLUMN_HEADER_NOT_FOUND.getMessage(columnName))
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)));
    }

    public @NotNull T getRequiredBodyMappedBlock(@NotNull String columnName) {
        return getBodyMappedBlock(columnName)
                .orElseThrow(() -> MappedBlockNotFound
                        .exception(TABLE_MAPPED_BLOCK_FOR_COLUMN_BODY_NOT_FOUND.getMessage(columnName))
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)));
    }

    public @NotNull T getRequiredFooterMappedBlock(@NotNull String columnName) {
        return getFooterMappedBlock(columnName)
                .orElseThrow(() -> MappedBlockNotFound
                        .exception(TABLE_MAPPED_BLOCK_FOR_COLUMN_FOOTER_NOT_FOUND.getMessage(columnName))
                        .addLastAttachmentEntry(WebElementAttachmentEntry.of(element)));
    }

    public Set<String> getTableColumnNames() {
        return new HashSet<>(columns);
    }

    public Map<String, WebLocatorHolder> getHeaderLocators() {
        return new HashMap<>(headerLocators);
    }

    public Map<String, WebLocatorHolder> getBodyLocators() {
        return new HashMap<>(bodyLocators);
    }

    public Map<String, WebLocatorHolder> getFooterLocators() {
        return new HashMap<>(footerLocators);
    }

    public Map<String, T> getHeaders() {
        return new HashMap<>(headers);
    }

    public Map<String, T> getBody() {
        return new HashMap<>(body);
    }

    public Map<String, T> getFooters() {
        return new HashMap<>(footers);
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode rootNode = createObjectNode();

        ObjectNode headerLocatorsNode = createObjectNode();
        headerLocators.forEach((key, value) -> headerLocatorsNode.set(key, value.toJson()));
        rootNode.set("headerLocators", headerLocatorsNode);
        ObjectNode bodyLocatorsNode = createObjectNode();
        bodyLocators.forEach((key, value) -> bodyLocatorsNode.set(key, value.toJson()));
        rootNode.set("bodyLocators", bodyLocatorsNode);
        ObjectNode footerLocatorsNode = createObjectNode();
        headerLocators.forEach((key, value) -> footerLocatorsNode.set(key, value.toJson()));
        rootNode.set("footerLocators", footerLocatorsNode);

        ObjectNode headerMappedBlockNode = createObjectNode();
        headers.forEach((key, value) -> headerMappedBlockNode.put(key, value.getClass().getCanonicalName()));
        rootNode.set("headerMappedBlocks", headerMappedBlockNode);
        ObjectNode bodyMappedBlockNode = createObjectNode();
        body.forEach((key, value) -> bodyMappedBlockNode.put(key, value.getClass().getCanonicalName()));
        rootNode.set("bodyMappedBlocks", bodyMappedBlockNode);
        ObjectNode footerMappedBlockNode = createObjectNode();
        footers.forEach((key, value) -> footerMappedBlockNode.put(key, value.getClass().getCanonicalName()));
        rootNode.set("footerMappedBlocks", footerMappedBlockNode);

        return rootNode;
    }

}
