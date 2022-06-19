package ru.bellintegrator;

import driver.Manager;
import org.junit.jupiter.api.Test;
import pages.CitilinkHomePage;
import pages.CitilinkVideocardsSearchPageFactory;
import pages.OpenPage;
import steps.Steps;

public class Tests extends BaseTest {

    @Test
    public void citilinkTest() {
        Steps.goPage("https://www.citilink.ru");
        CitilinkHomePage citilinkHomePage = new CitilinkHomePage();
        Steps.goMenu(citilinkHomePage, "Ноутбуки и компьютеры", "Комплектующие для ПК", "Видеокарты");
//        CitilinkVideocardsSearchPage citilinkVideocardsSearchPage = new CitilinkVideocardsSearchPage();
        CitilinkVideocardsSearchPageFactory citilinkVideocardsSearchPage = new CitilinkVideocardsSearchPageFactory();
        citilinkVideocardsSearchPage.getName();
    }

    @Test
    public void StepanOpenRuTest() {

        chromeDriver.get("https://www.open.ru");
        OpenPage op = new OpenPage(Manager.getCurrentDriver());
        op.getCollectExchangeRates();
    }
}