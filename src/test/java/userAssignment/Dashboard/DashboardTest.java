package userAssignment.Dashboard;

import base.BaseTest;
import org.example.pim.PimPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(DashboardTest.class);

    @Test
    public void verifyNavigationToPimModule() {
        log.info("Starting test: Navigate to PIM module using method chaining");

        PimPage pimPage = loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton();

        Assert.assertEquals(pimPage.getHeaderTitleText(), "PIM", "The page header title does not indicate you are inside the PIM module!");
        log.info("Finished test: Navigate to PIM module");
    }
}
