package io.perfeccionista.framework.pagefactory.elements.methods;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.base.WebLocatorChainAvailable;
import io.perfeccionista.framework.plugin.AssertMethodType;
import io.perfeccionista.framework.pagefactory.elements.actions.MappedElementAction;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.GET_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_HAVE_TEXT_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.WebMethods.SHOULD_NOT_HAVE_TEXT_LABEL_METHOD;

public interface GetLabelAvailable extends WebLocatorChainAvailable {

    @MappedElementAction(GET_LABEL_METHOD)
    @Nullable String getLabel();

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_TEXT_LABEL_METHOD)
    WebChildElement shouldHaveLabel(@NotNull StringValue expectedValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_HAVE_NUMBER_LABEL_METHOD)
    WebChildElement shouldHaveLabel(@NotNull NumberValue<?> expectedValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_TEXT_LABEL_METHOD)
    WebChildElement shouldNotHaveLabel(@NotNull StringValue expectedValue);

    @AssertMethodType
    @MappedElementAction(SHOULD_NOT_HAVE_NUMBER_LABEL_METHOD)
    WebChildElement shouldNotHaveLabel(@NotNull NumberValue<?> expectedValue);

}
