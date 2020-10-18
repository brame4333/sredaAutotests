package io.zeplin.steps;
import io.qameta.allure.Step;
import io.zeplin.generate.Generate;
import io.zeplin.locators.UnivLocators;
import io.zeplin.methods.Methods;
import io.zeplin.locators.BasePage;
import io.zeplin.locators.SignUpForm;

import static org.apache.commons.lang3.RandomStringUtils.*;
import static com.codeborne.selenide.Selenide.*;

public class Steps {
    Methods methods = new Methods();
    Generate generate = new Generate();
    UnivLocators univLocators = new UnivLocators();
    SignUpForm signUpForm = new SignUpForm();
    BasePage basePage = new BasePage();
    private String email, username, password;
    /**
     * Переход на стартовую страницу (изначально берется из config.properties)
     * @param url url страницы
     */
    @Step("Открыть стартовую страницу")
    public void openStartPage(String url) {
        open(url);
    }

    @Step("Переход на форму регистрации")
    public void goToRegistrationForm() {
        methods.clickButton(signUpForm.signUpButton);
        methods.assertHeader("Start using Zeplin!");
    }

    @Step("Заполнить форму регистрации")
    public void fillRegistrationFormAndGoToWebApp() {
        email = methods.setValueReturned(signUpForm.inputByName, "email", generate.generateDataForInput(1));
        username = methods.setValueReturned(signUpForm.inputByName, "username", randomAlphabetic(6));
        password = methods.setValueReturned(signUpForm.inputByName, "password", randomAlphabetic(10));
        methods.clickButton(signUpForm.spanCheckbox);
        methods.clickButton(univLocators.buttonByTextContains, "Sign up for free");
        methods.assertHeader("Thank you! Great to see you here ");
        methods.clickButton(univLocators.aTextContains, "Go to web app");
        methods.assertHeader("Welcome to Zeplin! We are glad you’re here");
        methods.closeBanners();
    }
    @Step("Проверить зарегистрированный профиль")
    public void checkProfile() {
        methods.clickButton(basePage.profileIcon);
        methods.checkLocatorText(basePage.selectProfile, username); //тут сравнение без учета регистра, т.к на сайте не учитывается регистр
        methods.checkLocatorText(basePage.profileEmail, email);
    }
    @Step("Релогин и проверка авторизации")
    public void reLogin() {
        methods.logout();
        methods.login(username, password);
    }

}
