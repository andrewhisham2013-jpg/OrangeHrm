package employeeDependents.Login;

import base.BaseTest;
import org.example.myinfo.MyInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    protected static MyInfoPage myInfo;

    @Test
    public void verifyUserAuthenticationAndNavigation() {
        myInfo = loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .searchAndOpenEmployeeDetails("Andrew Hisham");

        Assert.assertNotNull(myInfo, "Failed to navigate to the Employee Info panel.");
    }
}