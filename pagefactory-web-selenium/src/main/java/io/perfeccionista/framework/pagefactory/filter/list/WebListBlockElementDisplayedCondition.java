package io.perfeccionista.framework.pagefactory.filter.list;

import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.methods.IsDisplayedAvailable;
import io.perfeccionista.framework.pagefactory.filter.WebConditionProcessingResult;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter.WebListBlockConditionHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;

public class WebListBlockElementDisplayedCondition implements WebListBlockCondition {

    private final Deque<WebListBlockConditionHolder> childConditions = new ArrayDeque<>();

    private final String elementName;
    private final IsDisplayedAvailable elementMock;




    public WebListBlockElementDisplayedCondition(IsDisplayedAvailable elementMock) {
        this.elementName = null;
        this.elementMock = elementMock;
    }

     public WebListBlockElementDisplayedCondition(String elementName) {
        this.elementName = elementName;
        this.elementMock = null;
    }

    public WebListBlockElementDisplayedCondition inverse() {
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
