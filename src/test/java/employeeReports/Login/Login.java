package employeeReports.Login;

import base.BaseTest;
import org.example.pages.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login extends BaseTest {

    @Test
    public void loginAsAdmin() {
        DashboardPage dashboard = loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton();

        Assert.assertNotNull(dashboard, "Failed to authenticate and load the Dashboard Page.");
    }
}
