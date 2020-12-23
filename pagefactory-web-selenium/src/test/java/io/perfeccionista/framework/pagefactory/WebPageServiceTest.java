package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.UseEnvironmentConfiguration;
import io.perfeccionista.framework.extension.PerfeccionistaExtension;
import io.perfeccionista.framework.pagefactory.configurations.TestEnvironmentConfiguration;
import io.perfeccionista.framework.pagefactory.pageobjects.HomePage;
import io.perfeccionista.framework.value.ValueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PerfeccionistaExtension.class)
@UseEnvironmentConfiguration(TestEnvironmentConfiguration.class)
public class WebPageServiceTest {

    @Test
    void singleElementTest(Environment env, ValueService val) {
        HomePage homePage = env.getService(WebPageService.class)
                .getPageInstanceByClass(HomePage.class);

//        homePage.vvv();
//        homePage.getNames();


//        Method[] declaredMethods = homePage.getClass().getDeclaredMethods();
//        System.out.println("-----------------------");
//        for (Method declaredMethod : declaredMethods) {
//            System.out.println(declaredMethod.getName() + " : " + declaredMethod.getReturnType());
//        }
//
//        System.out.println(homePage.validate());
//        System.out.println(homePage.vvv());
//        System.out.println(homePage.val());
    }

}
