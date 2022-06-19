package com.google;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import driver.*;

public class BaseTest {
    protected WebDriver chromedriver;
    @BeforeEach
    public void beforeEach() {
        Manager.initChrome();
        chromedriver = Manager.getCurrentDriver();
    }
  //  @AfterEach
    public void afterEach()throws InterruptedException{
        Thread.sleep(500000);
        Manager.killCurrentDriver();
    }
}
