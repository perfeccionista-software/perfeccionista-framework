package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.AbstractWebChildElement;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.extractor.WebListBlockValueExtractor;
import io.perfeccionista.framework.pagefactory.filter.MultipleResult;
import io.perfeccionista.framework.pagefactory.filter.SingleResult;
import io.perfeccionista.framework.pagefactory.filter.WebListFilter;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.LI;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SCROLL_TO_ELEMENT_METHOD;
import static io.perfeccionista.framework.pagefactory.elements.methods.availability.AvailableMethod.SIZE_METHOD;


public abstract class WebListImpl extends AbstractWebChildElement implements WebList {

//    protected Class<? extends WebBlock> blockMapper;
//
//    @Override
//    public <V> SingleResult<V> getValue(WebListBlockValueExtractor<V> extractor, WebListFilter filter) {
//        SingleResult<Integer> result = filter.singleResult(this);
//        return extractor.extractSingleValue(this, result);
//    }
//
//    @Override
//    public <V> MultipleResult<V> getValues(WebListBlockValueExtractor<V> extractor) {
//        return extractor.extractMultipleValues(this, MultipleResult.empty());
//    }
//
//    @Override
//    public <V> MultipleResult<V> getValues(WebListBlockValueExtractor<V> extractor, WebListFilter filter) {
//        MultipleResult<Integer> result = filter.multipleResult(this);
//        return extractor.setHash(result.getElementHash()).extractMultipleValues(this, result);
//    }
//
//    @Override
//    public void scrollToElement(WebListFilter filter) {
//        getMethodImplementation(SCROLL_TO_ELEMENT_METHOD, Void.class).execute(this, filter);
//    }
//
//    @Override
//    public int size() {
//        return getMethodImplementation(SIZE_METHOD, Integer.class).execute(this, LI);
//    }
//
//    @Override
//    public Class<? extends WebBlock> getBlockMapper() {
//        return blockMapper;
//    }

}
