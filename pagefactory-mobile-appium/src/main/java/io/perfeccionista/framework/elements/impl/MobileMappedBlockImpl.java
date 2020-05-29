package io.perfeccionista.framework.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.base.ParentInfo;

import io.perfeccionista.framework.pagefactory.elements.MobileMappedBlock;

import static io.perfeccionista.framework.pagefactory.elements.components.MobileComponents.ROOT;

public abstract class MobileMappedBlockImpl extends MobileBlockImpl implements MobileMappedBlock {

    protected ParentInfo parentInfo;

    @Override
    public ParentInfo getParentInfo() {
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
