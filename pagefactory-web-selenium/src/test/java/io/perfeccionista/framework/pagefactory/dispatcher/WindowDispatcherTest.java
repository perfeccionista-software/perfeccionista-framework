package io.perfeccionista.framework.pagefactory.dispatcher;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.measurements.Dimensions2D;
import io.perfeccionista.framework.measurements.Point2D;
import io.perfeccionista.framework.utils.ThreadUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("Browser") @Tag("Window")
class WindowDispatcherTest extends AbstractWebSeleniumParallelTest {

    @Test
    void getInnerWindowSizeTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();

        assertEquals(Dimensions2D.of(1200, 877), browser.window().getInnerWindowSize());
    }

    @Test
    void getOuterWindowSizeTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();

        assertEquals(Dimensions2D.of(1200, 1000), browser.window().getOuterWindowSize());
    }

    @Test
    void setOuterWindowSizeTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();

        assertEquals(Dimensions2D.of(1200, 1000), browser.window().getOuterWindowSize());

        browser.window()
                .setOuterWindowSize(Dimensions2D.of(1250, 1050));

        assertEquals(Dimensions2D.of(1250, 1050), browser.window().getOuterWindowSize());
    }

    @Test
    void setWindowAbsoluteLocationTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();

        browser.window()
                .setAbsoluteLocation(Point2D.of(50, 50));

        assertEquals(Point2D.of(50d, 50d), browser.window().getAbsoluteLocation());
    }

    @Test
    @Tag("Manual") @Disabled("Platform dependent")
    void windowFullscreenTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();

        ThreadUtils.sleep(Duration.ofSeconds(3));

        browser.window()
                .fullscreen();

        ThreadUtils.sleep(Duration.ofSeconds(3));
    }

    @Test
    @Tag("Manual") @Disabled("Platform dependent")
    void windowMaximizeTest() {
        WebBrowserDispatcher browser = openDefaultBrowser();

        ThreadUtils.sleep(Duration.ofSeconds(3));

        browser.window()
                .maximize();

        ThreadUtils.sleep(Duration.ofSeconds(3));
    }

}
