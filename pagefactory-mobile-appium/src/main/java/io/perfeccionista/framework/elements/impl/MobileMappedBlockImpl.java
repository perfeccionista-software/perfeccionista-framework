package io.perfeccionista.framework.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.base.WebParentInfo;

import io.perfeccionista.framework.pagefactory.elements.MobileMappedBlock;

public abstract class MobileMappedBlockImpl extends MobileBlockImpl implements MobileMappedBlock {

    protected WebParentInfo parentInfo;

    @Override
    public WebParentInfo getParentInfo() {
        return parentInfo;
    }

//    @Override
//    public LocatorChain getLocatorChain() {
//        LocatorChain locatorChain = getParent().getLocatorChain();
//        locatorChain.getLastLocator().checkHash(parentInfo.getParentHash());
//        LocatorHolder locatorHolder = getLocator(ROOT).setSingle(true).setIndexes(parentInfo.getIndex());
//        return locatorChain.addLocator(locatorHolder);
//    }

}
