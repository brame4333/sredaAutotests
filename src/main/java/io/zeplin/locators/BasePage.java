package io.zeplin.locators;

import org.openqa.selenium.By;

public class BasePage {
    public By profileIcon = By.xpath("//button[@class='currentUser noSelect']");
    public By selectProfile = By.xpath("//span[@class='username ellipsis']");
    public By profileEmail = By.xpath("//span[@class='info ellipsis']");
    public By bannersCloseIcon = By.xpath("//button[@class='bannerCloseButton']//img");
}
