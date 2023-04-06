package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.matcher.element.WebRadioGroupMatcher;
import io.perfeccionista.framework.matcher.result.WebIndexesMatcher;
import io.perfeccionista.framework.matcher.result.WebMultipleIndexedResultMatcher;
import io.perfeccionista.framework.pagefactory.elements.impl.WebRadioButtonBlock;
import io.perfeccionista.framework.pagefactory.elements.mapping.WebRadioGroupFrame;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.jetbrains.annotations.NotNull;

public interface WebRadioGroup extends WebList<WebRadioButtonBlock> {

    @API(status = Status.MAINTAINED)
    @NotNull WebRadioGroupFrame<WebRadioButtonBlock> getBlockFrame();

    // Button Extractors
    @NotNull WebRadioButton getSelected();
    @NotNull WebRadioButton getByIndex(int expectedIndex);
    @NotNull WebRadioButton getByLabel(@NotNull String expectedText);

    // Actions
    @Override
    WebRadioGroup executeAction(@NotNull String name, Object... args);

    // Asserts
    WebRadioGroup should(@NotNull WebMultipleIndexedResultMatcher<Integer> matcher);
    WebRadioGroup should(@NotNull WebRadioGroupMatcher matcher);
    WebRadioGroup should(@NotNull WebIndexesMatcher matcher);

    // Size
    int size();

}
