package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.base.ParentInfo;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorChain;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.elements.WebMappedBlock;

import static io.perfeccionista.framework.pagefactory.elements.locators.Components.ROOT;

public abstract class WebMappedBlockImpl extends WebBlockImpl implements WebMappedBlock {

//    protected ParentInfo parentInfo;
//
//    @Override
//    public ParentInfo getParentInfo() {
//        return parentInfo;
//    }
//
//    @Override
//    public LocatorChain getLocatorChain() {
//        LocatorChain locatorChain = getParent().getLocatorChain();
//        locatorChain.getLastLocator().checkHash(parentInfo.getParentHash());
//        LocatorHolder locatorHolder = getLocator(ROOT).setSingle(true).setIndexes(parentInfo.getIndex());
//        return locatorChain.addLocator(locatorHolder);
//    }

}
