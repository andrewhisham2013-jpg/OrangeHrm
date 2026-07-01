package userAssignment.Pim;

import base.BaseTest;
import org.example.pim.AddEmployeePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PimAndAddEmpolyeeTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(PimAndAddEmpolyeeTest.class);

    @Test
    public void verifyNavigationToAddEmployeeScreen() {
        AddEmployeePage addEmployeePage = loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .clickAddEmployeeButton();

        Assert.assertEquals(addEmployeePage.getHeaderTitleText(), "Add Employee",
                "The browser failed to navigate to the Add Employee screen.");
    }
}
