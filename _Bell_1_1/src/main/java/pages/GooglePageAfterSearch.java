package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GooglePageAfterSearch extends GoogleBeforeSearchPageObject {
    public GooglePageAfterSearch(WebDriver wd) {
        super(wd);
    }

    /**
     * Данный локатор выбирает все теги "а", но при этом в них атрибут href может быть пустым,
     * поэтому применение к нему вызова contains приводит к NullPointerException.
     */
    public String eachRefInAdditionalResults_Locator =
            "//div/h2[contains(text(),\"Дополнительные результаты\")]/..//a";
    /**
     * Данный локатор выбирает все теги "а" в дополнительных результатах поиска. Причём в этих тегах
     * присутствует атрибут href, в котором содержится текст `http`. Т.о. к вебелементам, представляющим
     * эти теги, можно будет применить contains.
     */
    public String eachRefInAdditionalResultsWithHREF_HTTP_Locator =
            "//div/h2[contains(text(),\"Дополнительные результаты\")]/..//a[contains(@href,'http')]";
    /**
     * Этот локатор не совсем подходит, т.к. добавляет в выборку тексты со всем содержимым, а мне нужны только
     * вебелементы, основанные на теге "а", чтобы можно было их добавить из соновного и из дополнительного
     * результатов поиска в один список, а потом в нём искать нужную ссылку.
     */
    public String resultsGeneral_Locator = "//cite[@role=\"text\"]";
    public  String resultsGenearlA_Based_Locator =
    public String resultsTemp_Locator;
    /**
     * Видимо, недпустимо использовать такой локатор, т.к. на странице результатов поиска может быть
     * просто рекламная или какая-нибудь юбилейная ссылка на википелию (искомый сайт). При этом в
     * результатах поиска её не будет.
     */
    public String searchingTheWholeDocumentForRefsWithText = "//a[contains(@href,'ru.wikipedia.org')]";
    private List<WebElement> results;

    /**
     * Нужно поменять так, чтобы хранились ссылки( вебелементы, основанные на теге "а").
     * @return
     */
    public List<WebElement> getResults() {
        results = chromedriver.findElements(By.xpath(resultsGeneral_Locator));
        //results.addAll(chromedriver.findElements(By.xpath(eachRefInAdditionalResultsWithHREF_HTTP_Locator)));
        return results;
    }

    public List<WebElement> getAdditionalResults() {
        results = chromedriver.findElements(By.xpath(eachRefInAdditionalResultsWithHREF_HTTP_Locator));
        return results;
    }

    /**
     * Вариант рабочий, но ожидание занимает все 30 секунд... работает медленно
     *
     * @param text
     * @return
     */
    public boolean searchTextInAdditionalResults0(String text) {
        resultsTemp_Locator =
                "//div/h2[contains(text(),\"Дополнительные результаты\")]/..//a[contains(@href,'"
                        + text
                        + "')]";
        WebElement additionalResults = chromedriver.findElement(By.xpath(resultsTemp_Locator));
        if (additionalResults.isDisplayed()) return true;
        else return false;
    }

    /**
     * Вариант рабочий, но ожидание занимает все 30 секунд... работает медленно
     *
     * @param text
     * @return
     */
    public boolean searchTextInGeneralResults0(String text) {
        resultsTemp_Locator = "//div[@class='g']//a[contains(@href,"
                + text
                + "')]";
        WebElement generalResults = chromedriver.findElement(By.xpath(resultsTemp_Locator));
        if (generalResults.isDisplayed()) return true;
        else return false;
    }


    ;
}
