package ru.yandex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/** We prepare our web browser ( Chrome driver) to work.
 * Timeouts are added to wait for some events ( before closing the browser?).     *
 * Usually one timeout/wait is enough. Those others are included just for an example.
 *
 * Good practise is to keep output from hooks in a class named BaseTest.
 * This class opens and closes web browser for testing(and does some other things)
 */
public class BaseTest {
    protected WebDriver chromeDriver;
    protected int waitingTime30 = 120;
    WebDriverWait wait;
    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        chromeDriver = new ChromeDriver();
        wait = new WebDriverWait(chromeDriver,30);
        chromeDriver.manage().window().maximize();//obvious
        chromeDriver.manage().timeouts().implicitlyWait(waitingTime30, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().pageLoadTimeout(waitingTime30, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().setScriptTimeout(waitingTime30, TimeUnit.SECONDS);
    }

    @AfterEach
    public void closeGoogleTest() {
      // chromeDriver.quit();
    }
}
/**
 * UI
 * Если гугл вас лочит, используйте любой другой поисковик
 * Задание 1.3
 * Задача:
 * 1. Открыть браузер и развернуть на весь экран.
 * 2. Зайти на yandex.ru.
 * 3. Перейти в яндекс маркет
 * 4. Выбрать раздел Компьютеры         ПОСЛЕ КАЖДОГО ДЕЙСТВИЯ И ПЕРЕД ПРОИЗВОДСТВОМ СЛЕДУЮЩЕГО НУЖНО ЖДАТЬ
 *                                      ДОСТУПНОСТИ ВЕБ-ЭЛЕМЕНТА. ИНАЧЕ МОЖЕТ НЕ ВСЁ НАЖАТЬСЯ
 * 5. Выбрать раздел Ноутбуки
 * 6. Задать параметр «Цена, Р» от  10000 до 90000 рублей.
 * 7. Выбрать производителя HP и Lenovo
 * 8. Дождаться результатов поиска.
 * 9. Установить количество показываемых элементов на страницу 12 (Элемент находиться в самом низу страницы). Не нашёл этот элемент
 * 10. Дождаться обновления результатов.
 * 11. Проверить, что на странице отображалось 12 элементов.
 * 12. Запомнить наименование первого значения в списке.
 * 13. В поисковую строку ввести запомненное значение.
 * 14. Нажать кнопку «Найти»
 * 15. Проверить, что наименование товара соответствует сохраненному значению.
 * Автотест необходимо написать, используя данный стек:
 * Java, Junit Jupiter, Selenium, PageObject или PageFactory
 * По итогу:
 * -	Делаем ровно то что написано
 * -	Недопустимо использования Thread.sleep. За исключением, создания собственных ожиданий
 *      (к примеру каждый 5 милисекунд проверяем что что-то случилось, и так не более 10 секунд.).
 *      Лучше обойтись явными\неявными ожиданиями
 * -	Обязательно писать JavaDoc
 * -	Помните про универсальные методы. Старайтесь писать код, полезный в других тестах, полезный коллегам.
 *      Не будьте эгоистами!
 */