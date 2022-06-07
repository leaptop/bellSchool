package com.google;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.GooglePageAfterSearch;
import pages.GooglePageBeforeSearch;

public class Tests extends BaseTest{
    /**
     * Смотрим, есть ли  "wikipedia.org" в выдаче при гуглении "Гладиолус"
     * CSV- comma-separated values
     * @param keywords
     * @param result
     */
    @Feature("Проверка результатов поиска")
    @ParameterizedTest(name="{displayName} {arguments}")
    @CsvSource("Гладиолус, wikipedia.org")
    public void test_1_1_withPageObjectParameterized(String keywords, String result) {
        chromeDriver.get("https://www.google.com/search");
        GooglePageBeforeSearch googlePageBeforeSearch = new GooglePageBeforeSearch(chromeDriver);
        googlePageBeforeSearch.find(keywords);
        GooglePageAfterSearch googlePageAfterSearch = new GooglePageAfterSearch(chromeDriver);
        Assertions.assertTrue(googlePageAfterSearch.getResults().stream().anyMatch(x -> x.getText().contains(result)),
                "На первой странице результатов поиска '" + keywords + "' не найдено '" + result+"'\n");
    }
}
