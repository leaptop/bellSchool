package ru.bellintegrator;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.*;
import steps.Steps;

import java.util.List;
import java.util.Map;

public class Tests extends BaseTest {

    @Feature("Проверка тайтла")
    @Test
    public void firstTestTitle(){
        chromeDriver.get("https://bellintegrator.ru/");
        String title = chromeDriver.getTitle();
        System.out.println(title);
        Assertions.assertTrue(title.contains("Bell Integrator"), "Тайтл "+title+" на сайте не содержит Bell Integrator");
    }

    @Feature("Проверка результатов поиска")
    @Test
    public void secondTestFind(){
        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search");
        WebElement searchField = chromeDriver.findElement(By.id("input-search"));
        WebElement searchButton = chromeDriver.findElement(By.xpath("//*[@id='button-search']"));
        searchField.click();
        searchField.sendKeys("RPA");
        searchButton.click();
        List<WebElement> resaultSearch = chromeDriver.findElements(By.xpath("//*[@class='product-layout product-list col-xs-12']//*[@class='short__desc']"));
        System.out.println(resaultSearch.size());
        resaultSearch.forEach(x-> System.out.println(x.getText()));
        Assertions.assertTrue(resaultSearch.stream().anyMatch(x->x.getText().contains("Кирилл Филенков")),
                "Статьи содержащие Филенков Кирилл не найдены для поискового слова RPA");
    }

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска c помощью PO")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"RPA,Кирилл Филенков","нагрузочное тестирование,Сергей Минаев"})
    public void testPO(String keyWords,String result){
        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search");
        BellBeforeSearch bellBeforeSearch = new BellBeforeSearch(chromeDriver);
        bellBeforeSearch.find(keyWords);
        BellAfterSearch bellAfterSearch = new BellAfterSearch(chromeDriver);
        Assertions.assertTrue(bellAfterSearch.getResults().stream().anyMatch(x->x.getText().contains(result)),
                "Статьи содержащие "+result+" не найдены для поискового слова "+keyWords);
    }

    @Feature("Проверка результатов поиска")
    @Test
    public void testPF(){
        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search");
        PageFactoryBell bellPageFactory = PageFactory.initElements(chromeDriver,PageFactoryBell.class);
        bellPageFactory.find("RPA");
        Assertions.assertTrue(bellPageFactory.getResults().stream().anyMatch(x->x.getText().contains("Кирилл Филенков")),
                "Статьи содержащие Филенков Кирилл не найдены для поискового слова RPA");
    }

    @Test
    public void testOpen(){
        GooglePageWithSearch googlePageWithSearch = new GooglePageWithSearch(chromeDriver,"открытие");
        List<Map<String,Object>> resultSearch = googlePageWithSearch.getCollectResults();
        googlePageWithSearch.goPage("Банк Открытие");
        OpenPage openPage = new OpenPage(chromeDriver);
        List<Map<String,String>> collectExchangeRates = openPage.getCollectExchangeRates();
        System.out.println(collectExchangeRates);
        Assertions.assertTrue(
                Double.parseDouble(
                        collectExchangeRates.stream()
                                .filter(x->x.get("Валюта обмена").contains("USD"))
                                .findFirst()
                                .get().get("Банк покупает").replace(",",".")
                )
                        < Double.parseDouble(
                        collectExchangeRates.stream()
                                .filter(x->x.get("Валюта обмена").contains("USD"))
                                .findFirst()
                                .get().get("Банк продает").replace(",",".")
                )
        );
    }

    @Feature("Проверка курса валют")
    @DisplayName("Проверка курса валют cо степами")
    @ParameterizedTest(name="{displayName} {arguments}")
    @Tag("Kotik")
    @CsvSource({"USD"})
    public void testOpenWithPage(String value){
        GooglePageWithSearch googlePageWithSearch = new GooglePageWithSearch(chromeDriver,"открытие");
        List<Map<String,Object>> resultSearch = googlePageWithSearch.getCollectResults();
        Steps.checkContainsName(resultSearch, "Банк Открытие", chromeDriver);
        Steps.goPageText(googlePageWithSearch, "Банк Открытие");
        OpenPage openPage = new OpenPage( chromeDriver);
        List<Map<String,String>> collectExchangeRates = openPage.getCollectExchangeRates();
        Steps.checkCourse(value,openPage);
    }
}