package pages.selenide;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class GoogleSearchResult extends BasePage {

    @Step("Поереходим по имени ссылки {linkName}")
    public <T extends BasePage> T goLinkByName(String linkName, Class<T> typeNextPage){
        SelenideElement elem = $x("//div[@class='g']").shouldHave(text(linkName));
        elem.$x(".//a[@href]").click();
        switchTo().window(1);
        return typeNextPage.cast(page(typeNextPage));
    }
}
