package io.perfeccionista.framework.pagefactory.operation;

import io.perfeccionista.framework.pagefactory.elements.MobilePage;
import io.perfeccionista.framework.pagefactory.elements.locators.MobileLocatorChain;
import io.perfeccionista.framework.pagefactory.operation.type.MobileElementOperationType;
import org.jetbrains.annotations.NotNull;

public class MobilePageOperationBuilder<T> {

//    private final MobilePage page;
//    private final MobileElementOperationType<T> operationType;
//
//    private MobilePageOperationBuilder(MobilePage page, MobileElementOperationType<T> operationType) {
//        this.page = page;
//        this.operationType = operationType;
//    }
//
//    public static <T> MobilePageOperationBuilder<T> of(@NotNull MobilePage page,
//                                                       @NotNull MobileElementOperationType<T> operationType) {
//        return new MobilePageOperationBuilder<>(page, operationType);
//    }
//
//    public T executeGetter() {
//        MobileLocatorChain mobileLocatorChain = page.getLocatorChain();
//        MobilePageOperation<T> operation = MobilePageOperation.of(mobileLocatorChain, operationType);
//        return page.getMobileDeviceDispatcher()
//                .executor()
//                .executeMobilePageOperation(operation)
//                .ifException(exception -> {
//                    throw exception;
//                })
//                .getResult();
//    }
//
//    public void executeAction() {
//        MobileLocatorChain mobileLocatorChain = page.getLocatorChain();
//        MobilePageOperation<T> operation = MobilePageOperation.of(mobileLocatorChain, operationType);
//        page.getMobileDeviceDispatcher()
//                .executor()
//                .executeMobilePageOperation(operation)
//                .ifException(exception -> {
//                    throw exception;
//                });
//    }

}
