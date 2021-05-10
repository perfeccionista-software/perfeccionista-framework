package io.perfeccionista.framework.pagefactory.elements.locators;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class AndroidLocatorHolder extends MobileLocatorHolder {

    private AndroidLocatorHolder(String locatorComponent, LocatorStrategy locatorStrategy, String locatorValue) {
        super(locatorComponent, locatorStrategy, locatorValue);
    }

    public static AndroidLocatorHolder of(@NotNull String locatorComponent,
                                          @NotNull AndroidLocatorStrategy locatorStrategy,
                                          @NotNull String locatorValue) {
        return new AndroidLocatorHolder(locatorComponent, locatorStrategy, locatorValue);
    }

    @Override
    public AndroidLocatorHolder setLocatorComponent(@NotNull String locatorComponent) {
        super.setLocatorComponent(locatorComponent);
        return this;
    }

    @Override
    public AndroidLocatorHolder setIndex(int index) {
        super.setIndex(index);
        return this;
    }

    @Override
    public AndroidLocatorHolder setIndexes(Collection<Integer> indexes) {
        super.setIndexes(indexes);
        return this;
    }

    @Override
    public AndroidLocatorHolder setSingle(boolean single) {
        super.setSingle(single);
        return this;
    }

    @Override
    public AndroidLocatorHolder setStrictSearch(boolean strictSearch) {
        super.setStrictSearch(strictSearch);
        return this;
    }

    @Override
    public AndroidLocatorHolder setOnlyWithinParent(boolean onlyWithinParent) {
        super.setOnlyWithinParent(onlyWithinParent);
        return this;
    }

    @Override
    public AndroidLocatorHolder setCalculateHash(boolean calculateHash) {
        super.setCalculateHash(calculateHash);
        return this;
    }

    @Override
    public AndroidLocatorHolder setExpectedHash(@Nullable String expectedHash) {
        super.setExpectedHash(expectedHash);
        return this;
    }

    @Override
    public AndroidLocatorStrategy getLocatorStrategy() {
        return (AndroidLocatorStrategy) super.getLocatorStrategy();
    }

    @Override
    public AndroidLocatorHolder clone() {
        return new AndroidLocatorHolder(this.locatorComponent, this.locatorStrategy, this.locatorValue)
                .setIndexes(this.getIndexes())
                .setSingle(this.single)
                .setStrictSearch(this.strictSearch)
                .setOnlyWithinParent(this.onlyWithinParent)
                .setCalculateHash(this.calculateHash)
                .setExpectedHash(this.expectedHash);
    }

}
