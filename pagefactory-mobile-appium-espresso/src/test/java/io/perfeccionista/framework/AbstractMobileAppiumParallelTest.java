package io.perfeccionista.framework;

import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.pagefactory.configurations.PagefactoryMobileAppiumEnvironmentConfiguration;
import io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceDispatcher;
import io.perfeccionista.framework.pagefactory.dispatcher.MobileDeviceService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static io.perfeccionista.framework.pagefactory.dispatcher.screen.ScreenOrientation.PORTRAIT;
import static io.perfeccionista.framework.value.Values.stringProcess;

@TestInstance(Lifecycle.PER_METHOD)
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironment(PagefactoryMobileAppiumEnvironmentConfiguration.class)
public abstract class AbstractMobileAppiumParallelTest {

//    protected MobilePageContext initIosMobilePageContext() {
//        return startDefaultIosDevice().getMobilePageContext();                                      // Возвращаем контекст страницы для активного браузера
//    }
//
//    protected MobilePageContext initAndroidMobilePageContext() {
//        return startDefaultAndroidDevice().getMobilePageContext();                                  // Возвращаем контекст страницы для активного браузера
//    }

    protected MobileDeviceDispatcher startDefaultDeviceDispatcher() {
        return startDevice("${[config] device}");
    }

    protected MobileDeviceDispatcher startDevice(@NotNull String device) {
        Environment environment = Environment.getCurrent();
        String deviceName = stringProcess(device);
        // Создаем окружение для выполнения теста (браузер, начальный УРЛ, контекст страницы)
        MobileDeviceDispatcher mobileDeviceDispatcher = environment.getService(MobileDeviceService.class)
                .createDispatcher(deviceName)                       // создаем диспетчер
                .launch();                                          // запускаем браузер
        // Ставим принудительно ориентацию
        mobileDeviceDispatcher.screen()
                .rotateTo(PORTRAIT);
//        mobileDeviceDispatcher.tabs()
//                .openUrl(startUrl);                                 // открываем ссылку
        return mobileDeviceDispatcher;
    }

}
