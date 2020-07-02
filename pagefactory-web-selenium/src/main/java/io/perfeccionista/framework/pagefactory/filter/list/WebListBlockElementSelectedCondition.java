package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.methods.IsSelectedAvailable;
import io.perfeccionista.framework.pagefactory.filter.WebConditionProcessingResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter.WebListBlockConditionHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebListBlockElementSelectedCondition implements WebListBlockCondition {

    private final Deque<WebListBlockConditionHolder> childConditions = new ArrayDeque<>();

    private final IsSelectedAvailable elementMock;
    private final String elementName;

    public WebListBlockElementSelectedCondition(IsSelectedAvailable elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
    }

    public WebListBlockElementSelectedCondition(String elementName) {
        this.elementName = elementName;
        this.elementMock = null;
    }

    public WebListBlockElementSelectedCondition inverse() {
        return this;
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
    public Deque<WebListBlockConditionHolder> getChildConditions() {
        return childConditions;
    }

    @Override
    public WebConditionProcessingResult process(@NotNull WebList element, @Nullable String hash) {
        // TODO: Implement
        return null;
    }
}

