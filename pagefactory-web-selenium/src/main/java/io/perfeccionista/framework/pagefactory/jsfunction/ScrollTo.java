package io.perfeccionista.framework.pagefactory.jsfunction;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.Duration;
import java.util.function.Function;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class ScrollTo implements JsFunction<Void> {

    private final String scrollRoot;
    private Double topIndent;
    private Double rightIndent;
    private Double bottomIndent;
    private Double leftIndent;
    private Duration delay;

    public ScrollTo() {
        // TODO: В зависимости от типа браузера корневой элемент может меняться.
        //  В каких-то браузерах работает document.body, но в большинстве - document.documentElement
        this.scrollRoot = "document.documentElement";
        this.topIndent = 0d;
        this.rightIndent = 0d;
        this.bottomIndent = 0d;
        this.leftIndent = 0d;
        this.delay = Duration.ZERO;
    }

    protected ScrollTo(String scrollRoot, Double topIndent, Double rightIndent, Double bottomIndent, Double leftIndent, Duration delay) {
        this.scrollRoot = scrollRoot;
        this.topIndent = topIndent;
        this.rightIndent = rightIndent;
        this.bottomIndent = bottomIndent;
        this.leftIndent = leftIndent;
        this.delay = delay;
    }

    public ScrollTo setDelay(Duration delay) {
        this.delay = delay;
        return this;
    }

    @Override
    public Function<Object, Void> getConverter() {
        return object -> null;
    }

    @Override
    public ObjectNode getJsFunctionInvocation() {
        ObjectNode scrollToInvocation = createObjectNode()
                .put("name", "perfeccionista.js.selenium.ScrollTo");
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

    @Override
    public String getScriptName() {
        return "perfeccionista.js.selenium.ScrollTo";
    }

    @Override
    public String getScriptDestination() {
        return "js/ScrollTo.js";
    }

    protected long getDelayInMilliseconds() {
        return (this.delay.getSeconds() * 1_000) + (this.delay.getNano() / 1_000_000);
    }

}
