package io.perfeccionista.framework.pagefactory.elements.context;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;

public class WebBlockContextLimiter<T extends WebBlock> implements WebSearchContextLimiter<T> {

    public WebBlockContextLimiter(T block) {
    }




    public static <T extends WebBlock> WebSearchContextLimiter<T> ofBlock(T block) {
        return new WebBlockContextLimiter<>(block);
    }


    @Override
    public T getContext() {
        return null;
    }

}
