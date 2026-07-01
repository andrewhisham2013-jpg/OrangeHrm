package userAssignment.Logintest;

import base.BaseTest;
import reuse.RetryAnalyzer;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyValidLogin() {
        loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton();
    }
}
