package userAssignment.Dashboard;

import base.BaseTest;
import reuse.JsonFileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DashboardEdgeTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(DashboardEdgeTest.class);
    private final JsonFileManager json = new JsonFileManager("src/main/resources/config.json");

    @Test
    public void verifyUnauthorizedUrlAccessAfterLogout() {
        log.info("Starting test: Unauthorized URL access after logout");

        softAssert = new SoftAssert();

        loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .logout();

        softAssert.assertEquals(getDriver().getTitle(), "OrangeHRM", "Logout did not redirect to the correct page title!");

        String baseUrl = json.getValueByKey("url");
        getDriver().get(baseUrl);

        Assert.assertEquals(getDriver().getTitle(), "OrangeHRM", "System allowed unauthorized forced access after logout based on page title!");

        log.info("Finished test: Unauthorized URL access after logout");
    }
}
