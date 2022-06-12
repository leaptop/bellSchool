package com.google;

import org.junit.jupiter.api.Test;

public class TaskGladiolus extends BaseTest {
    @Test
    public void checkIfGladiolusWikiExists() {
        chromedriver.get("https://google.com/");

    }
}
