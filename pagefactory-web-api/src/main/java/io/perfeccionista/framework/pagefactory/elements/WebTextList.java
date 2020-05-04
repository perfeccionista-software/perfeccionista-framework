package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.methods.Bounds;
import io.perfeccionista.framework.pagefactory.elements.methods.ClickToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.ScrollToElementAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.SizeAvailable;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilter;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterResult;
import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;
import io.perfeccionista.framework.value.Value;
import io.perfeccionista.framework.value.number.NumberValue;
import io.perfeccionista.framework.value.string.StringValue;

// TODO: Добавить TextBlockExtractor/LinkExtractor
public interface WebTextList extends WebChildElement,
        ScrollToElementAvailable<WebTextListFilter>, ClickToElementAvailable<WebTextListFilter>, SizeAvailable {

    default WebTextListFilterResult filter(WebTextListFilter filter) {
        return filter.filter(this);
    }

    MultipleResult<String> extractAll();

    @Override
    WebTextList executeAction(String name, Object... args);

    @Override
    WebTextList executeInteraction(String name, WebChildElement other, Object... args);


    @Override
    WebTextList clickToElement(WebTextListFilter filter); // Тут нужно еще скроллить к элементу

    @Override
    WebTextList hoverTo(boolean withOutOfBounds);

    @Override
    WebTextList scrollTo();

    @Override
    WebTextList scrollToElement(WebTextListFilter filter);


    @Override
    WebTextList shouldBePresent();

    @Override
    WebTextList shouldNotBePresent();

    @Override
    WebTextList shouldBeDisplayed();

    @Override
    WebTextList shouldNotBeDisplayed();

    @Override
    WebTextList shouldBeInFocus();

    @Override
    WebTextList shouldNotBeInFocus();


    @Override
    WebTextList shouldHaveSize(Value<Integer> integerValue);

    @Override
    WebTextList shouldHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebTextList shouldHavePropertyValue(String propertyName, NumberValue<?> numberValue);

    @Override
    WebTextList shouldNotHavePropertyValue(String propertyName, StringValue stringValue);

    @Override
    WebTextList shouldNotHavePropertyValue(String propertyName, NumberValue<?> numberValue);


    @Override
    WebTextList componentShouldBePresent(String componentName);

    @Override
    WebTextList componentShouldNotBePresent(String componentName);

    @Override
    WebTextList componentShouldBeDisplayed(String componentName);

    @Override
    WebTextList componentShouldNotBeDisplayed(String componentName);

    @Override
    WebTextList componentShouldHaveBounds(String componentName, Bounds bounds);

    @Override
    WebTextList componentShouldNotHaveBounds(String componentName, Bounds bounds);

    @Override
    WebTextList componentShouldLooksLike(String componentName, Screenshot screenshot);

    @Override
    WebTextList componentShouldNotLooksLike(String componentName, Screenshot screenshot);
}