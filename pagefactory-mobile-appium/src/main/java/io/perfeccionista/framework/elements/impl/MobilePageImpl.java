package io.perfeccionista.framework.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.MobilePage;

public abstract class MobilePageImpl implements MobilePage {

//    protected final MobileDriverDispatcher driverInstance;
//    protected final MobileSearchLimiterRegistry searchLimiterRegistry;
//    protected final ElementsConfiguration configuration;
//
//    protected MobileElementRegistry elementsRegistry;
//    protected LocatorRegistry locatorRegistry;
//    protected NamesRegistry namesRegistry;
//
//    public MobilePageImpl(MobileDriverDispatcher driverInstance, ElementsConfiguration configuration) {
//        this.driverInstance = driverInstance;
//        this.configuration = configuration;
//        this.searchLimiterRegistry = new MobileSearchLimiterRegistry(this);
//    }
//
//    @Override
//    public MobileSearchLimiterRegistry getSearchLimiterRegistry() {
//        return searchLimiterRegistry;
//    }
//
//    @Override
//    public ElementsConfiguration getElementsConfiguration() {
//        return configuration;
//    }
//
//    @Override
//    public MobileDriverDispatcher getDriverInstance() {
//        return driverInstance;
//    }
//
//    @Override
//    public MobileElementRegistry getElementRegistry() {
//        return elementsRegistry;
//    }
//
//    @Override
//    public LocatorChain getLocatorChainTo(String locatorName) {
//        Optional<LocatorHolder> optionalLocator = locatorRegistry.getOptionalLocator(locatorName);
//        if (optionalLocator.isPresent()) {
//            return LocatorChain.of(optionalLocator.get());
//        }
//        return LocatorChain.of();
//    }
//
//    @Override
//    public LocatorChain getLocatorChain() {
//        return LocatorChain.of();
//    }
//
//    @Override
//    public LocatorHolder getLocator(String locatorName) {
//        return locatorRegistry.getLocator(locatorName);
//    }
////
////    @Override
////    public Optional<MobileChildElement> getElementByPath(String elementPath) {
////        return searchLimiterRegistry.getContext().getElementRegistry().getElementByPath(elementPath);
////    }
////
////    @Override
////    public <T extends MobileChildElement> Optional<T> getElementByPath(String elementPath, Class<T> elementClass) {
////        return searchLimiterRegistry.getContext().getElementRegistry().getElementByPath(elementPath, elementClass);
////    }
////
////    @Override
////    public void invokeMethod(String methodName, Object... args) {
////        searchLimiterRegistry.getContext().getElementRegistry().invokeMethodByName(methodName, this, args);
////    }
//
//    @Override
//    public Set<String> getNames() {
//        return namesRegistry.names();
//    }
//
//    // Этот метод должен переопределяться из интерфейса, если он переопределен там
//    @Override
//    public OperationResult<Boolean> isPageOpen() {
//        return OperationResult.success(true);
//    }

}
