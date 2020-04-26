package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.actions.ElementAction;
import io.perfeccionista.framework.pagefactory.elements.actions.JsScrollToTextBlockElement;
import io.perfeccionista.framework.pagefactory.elements.actions.JsSize;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.extractor.textlist.WebTextListBlockStringExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterSeleniumImpl;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableElementMethods.SIZE_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.AvailableWebMethods.SCROLL_TO_ELEMENT_METHOD;

@WebLocator(component = LI, xpath = ".//li", single = false)
@ElementAction(name = SCROLL_TO_ELEMENT_METHOD, implementation = JsScrollToTextBlockElement.class)
@ElementAction(name = SIZE_METHOD, implementation = JsSize.class)
public abstract class WebTextListSeleniumImpl extends AbstractWebChildElementSeleniumImpl implements WebTextList {

    @Override
    public MultipleResult<String> extractAll() {
        return new WebTextListFilterSeleniumImpl().filter(this)
                .extractAll(new WebTextListBlockStringExtractor());
    }

//    @Override
//    public MultipleResult<String> getValues() {
//        GetText getTextFunction = ReflectionUtils.newInstance(GetText.class);
//        JsOperation<MultipleResult<String>> operation = JsOperation.multiple(getLocatorChainTo(LI), getTextFunction);
//        return getDriverInstance().getDriverOperationExecutor().executeOperation(operation);
//    }
//
//    @Override
//    public MultipleResult<String> getValues(WebStringListFilter filter) {
//        return filter.multipleResult(this);
//    }
//
//    @Override
//    public void scrollToElement(WebStringListFilter filter) {
//        getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
//    }
//
//    @Override
//    public int size() {
//        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, LI);
//    }

}
