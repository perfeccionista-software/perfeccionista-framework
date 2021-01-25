package io.perfeccionista.framework.pagefactory.elements.locators;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class IosLocatorHolder extends MobileLocatorHolder {

    private IosLocatorHolder(String locatorComponent, LocatorStrategy locatorStrategy, String locatorValue) {
        super(locatorComponent, locatorStrategy, locatorValue);
    }

    public static IosLocatorHolder of(@NotNull String locatorComponent,
                                      @NotNull IosLocatorStrategy locatorStrategy,
                                      @NotNull String locatorValue) {
        return new IosLocatorHolder(locatorComponent, locatorStrategy, locatorValue);
    }

    @Override
    public IosLocatorHolder setLocatorComponent(@NotNull String locatorComponent) {
        super.setLocatorComponent(locatorComponent);
        return this;
    }

    @Override
    public IosLocatorHolder setIndex(int index) {
        super.setIndex(index);
        return this;
    }

    @Override
    public IosLocatorHolder setIndexes(Collection<Integer> indexes) {
        super.setIndexes(indexes);
        return this;
    }

    @Override
    public IosLocatorHolder setSingle(boolean single) {
        super.setSingle(single);
        return this;
    }

    @Override
    public IosLocatorHolder setStrictSearch(boolean strictSearch) {
        super.setStrictSearch(strictSearch);
        return this;
    }

    @Override
    public IosLocatorHolder setOnlyWithinParent(boolean onlyWithinParent) {
        super.setOnlyWithinParent(onlyWithinParent);
        return this;
    }

    @Override
    public IosLocatorHolder setCalculateHash(boolean calculateHash) {
        super.setCalculateHash(calculateHash);
        return this;
    }

    @Override
    public IosLocatorHolder setExpectedHash(@Nullable String expectedHash) {
        super.setExpectedHash(expectedHash);
        return this;
    }

    @Override
    public IosLocatorStrategy getLocatorStrategy() {
        return (IosLocatorStrategy) super.getLocatorStrategy();
    }

    @Override
    public IosLocatorHolder clone() {
        return new IosLocatorHolder(this.locatorComponent, this.locatorStrategy, this.locatorValue)
                .setIndexes(this.getIndexes())
                .setSingle(this.single)
                .setStrictSearch(this.strictSearch)
                .setOnlyWithinParent(this.onlyWithinParent)
                .setCalculateHash(this.calculateHash)
                .setExpectedHash(this.expectedHash);
    }

}
