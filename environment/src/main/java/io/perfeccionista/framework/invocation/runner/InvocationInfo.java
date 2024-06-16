package io.perfeccionista.framework.invocation.runner;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.attachments.Attachment;
import io.perfeccionista.framework.exceptions.attachments.AttachmentEntry;
import io.perfeccionista.framework.invocation.InvocationService;
import io.perfeccionista.framework.invocation.runner.InvocationResult.InvocationStatus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import static io.perfeccionista.framework.invocation.runner.InvocationInfo.InvocationType.ACTION;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.InvocationType.ASSERT;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.InvocationType.CUSTOM;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.InvocationType.EMPTY;
import static io.perfeccionista.framework.invocation.runner.InvocationInfo.InvocationType.GETTER;

public final class InvocationInfo {

    protected final String uuid;

    protected final InvocationType type;
    protected final String invocationName;

    protected Deque<InvocationResult> invocationResults;
    protected InvocationResult current = null;
    protected Attachment attachment = Attachment.empty();

    protected InvocationInfoStatisticsFormatter statisticsFormatter;

    protected InvocationInfo(@NotNull InvocationInfo.InvocationType type, @NotNull String invocationName, String... args) {
        this.uuid = UUID.randomUUID().toString();
        this.type = type;
        InvocationService invocationService = Environment.getForCurrentThread().getService(InvocationService.class);
        InvocationInfoNameFormatter nameFormatter = invocationService.getInvocationInfoNameFormatter();
        this.statisticsFormatter = invocationService.getInvocationInfoStatisticsFormatter();
        this.invocationResults = new ArrayDeque<>();
        this.invocationName = nameFormatter.format(invocationName, args);
    }

    public static InvocationInfo assertInvocation(String invocationName, String... args) {
        return new InvocationInfo(ASSERT, invocationName, args);
    }

    public static InvocationInfo actionInvocation(String invocationName, String... args) {
        return new InvocationInfo(ACTION, invocationName, args);
    }

    public static InvocationInfo getterInvocation(String invocationName, String... args) {
        return new InvocationInfo(GETTER, invocationName, args);
    }

    public static InvocationInfo customOperationInvocation(String invocationName, String... args) {
        return new InvocationInfo(CUSTOM, invocationName, args);
    }

    public static InvocationInfo empty() {
        return new InvocationInfo(EMPTY, "Step");
    }

    public boolean isNotEmpty() {
        return EMPTY != type;
    }

    public String getUuid() {
        return this.uuid;
    }

    public @NotNull InvocationInfo.InvocationType getType() {
        return type;
    }

    public @NotNull String getInvocationName() {
        return this.invocationName;
    }

    public Optional<InvocationResult> getCurrent() {
        return Optional.ofNullable(current);
    }

    public Deque<InvocationResult> getResults() {
        return invocationResults;
    }

    public InvocationStatus getLastStatus() {
        if (invocationResults.isEmpty()) {
            return InvocationStatus.NEW;
        }
        return invocationResults.getLast().getStatus();
    }

    public Attachment getAttachment() {
        return this.attachment;
    }

    public InvocationInfo setAttachment(@NotNull Attachment attachment) {
        this.attachment = attachment;
        return this;
    }

    public InvocationInfo addAttachmentEntry(@NotNull AttachmentEntry<?> attachmentEntry) {
        this.attachment.addLastAttachmentEntry(attachmentEntry);
        return this;
    }

    public InvocationInfo start() {
        this.current = InvocationResult.start();
        return this;
    }

    public InvocationInfo start(@NotNull Consumer<InvocationInfo> startInvocationVisitor) {
        this.current = InvocationResult.start();
        startInvocationVisitor.accept(this);
        return this;
    }

    public InvocationInfo success() {
        this.current.success();
        this.invocationResults.addLast(this.current);
        this.current = null;
        return this;
    }

    public InvocationInfo success(@NotNull Consumer<InvocationInfo> successInvocationVisitor) {
        this.current.success();
        this.invocationResults.addLast(this.current);
        successInvocationVisitor.accept(this);
        this.current = null;
        return this;
    }

    public InvocationInfo exception(Throwable t) {
        this.current.exception(t);
        this.invocationResults.addLast(this.current);
        this.current = null;
        return this;
    }

    public InvocationInfo exception(Throwable t, @NotNull Consumer<InvocationInfo> exceptionInvocationVisitor) {
        this.current.exception(t);
        this.invocationResults.addLast(this.current);
        exceptionInvocationVisitor.accept(this);
        this.current = null;
        return this;
    }

    public InvocationInfo close(@NotNull Consumer<InvocationInfo> closeInvocationVisitor) {
        closeInvocationVisitor.accept(this);
        return this;
    }

    public String getStatistics() {
        return this.statisticsFormatter.format(this);
    }

    @Override
    public String toString() {
        return this.invocationName + " " + getStatistics();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InvocationInfo that = (InvocationInfo) o;
        if (!Objects.equals(type, that.type)) {
            return false;
        }
        return Objects.equals(invocationName, that.invocationName);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + invocationName.hashCode();
        return result;
    }

    public enum InvocationType {

        EMPTY,
        ASSERT,
        ACTION,
        GETTER,
        CUSTOM

    }

}
