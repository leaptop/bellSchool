package ru.open;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/** Good practise is to keep  output from hooks in a class named BaseTest.
 * This class opens and closes web browser for testing(and does some other things)
 */
public class BaseTest {
    protected WebDriver chromeDriver;
    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();//obvious
        chromeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//(Неявные ожидания)
        chromeDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);//обычно достаточно одного
        chromeDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);//эти все просто для примера
    }

    @AfterEach
    public void closeGoogleTest() {
        chromeDriver.quit();
    }
}
/**
 *Задание 1.2
 * Задача:
 * 5.	запустить Chrome
 * 6.	открыть https://www.google.com/
 * 7.	написать в строке поиска «Открытие»
 * 8.	нажать Поиск
 * 9.	проверить, что результатах поиска есть https://www.open.ru
 * 10.	перейти на сайт https://www.open.ru
 * 11.	проверить в блоке «Курс обмена в интернет-банке», что курс продажи больше курса покупки, для USD и для EUR.
 * Автотест необходимо написать, используя данный стек:
 * Java, JUnit Jupiter, Selenium, PageFactory
 *
 * Изучить самостоятельно:
 * - как переключаться между вкладками браузера
 * - почитать про xpath, попрактиковаться
 */