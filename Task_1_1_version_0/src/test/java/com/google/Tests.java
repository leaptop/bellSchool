//ЛЮБОЙ ПАТТЕРН ДОЛЖЕН БЫТЬ ИСПОЛЬЗОВАН СТРОГО, ЕСЛИ НУЖНО РЕШИТЬ СООТВТЕТСВУЮЩУЮ ЗАДАЧУ
//ПАТТЕРН - УСЛОЖНЕНИЕ КОДА! ПОЭТОМУ ПРОСТО ТАК ИХ ИСПОЛЬЗОВАТЬ НЕЛЬЗЯ.

//НИКАКИХ ДРАЙВЕРОВ НЕ ДОЛЖНО БЫТЬ. ПАПКА ALLURE-RESULTS ПОЧИЩЕНА. 2 ПРОЕКТА В АРХИВАХ RAR БЕЗ SOUT.

//A pattern PageFactory is used for situations where you want to avoid multiple pages invokes. The idea is:
//1 We don't create separate classes for each page, but one class for multiple pages
//2 Selectors are written right away
//3 Search for elements at the same time with a need of calling them
//You shouldn't confuse functionality of selenium. ... .PageFactory with the pattern of the same name.
package com.google;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.GooglePageAfterSearch;
import pages.GooglePageBeforeSearch;
import pages.PageFactoryOpenRu;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests extends BaseTest {//These tests work, but they have to be remade with PageObject and PageFactory

    @Test
    public void testTabs() throws InterruptedException {
        //driver.get("https://business.twitter.com/start-advertising");
        driver.get("https://www.tutorialspoint.com/about/about_careers.htm");
        // method Keys.chord
        String n = Keys.chord(Keys.CONTROL, Keys.ENTER);//Ctrl + click opens a link in a new tab
        //open link in new tab
        driver.findElement(By.linkText("Library")).sendKeys(n);//в любом случае какой-то линк надо будет открыть, ведь новая вкладка пустой не нужна для тестовой прогаммы.
        Thread.sleep(8000);//нужно некоторое время, чтобы вторая вкладка открылась и имело смысл вызывать driver.getWindowHandles()
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(1));

    }

    @DisplayName("Проверка тайтла ну")
    @Feature("Проверка тайтла")
    @Test
    public void firstTestTitle() {//just checking if title contains a word
        driver.get("https://google.com/");
    driver.getWindowHandle();
    }

    @Feature("Проверка результатов поиска")
    @Test
    public void test_1_1() {
        driver.get("https://www.google.com/search");
        WebElement searchField = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"));//saved an element from the site
        WebElement searchButton = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[3]/center/input[1]"));
        ////*[@id="tsf"]/div[1]/div[1]/div[2]/div[1]/button//previously was full xpath. And this is an example of just xpath
        searchField.click();
        searchField.sendKeys("Гладиолус");//                        //*[@id="rso"]/div[1]/div/div[1]/div/a/div/cite
        searchButton.click();
        List<WebElement> resultSearch = driver.findElements(By.xpath("//cite"));
        System.out.println("resultSearch.size() = " + resultSearch.size());
        resultSearch.forEach((x -> System.out.println(x.getText())));
        System.out.println("------------------------------------------------------------------");
        Assertions.assertTrue(resultSearch.stream().anyMatch(x -> x.getText().contains("wikipedia.org")));
    }

    @Feature("Проверка результатов поиска")
    @Test
    public void test_1_2_1() {//AWKWARD METHOD
        driver.get("https://www.google.com/search");
        WebElement searchField = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"));//saved an element from the site
        WebElement searchButton = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[3]/center/input[1]"));
        ////*[@id="tsf"]/div[1]/div[1]/div[2]/div[1]/button//previously was full xpath. And this is an example of just xpath
        searchField.click();
        searchField.sendKeys("«Открытие»");//                        //*[@id="rso"]/div[1]/div/div[1]/div/a/div/cite
        searchButton.click();
        List<WebElement> resultSearch = driver.findElements(By.xpath("//cite"));
        System.out.println("resultSearch.size() = " + resultSearch.size());
        resultSearch.forEach((x -> System.out.println(x.getText())));
        System.out.println("------------------------------------------------------------------");
        Assertions.assertTrue(resultSearch.stream().anyMatch(x -> x.getText().contains("https://www.open.ru")));
    }

    @Feature("Проверка результатов поиска")
    @Test
    public void test_1_1_withPageObject() {
        driver.get("https://www.google.com/search");
        GooglePageBeforeSearch googlePageBeforeSearch = new GooglePageBeforeSearch(driver);
        googlePageBeforeSearch.find("Гладиолус");
        GooglePageAfterSearch googlePageAfterSearch = new GooglePageAfterSearch(driver);
        Assertions.assertTrue(googlePageAfterSearch.getResults().stream().anyMatch(x -> x.getText().contains("wikipedia.org")));
    }

    @Feature("Проверка результатов поиска")
    @ParameterizedTest(name="{displayName} {arguments}")
    @CsvSource("Гладиолус, wikipedia.org")
    public void test_1_1_withPageObjectParameterized(String keywords, String result) {
        driver.get("https://www.google.com/search");
        GooglePageBeforeSearch googlePageBeforeSearch = new GooglePageBeforeSearch(driver);
        googlePageBeforeSearch.find(keywords);
        GooglePageAfterSearch googlePageAfterSearch = new GooglePageAfterSearch(driver);
        Assertions.assertTrue(googlePageAfterSearch.getResults().stream().anyMatch(x -> x.getText().contains(result)),
                "На первой странице результатов поиска " + keywords + " не найдено " + result);
    }

    @Feature("Проверка результатов поиска")
    @Test
    public void test_1_2_a_withPageFactory() {
        driver.get("https://www.google.com/search");
        PageFactoryOpenRu pageFactoryOpenRu = PageFactory.initElements(driver, PageFactoryOpenRu.class);//important: invoke selenium's PageFactory
        pageFactoryOpenRu.find("Открытие");
        // Assertions.assertTrue(googlePageAfterSearch.getResults().stream().anyMatch(x->x.getText().contains("https://www.open.ru")));
        System.out.println("pageFactoryOpenRu.getResults().size() = " + pageFactoryOpenRu.getResults().size());
        Assertions.assertTrue(pageFactoryOpenRu.getResults().stream().anyMatch(x -> x.getText().contains("https://www.open.ru")));
    }
    @Test
    public void test_1_2_b_withPageFactory() {
        driver.get("https://www.open.ru");
        PageFactoryOpenRu pageFactoryOpenRu = PageFactory.initElements(driver, PageFactoryOpenRu.class);//important: invoke selenium's PageFactory
        double bbu = Double.parseDouble(pageFactoryOpenRu.getBankBuysUSD().getText().replace(',','.'));
        double bsu = Double.parseDouble(pageFactoryOpenRu.getBankSellsUSD().getText().replace(',','.') );
        double bbe = Double.parseDouble(pageFactoryOpenRu.getBankBuysEUR().getText().replace(',','.') );
        double bse = Double.parseDouble(pageFactoryOpenRu.getBankSellsEUR().getText().replace(',','.') );
        Assertions.assertTrue(bbu<bsu);
        Assertions.assertTrue(bbe<bse);
    }

    @Test
    public void testOpen(){

    }

}
