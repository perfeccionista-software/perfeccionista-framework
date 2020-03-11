package io.perfeccionista.framework.pagefactory.elements.base;

public final class ParentInfo {

    private final String parentHash;
    private final int index;

    public ParentInfo(String parentHash, int index) {
        this.parentHash = parentHash;
        this.index = index;
    }

    public String getParentHash() {
        return parentHash;
    }

    public int getIndex() {
        return index;
    }

}
