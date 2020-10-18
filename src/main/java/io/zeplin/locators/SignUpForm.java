package io.zeplin.locators;

import org.openqa.selenium.By;

import static org.openqa.selenium.By.*;

public class SignUpForm {
    public String inputByName = "//input[@name='%s']";
    public By spanCheckbox = xpath("//label[@class='termsOfService noSelect']//span[@class='checkbox']");
    public By signUpButton = xpath("//nav[@class='right']//a[text()='Sign up for free']");
}
