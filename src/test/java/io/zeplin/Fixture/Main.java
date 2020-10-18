package io.zeplin.Fixture;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.zeplin.steps.Steps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    static Steps steps = new Steps();
    Properties prop = new Properties();
    public static final String pathToProperties = "src/main/resources/config.properties";
    //vars from config.properties
    public String url, browserName;

    @BeforeClass(description = "Запуск браузера и применение конфигурации")
    public void beforeClass() { setConfiguration(); } //применение настроек браузера
    @BeforeMethod
    public void beforeMethod() { steps.openStartPage(url);} //открыть стартовую страницу (url из config.properties)
    @AfterClass
    public void afterClass() { Selenide.closeWebDriver(); } //убить драйвер

    public void loadProperties() { //взаимодействие с файлом config.properties
        try {
            prop.load(new FileInputStream(pathToProperties));
            url = prop.getProperty("url");
            browserName = prop.getProperty("browserName");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void setConfiguration() { //настройка конфигурации браузера
        loadProperties();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.browser = browserName;
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
        }
}
