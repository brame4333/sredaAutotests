package io.zeplin.locators;

import org.openqa.selenium.By;

public class UnivLocators {
    public String divText = "//div[text()='%s']";
    public String aText = "//a[text()='%s']";
    public String aTextContains = "//a[contains(text(), '%s')]";
    public String buttonByText = "//button[text()='%s']";
    public String buttonByTextContains = "//button[contains(text(), '%s')]";
    public String spanText = "//span[text()='%s']";
    public By header = By.xpath("//h1");
}
