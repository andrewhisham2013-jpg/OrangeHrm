package userAssignment.Pim;

import base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import reuse.JsonFileManager;

public class PimAndAddEmployeeNegativeFlowTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(PimAndAddEmployeeNegativeFlowTest.class);
    private final JsonFileManager json = new JsonFileManager("src/main/resources/config.json");

    @Test(priority = 1)
    public void verifyUnauthorizedDirectUrlAccessToAddEmployeeScreen() {
        log.info("Starting negative test: Accessing Add Employee screen directly without authenticating");

        getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee");

        Assert.assertEquals(getDriver().getTitle(), "OrangeHRM", "Security breach: System allowed direct unauthorized access to the Add Employee page!");

        log.info("Finished negative test: Accessing Add Employee screen directly without authenticating");
    }

    @Test(priority = 2)
    public void verifyBackNavigationToAddEmployeeScreenIsBlockedAfterLogout() {
        loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .clickAddEmployeeButton()
                .logout();

        getDriver().navigate().back();

        Assert.assertEquals(getDriver().getTitle(), "OrangeHRM",
                "Edge case failed: Browser back button exposed data or allowed unauthorized layout caching after logout!");
    }
}
