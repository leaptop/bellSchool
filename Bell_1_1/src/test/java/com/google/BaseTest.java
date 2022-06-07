package com.google;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Good practise is to keep  output from hooks in a class named BaseTest.
 * This class opens and closes web browser for testing(and does some other things).
 */
public class BaseTest {
    protected WebDriver chromeDriver;

    /** We prepare our web browser ( Chrome driver) to work.
     * Timeouts are added to wait for some events ( before closing the browser?).     *
     * Usually one timeout/wait is enough. Those others are included just for an example.
     */
    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();//obvious
        chromeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
    }

    /**
     * Closes the window after checking the test.
     * If needed to watch what happens slower one can comment the  chromeDriver.quit(); line.
     */
    @AfterEach
    public void closeGoogleTest() {
        //chromeDriver.quit();
    }
}
/**
 * UI
 * Если гугл вас лочит, используйте любой другой поисковик
 * Задание 1.1
 * Задача:
 * 1.	запустить Chrome
 * 2.	Открыть https://www.google.com/
 * 3.	Ввести «Гладиолус». Нажать поиск
 * 4.	Убедится что в полученной выборке на первой странице есть ссылка на википедию
 *  Автотест необходимо написать, используя данный стек:
 * Java, Junit Jupiter, Selenium, PageObject
 */