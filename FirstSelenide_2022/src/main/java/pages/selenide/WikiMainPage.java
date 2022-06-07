package pages.selenide;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class WikiMainPage extends BasePage{
    @Step("Ищу слово {query}")
    public WikiMainPage search(String query){
       //todo
        return this;
    }
}
