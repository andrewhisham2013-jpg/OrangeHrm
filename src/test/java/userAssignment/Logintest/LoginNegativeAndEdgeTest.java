package userAssignment.Logintest;

import base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginNegativeAndEdgeTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginNegativeAndEdgeTest.class);

    @Test(priority = 1)
    public void verifyLoginWithCompletelyInvalidCredentials() {
        log.info("Starting test: Completely invalid credentials");

        loginPage
                .enterUsername("FalseAdminUser")
                .enterPassword("NotRealPassword123!")
                .clickLoginButton();

        String expectedError = "Invalid credentialss";
        Assert.assertEquals(loginPage.getErrorMessageText(), expectedError, "System failed to reject non-existent credentials!");
        log.info("Finished test: Completely invalid credentials");
    }

    @Test(priority = 2)
    public void verifyLoginWithCorrectUsernameButWrongPassword() {
        log.info("Starting test: Correct username, wrong password");

        loginPage
                .enterUsername("Admin")
                .enterPassword("completelyWrongPassword")
                .clickLoginButton();

        String expectedError = "Invalid credentials";
        softAssert.assertEquals(loginPage.getErrorMessageText(), expectedError, "System failed to block valid username paired with a wrong password!");
        softAssert.assertAll();
        log.info("Finished test: Correct username, wrong password");
    }

    @Test(priority = 3)
    public void verifyLoginWithWrongUsernameButCorrectPassword() {
        log.info("Starting test: Wrong username, correct password");

        loginPage
                .enterUsername("WrongAdminName")
                .enterPassword("admin123")
                .clickLoginButton();

        String expectedError = "Invalid credentials";
        softAssert.assertEquals(loginPage.getErrorMessageText(), expectedError, "System failed to block an invalid username paired with a real password!");
        softAssert.assertAll();
        log.info("Finished test: Wrong username, correct password");
    }

    @Test(priority = 4)
    public void verifyLoginWithCaseSensitivityEdgeCase() {
        log.info("Starting test: Case sensitivity edge case");

        loginPage
                .enterUsername("ADMIN")
                .enterPassword("ADMIN123")
                .clickLoginButton();

        String expectedError = "Invalid credentials";
        softAssert.assertEquals(loginPage.getErrorMessageText(), expectedError, "System accepted incorrect uppercase variations!");
        softAssert.assertAll();
        log.info("Finished test: Case sensitivity edge case");
    }

    @Test(priority = 5)
    public void verifyLoginWithEmptyFieldsBoundaryCondition() {
        log.info("Starting test: Empty fields boundary condition");

        loginPage
                .enterUsername("")
                .enterPassword("")
                .clickLoginButton();

        String expectedValidation = "Required";
        softAssert.assertEquals(loginPage.getRequiredFieldMessageText(), expectedValidation, "Input missing-value flags failed to trigger!");
        softAssert.assertAll();
        log.info("Finished test: Empty fields boundary condition");
    }
}
