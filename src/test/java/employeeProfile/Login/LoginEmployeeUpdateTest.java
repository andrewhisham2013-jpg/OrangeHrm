package employeeProfile.Login;

import base.BaseTest;
import org.testng.annotations.Test;

public class LoginEmployeeUpdateTest extends BaseTest {

    @Test
    public void verifyEmployeePersonalInformationUpdateFlow() {
        loginPage
                .enterUsername(testData.get("username"))
                .enterPassword(testData.get("password"))
                .clickLoginButton();
    }
}
