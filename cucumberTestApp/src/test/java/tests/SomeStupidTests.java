package tests;

import com.example.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SomeStupidTests extends Steps {

    @Before
    public void before() {
        openBrowserStep();
    }

    @Test
    public void firstTest() {
        goToSelectedPageStep("https://www.google.com/");
        checkIsPageTitleEqualsStep("Google");
    }

    @Test
    public void secondTest() {
        goToSelectedPageStep("https://yandex.ru/");
        checkIsPageTitleEqualsStep("Яндекс");
    }

    @After
    public void after() {
        closeBrowserStep();
    }
}
