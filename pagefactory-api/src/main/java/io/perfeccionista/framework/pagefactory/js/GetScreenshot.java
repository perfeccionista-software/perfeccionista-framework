package io.perfeccionista.framework.pagefactory.js;

import io.perfeccionista.framework.pagefactory.screenshots.Screenshot;

public class GetScreenshot implements JsFunction<byte[], Screenshot> {

    @Override
    public Screenshot convert(byte[] json) {
        return null;
    }

    @Override
    public Class<Screenshot> getResultType() {
        return null;
    }
}

