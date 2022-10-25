package com.wegotrip.pages.components;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultComponent {
    private final static String TITLE_TEXT = "Thanks for submitting the form";

    public void checkVisible() {
        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text(TITLE_TEXT));

    }
    public void checkResult(String key, String value) {
        $(".table-responsive table")
                .$(byText(key)).parent().shouldHave(text(value));
    }
}
