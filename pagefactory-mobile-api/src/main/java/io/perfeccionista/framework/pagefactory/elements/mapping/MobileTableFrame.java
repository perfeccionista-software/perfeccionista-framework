package io.perfeccionista.framework.pagefactory.elements.mapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.perfeccionista.framework.exceptions.LocatorNotFound;
import io.perfeccionista.framework.exceptions.MappedBlockNotFound;
import io.perfeccionista.framework.exceptions.attachments.MobileElementAttachmentEntry;
import io.perfeccionista.framework.json.JsonSerializable;
import io.perfeccionista.framework.pagefactory.elements.MobileBlock;
import io.perfeccionista.framework.pagefactory.elements.base.MobileChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorHolder;
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

public class MobileTableFrame<T extends MobileBlock> implements JsonSerializable {

    private final MobileChildElement element;

    private final Set<String> columns;

    private final Map<String, MobileLocatorHolder> headerLocators;
    private final Map<String, MobileLocatorHolder> bodyLocators;
    private final Map<String, MobileLocatorHolder> footerLocators;

    private final Map<String, T> headers;
    private final Map<String, T> body;
    private final Map<String, T> footers;

    public MobileTableFrame(@NotNull MobileChildElement element,
                            @NotNull Set<String> columns,
                            @NotNull Map<String, MobileLocatorHolder> headerLocators,
                            @NotNull Map<String, MobileLocatorHolder> bodyLocators,
                            @NotNull Map<String, MobileLocatorHolder> footerLocators,
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

    public Optional<MobileLocatorHolder> getHeaderLocator(@NotNull String columnName) {
        return Optional.ofNullable(headerLocators.get(columnName));
    }

    public Optional<MobileLocatorHolder> getBodyLocator(@NotNull String columnName) {
        return Optional.ofNullable(bodyLocators.get(columnName));
    }

    public Optional<MobileLocatorHolder> getFooterLocator(@NotNull String columnName) {
        return Optional.ofNullable(footerLocators.get(columnName));
    }


    public @NotNull MobileLocatorHolder getRequiredHeaderLocator(@NotNull String columnName) {
        return getHeaderLocator(columnName)
                .orElseThrow(() -> LocatorNotFound
                        .exception(TABLE_ELEMENT_LOCATOR_FOR_COLUMN_HEADER_NOT_FOUND.getMessage(columnName))
                        .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element)));
    }

    public @NotNull MobileLocatorHolder getRequiredBodyLocator(@NotNull String columnName) {
        return getBodyLocator(columnName)
                .orElseThrow(() -> LocatorNotFound
                        .exception(TABLE_ELEMENT_LOCATOR_FOR_COLUMN_BODY_NOT_FOUND.getMessage(columnName))
                        .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element)));
    }

    public @NotNull MobileLocatorHolder getRequiredFooterLocator(@NotNull String columnName) {
        return getFooterLocator(columnName)
                .orElseThrow(() -> LocatorNotFound
                        .exception(TABLE_ELEMENT_LOCATOR_FOR_COLUMN_FOOTER_NOT_FOUND.getMessage(columnName))
                        .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element)));
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
                        .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element)));
    }

    public @NotNull T getRequiredBodyMappedBlock(@NotNull String columnName) {
        return getBodyMappedBlock(columnName)
                .orElseThrow(() -> MappedBlockNotFound
                        .exception(TABLE_MAPPED_BLOCK_FOR_COLUMN_BODY_NOT_FOUND.getMessage(columnName))
                        .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element)));
    }

    public @NotNull T getRequiredFooterMappedBlock(@NotNull String columnName) {
        return getFooterMappedBlock(columnName)
                .orElseThrow(() -> MappedBlockNotFound
                        .exception(TABLE_MAPPED_BLOCK_FOR_COLUMN_FOOTER_NOT_FOUND.getMessage(columnName))
                        .addLastAttachmentEntry(MobileElementAttachmentEntry.of(element)));
    }

    public Set<String> getTableColumnNames() {
        return new HashSet<>(columns);
    }

    public Map<String, MobileLocatorHolder> getHeaderLocators() {
        return new HashMap<>(headerLocators);
    }

    public Map<String, MobileLocatorHolder> getBodyLocators() {
        return new HashMap<>(bodyLocators);
    }

    public Map<String, MobileLocatorHolder> getFooterLocators() {
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
