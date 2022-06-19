package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenPage {

    private String selectorExchangeRates = "//*[@class='main-page-exchange main-page-info__card']";
    private String selectorTableHeaders=".//tbody/tr[contains(@class,'header')]/td";//преключается по заголовкам таблицы обмена//"это де-факто лист заголовков таблицы с валютами"
    private String selectorTableRows = ".//tbody/tr[contains(@class,'row')]";//Это внутри таблицы выбирает по очереди строки таблицы с обменом//"лист строк"

    private WebDriver driver;

    private WebElement exchangeRates;
    private List<Map<String,String>> collectExchangeRates = new ArrayList<>();

    public WebDriver getDriver() {
        return driver;
    }//Это вроде для аллюра

    public WebElement getExchangeRates() {
        return exchangeRates;
    }//Это вроде для аллюра

    public OpenPage(WebDriver driver){
        this.driver=driver;
        exchangeRates= driver.findElement(By.xpath(selectorExchangeRates));
    }

    public List<Map<String, String>> getCollectExchangeRates() {//Метод возвращает коллекцию с курсом валют
        List<WebElement> tableHeaders = exchangeRates.findElements(By.xpath(selectorTableHeaders));
        List<WebElement> tableRows = exchangeRates.findElements(By.xpath(selectorTableRows));
        for(int i= 0; i<tableRows.size();++i){
            Map<String,String> collectRow=new HashMap<>();//Создал коллекцию типа Map.
            for (int j=0;j<tableHeaders.size();++j){
                //Первое добавление:
                //к1: Валюта        v1: USD
                //Второе добавление:
                //k2: Банк покупает v2: 46.3
                //Третье добавлене:
                //k3: Банк продаёт  v3: 66.5
                //Здесь заголовки закончились
                collectRow.put(//Добавляю в коллекцию ключ: название столбца, значение: ячейку в столбце (текущего ряда конечно)
                        tableHeaders.get(j).getText(),//Т.о. в итоге весь ряд сохранил в мапе, с подписью каждой ячейки по названию её столбца.
                        tableRows.get(i).findElement(By.xpath("./td["+(j+1)+"]")).getText()//td - ячейка
                );
            }
            collectExchangeRates.add(collectRow);//Добавил в коллекцию ArrayList очередную коллекцию Map<String, String>
        }//вторая - текст ячейки.
        return collectExchangeRates;
    }
}
