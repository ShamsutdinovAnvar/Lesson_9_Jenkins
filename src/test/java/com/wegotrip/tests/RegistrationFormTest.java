package com.wegotrip.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.wegotrip.pages.RegistrationFormPage;
import com.wegotrip.pages.testdata.UserTestData;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.wegotrip.Attach;

import static io.qameta.allure.Allure.step;

public class RegistrationFormTest {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void configure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("browserName", "chrome");
//        capabilities.setCapability("browserVersion", "100.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

        @Test
        void checkFormTest() {
            step("Open registrations form", () -> {
            registrationFormPage.openPage();
            });
            step("Fill form", () -> {
            registrationFormPage.setFirstName(UserTestData.firstName)
                    .setLastName(UserTestData.lastName)
                    .setUserEmail(UserTestData.email)
                    .setGender(UserTestData.gender)
                    .setUserNumber(UserTestData.mobile)
                    .setBirthDate(UserTestData.day, UserTestData.month, UserTestData.year)
                    .subjectsInput(UserTestData.subjects)
                    .setHobbies(UserTestData.hobbie)
                    .uploadPicture(UserTestData.picture)
                    .setAddress(UserTestData.address, UserTestData.state, UserTestData.city)
                    .clickSubmit();
            });
            //Check results
            step("Check form results", () -> {
            registrationFormPage.checkResultsTable()
                    .checkResult("Student Name", UserTestData.firstName + " " + UserTestData.lastName)
                    .checkResult("Student Email", UserTestData.email)
                    .checkResult("Gender", UserTestData.gender)
                    .checkResult("Mobile", UserTestData.mobile)
                    .checkResult("Date of Birth", UserTestData.birthDay)
                    .checkResult("Subjects", UserTestData.subjects)
                    .checkResult("Hobbies", UserTestData.hobbie)
                    .checkResult("Picture", UserTestData.picture)
                    .checkResult("Address", UserTestData.address)
                    .checkResult("State and City", UserTestData.state + " " + UserTestData.city);
            });
        }
    }

