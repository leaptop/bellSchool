package com.yandex;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTests {

    @BeforeEach
    public void option(){
        Configuration.timeout=6000;
        Configuration.browser="chrome";
        Configuration.startMaximized=true;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;

        /*
        System.setProperty("webdriver.chrome.driver",System.getenv("CHROME_DRIVER"));
        WebDriver driver;
        driver = new ChromeDriver(options);
        setWebDriver(driver);
         */
    }
}
