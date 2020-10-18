package io.zeplin.methods;

import com.codeborne.selenide.ElementsCollection;
import io.zeplin.locators.BasePage;
import io.zeplin.locators.SignUpForm;
import io.zeplin.locators.UnivLocators;
import org.openqa.selenium.By;

import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Methods {
    Integer waitTime = 10000;
    UnivLocators univLocators = new UnivLocators();
    BasePage basePage = new BasePage();
    SignUpForm signUpForm = new SignUpForm();

    /**
     * Клик на кнопку по локатору By
     * @param locator локатор By
     */
    public void clickButton(By locator) {
        $(locator)
                .waitUntil(visible, waitTime)
                .click();
    }
    /**
     * Клик по универсальному локатору из класса UnivLocators
     * @param baseLocator базовый локатор String
     * @param buttonName часть базового локатора (имя кнопки)
     */
    public void clickButton(String baseLocator, String buttonName) {
        $(By.xpath(String.format(baseLocator, buttonName)))
                .waitUntil(visible, waitTime)
                .click();
    }

    /**
     * Установить значение в input
     * @param baseLocator базовый локатор
     * @param partLocator часть базового локатора
     * @param value значение
     */
    public void setValue(String baseLocator, String partLocator, String value) {
        $(By.xpath(String.format(baseLocator, partLocator)))
                .waitUntil(visible, waitTime)
                .setValue(value);
    }
    public void setValue(By locator, String value) {
        $(locator)
                .waitUntil(visible, waitTime)
                .setValue(value);
    }
    /**
     * Установить значение в составной локатор и вернуть его
     * @param baseLocator базовый локатор
     * @param partLocator часто базового локатора
     * @param value вернуть
     * @return
     */
    public String setValueReturned(String baseLocator, String partLocator, String value) {
        $(By.xpath(String.format(baseLocator, partLocator)))
                .waitUntil(visible, waitTime)
                .setValue(value);
        return value;
    }
    public String setValueReturned(By locator, String value) {
        $(locator)
                .waitUntil(visible, waitTime)
                .setValue(value);
        return value;
    }

    /**
     * Выбрать случайное значение из коллекции
     * @param collection коллекция
     */
    public void selectRandomValueFromCollection(By collection) {
        ElementsCollection elementsCollection = $$(collection);
        Random random = new Random();
        elementsCollection.get(random.nextInt(elementsCollection.size()))
                .waitUntil(exist, waitTime)
                .scrollIntoView(true)
                .click();
    }
    /**
     * Проверить хедер страницы
     * @param header хедер (h1)
     */
    public void assertHeader(String header) {
        $(univLocators.header)
                .shouldHave(text(header));
    }

    /**
     * Проверить текст, находящийся по локатору
     * @param locator локатор
     * @param value текст для проверки
     */
    public void checkLocatorText(By locator, String value) {
        $(locator)
                .shouldHave(text(value));
    }
    /**
     * Закрыть баннеры с просьбой о верификации емейла
     */
    public void closeBanners() {
        ElementsCollection elementsCollection = $$(basePage.bannersCloseIcon);
//        elementsCollection.forEach(clickElement -> clickElement.should(exist).click()); //не работает т.к баннеры расположены в другом порядке
        for (int i = elementsCollection.size();i>0;i--) {
            elementsCollection.get(i-1).should(exist).click();
        }
    }
    /**
     * Логаут
     */
    public void logout() {
        $(By.xpath(String.format(univLocators.spanText, "Log out")))
                .waitUntil(visible, waitTime)
                .click();
    }

    /**
     * Логин
     * @param username
     * @param password
     */
    public void login(String username, String password) {
        assertHeader("Great to see you again!");
        setValue(signUpForm.inputByName, "handle", username);
        setValue(signUpForm.inputByName, "password", password);
        clickButton(univLocators.buttonByTextContains, "Login");
    }
}
