package timeSheet.Login;

import base.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void verifyEmployeeNavigatesToTimesheet() {
        loginPage
                .enterUsername(json.getNestedValue("newEmployee", "username"))
                .enterPassword(json.getNestedValue("newEmployee", "password"))
                .clickLoginButton()
                .pressOnTimeButton();
    }
}
