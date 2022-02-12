package com.google;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

//good practise is to keep  output from hooks in a class named BaseTest
public class BaseTest {//this class opens and closes web browser for testing(and does some other things)

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
