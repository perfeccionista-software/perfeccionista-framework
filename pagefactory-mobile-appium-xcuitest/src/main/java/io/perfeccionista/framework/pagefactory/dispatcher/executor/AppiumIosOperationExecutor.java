package io.perfeccionista.framework.pagefactory.dispatcher.executor;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.exceptions.mapper.MobileExceptionMapper;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperation;
import io.perfeccionista.framework.pagefactory.operation.MobileElementOperationResult;
import io.perfeccionista.framework.pagefactory.operation.MobilePageOperation;
import io.perfeccionista.framework.pagefactory.operation.MobilePageOperationResult;

public class AppiumIosOperationExecutor implements MobileDeviceOperationExecutor {

    protected final Environment environment;
    protected final IOSDriver<IOSElement> instance;
    protected final MobileExceptionMapper exceptionMapper;

    public AppiumIosOperationExecutor(Environment environment, IOSDriver<IOSElement> instance, MobileExceptionMapper exceptionMapper) {
        this.environment = environment;
        this.instance = instance;
        this.exceptionMapper = exceptionMapper;
    }

    @Override
    public <T> MobileElementOperationResult<T> executeMobileElementOperation(MobileElementOperation<T> operation) {
        return null;
    }


    @Override
    public <T> MobilePageOperationResult<T> executeMobilePageOperation(MobilePageOperation<T> operation) {
        return null;
    }



    public AppiumIosOperationExecutor withTraceSearch() {


        return this;
    }

}
