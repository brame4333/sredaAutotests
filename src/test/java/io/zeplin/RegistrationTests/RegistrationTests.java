package io.zeplin.RegistrationTests;

import io.zeplin.Fixture.Main;
import io.zeplin.steps.Steps;
import org.testng.annotations.Test;

public class RegistrationTests extends Main {
    Steps steps = new Steps();

    @Test(description = "Регистрация [позитивный сценарий]")
    public void positiveRegistration() {
        steps.goToRegistrationForm();
        steps.fillRegistrationFormAndGoToWebApp();
        steps.checkProfile();
        steps.reLogin();
    }

}
