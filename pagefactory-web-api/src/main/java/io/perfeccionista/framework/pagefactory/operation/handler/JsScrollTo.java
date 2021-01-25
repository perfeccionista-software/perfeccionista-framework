package io.perfeccionista.framework.pagefactory.operation.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class JsScrollTo implements EndpointHandler<Void> {

    private final String scrollRoot;
    private double topIndent;
    private double rightIndent;
    private double bottomIndent;
    private double leftIndent;
    private Duration delay;

    public JsScrollTo() {
        // TODO: В зависимости от типа браузера корневой элемент может меняться.
        //  В каких-то браузерах работает document.body, но в большинстве - document.documentElement
        this.scrollRoot = "document.documentElement";
        this.topIndent = 0d;
        this.rightIndent = 0d;
        this.bottomIndent = 0d;
        this.leftIndent = 0d;
        this.delay = Duration.ZERO;
    }

    public JsScrollTo setTopIndent(double topIndent) {
        this.topIndent = topIndent;
        return this;
    }

    public JsScrollTo setRightIndent(double rightIndent) {
        this.rightIndent = rightIndent;
        return this;
    }

    public JsScrollTo setBottomIndent(double bottomIndent) {
        this.bottomIndent = bottomIndent;
        return this;
    }

    public JsScrollTo setLeftIndent(double leftIndent) {
        this.leftIndent = leftIndent;
        return this;
    }

    public JsScrollTo setDelay(@NotNull Duration delay) {
        this.delay = delay;
        return this;
    }

    @Override
    public Void handle(Object endpoint) {
        return null;
    }

    @Override
    public @NotNull JsonNode toJson() {
        ObjectNode scrollToInvocation = createObjectNode()
                .put("name", "perfeccionista.web.js.ScrollTo")
                .put("script", "js/ScrollTo.js");
        ObjectNode options = createObjectNode()
                .put("scrollRoot", scrollRoot)
                .put("topIndent", topIndent)
                .put("rightIndent", rightIndent)
                .put("bottomIndent", bottomIndent)
                .put("leftIndent", leftIndent);
        if (!delay.isZero() && !delay.isNegative()) {
            options.put("delay", getDelayInMilliseconds());
        }
        scrollToInvocation.set("options", options);
        return scrollToInvocation;
    }

    protected long getDelayInMilliseconds() {
        return (this.delay.getSeconds() * 1_000) + (this.delay.getNano() / 1_000_000);
    }

}
