package com.example;

import static org.testng.AssertJUnit.assertEquals;

public class Steps {

    private DriverManager driverManager = new DriverManager();

    public void openBrowserStep() {
        driverManager.getDriver();
    }

    public void goToSelectedPageStep(String pageUrl) {
        driverManager.getDriver().get(pageUrl);
    }

    public void checkIsPageTitleEqualsStep(String title) {
        String titleFromPage = driverManager.getDriver().getTitle();
        assertEquals("Ошибка! Заголовок страницы не равен ожидаемому", title, titleFromPage);
    }

    public void closeBrowserStep() {
        driverManager.getDriver().close();
    }
}
