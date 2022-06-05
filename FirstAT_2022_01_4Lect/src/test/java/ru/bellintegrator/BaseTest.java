package ru.bellintegrator;

import driver.Manager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver chromeDriver;

    @BeforeEach
    public void before() {
        Manager.initChrome();
        chromeDriver = Manager.getCurrentDriver();
    }

    @AfterEach
    public void closeBellTest() {
        Manager.killCurrentDriver();
    }

}
