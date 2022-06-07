package tests.selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import custom.assertions.Assertions;
import custom.properties.PropsDriver;
import custom.properties.PropsUrl;
import custom.properties.TestData;
import io.qameta.allure.Feature;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import pages.selenide.GoogleMainPage;
import pages.selenide.GoogleSearchResult;
import pages.selenide.OpenMainPage;
import pages.selenide.WikiMainPage;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Tests extends BaseTests {

    @Test
    public void firstSelenide() {
        open("https://www.google.ru/");
        $(By.name("q")).setValue("Открытие").pressEnter();
        //$$x("//div[@class='g']").shouldHave(size(25));
        ElementsCollection resultSearch = $$x("//div[@class='g']");
        System.out.println(resultSearch);
        SelenideElement elemWiki = resultSearch.find(text("Открытие (банк) - Википедия"));
        System.out.println("_______________");
        SelenideElement elemOtkr = $x("//div[@class='g']").shouldHave(text("Банк Открытие: Частным клиентам"));
        elemOtkr.$x(".//a[@href]").click();
        switchTo().window(1);
        System.out.println(title());
        System.out.println($x("//*[@class='main-page-exchange main-page-info__card']").getText());
        Assertions.assertTrue(true,"Отладочная ошибка");
    }

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска c помощью PO")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"USD"})
    public void otkrSelenideShort(String typeMoney) {
        open(TestData.propsUrl.baseURLGoogle(), GoogleMainPage.class)
                .search("Открытие")
                .goLinkByName("Банк Открытие: Частным клиентам", OpenMainPage.class)
                .checkBuySell(typeMoney);
    }

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска c помощью PO")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"USD"})
    public void otkrSelenideShortWiki(String typeMoney) {
        open("https://www.google.ru/", GoogleMainPage.class)
                .search("Открытие")
                .goLinkByName("Открытие (банк) - Википедия", WikiMainPage.class)
                .search("Cat");
    }

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска c помощью PO")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"USD"})
    public void otkrSelenideShort2(String typeMoney) {
       GoogleMainPage googleMainPage = open("https://www.google.ru/", GoogleMainPage.class);
       GoogleSearchResult googleSearchResult = googleMainPage.search("Открытие");
       OpenMainPage openMainPage = googleSearchResult.goLinkByName("Банк Открытие: Частным клиентам", OpenMainPage.class);
       openMainPage.checkBuySell(typeMoney);
    }

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска c помощью PO")
    @ParameterizedTest(name = "{displayName} {arguments}")
    @CsvSource({"USD"})
    public void otkrSelenideShort3(String typeMoney) {
        open("https://www.google.ru/");
        GoogleMainPage googleMainPage = new GoogleMainPage();
        googleMainPage.search("Открытие");
        GoogleSearchResult googleSearchResult = new GoogleSearchResult();
        googleSearchResult.goLinkByName("Банк Открытие: Частным клиентам", OpenMainPage.class);
        OpenMainPage openMainPage = new OpenMainPage();
        openMainPage.checkBuySell(typeMoney);

    }

}
