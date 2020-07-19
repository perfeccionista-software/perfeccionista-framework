package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.methods.IsEnabledAvailable;
import io.perfeccionista.framework.pagefactory.filter.WebFilterResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebListBlockElementEnabledCondition implements WebListBlockCondition {

    private final Deque<WebListBlockConditionHolder> childConditions = new ArrayDeque<>();

    private final IsEnabledAvailable elementMock;
    private final String elementName;

    private boolean inverse = false;

    public WebListBlockElementEnabledCondition(IsEnabledAvailable elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebListBlockElementEnabledCondition(String elementName) {
        this.elementName = elementName;
        this.elementMock = null;
    }

    @Override
    public WebListBlockCondition and(WebListBlockCondition condition) {
        return null;
    }

    @Override
    public WebListBlockCondition or(WebListBlockCondition condition) {
        return null;
    }

    @Override
    public WebListBlockElementEnabledCondition inverse() {
        this.inverse = true;
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
