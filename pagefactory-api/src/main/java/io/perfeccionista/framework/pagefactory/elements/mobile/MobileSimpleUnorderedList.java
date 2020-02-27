package io.perfeccionista.framework.pagefactory.elements.mobile;

import io.perfeccionista.framework.pagefactory.elements.methods.availability.SizeAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.availability.SwipeToElementAvailable;
import io.perfeccionista.framework.pagefactory.itemfilter.MultipleResult;
import io.perfeccionista.framework.pagefactory.itemfilter.appium.AppiumStringBlockFilter;
import io.perfeccionista.framework.pagefactory.operations.OperationResult;

public interface MobileSimpleUnorderedList extends MobileChildElement,
        SwipeToElementAvailable<AppiumStringBlockFilter>, SizeAvailable {

    OperationResult<MultipleResult<String>> getValues();

    OperationResult<MultipleResult<String>> getValues(AppiumStringBlockFilter filter);

}
