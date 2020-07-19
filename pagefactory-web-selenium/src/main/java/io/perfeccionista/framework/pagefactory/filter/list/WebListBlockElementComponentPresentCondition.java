package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebListBlockElementComponentPresentCondition implements WebListBlockCondition {

    private final Deque<WebListBlockConditionHolder> childConditions = new ArrayDeque<>();

    private final String componentName;
    private final WebChildElement elementMock;
    private final String elementName;

    private boolean inverse = false;

    public WebListBlockElementComponentPresentCondition(WebChildElement elementMock, String componentName) {
        this.elementName = null;
        this.elementMock = elementMock;
        this.componentName = componentName;
    }

    public WebListBlockElementComponentPresentCondition(String elementName, String componentName) {
        this.elementName = elementName;
        this.elementMock = null;
        this.componentName = componentName;
    }

    @Override
    public WebListBlockCondition and(WebListBlockCondition condition) {

        return this;
    }

    @Override
    public WebListBlockCondition or(WebListBlockCondition condition) {

        return this;
    }

    @Override
    public WebListBlockElementComponentPresentCondition inverse() {
        inverse = true;
        return this;
    }

    @Override
    public Deque<WebListBlockConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public WebFilterResult process(@NotNull WebList element, @Nullable String hash) {
        // TODO: Implement
        return null;
    }
}
