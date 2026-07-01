package employeeReports.Validation;

import base.BaseTest;
import org.example.pim.PimReportPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidationTest extends BaseTest {

    @Test
    public void validateCustomEmployeeReport() {
        PimReportPage reportPage = new PimReportPage(this.driver);

        loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton();

        reportPage
                .navigateToPimReports()
                .clickAddReport()
                .configureReportDetails("Andrew Hisham report")
                .enterEmployeeCriterion("Andrew Hisham")
                .selectDisplayFields("Personal", "Employee First Name")
                .selectDisplayFields("Personal", "Nationality")
                .selectDisplayFields("Personal", "Marital Status")
                .selectDisplayFields("Contact Details", "Address")
                .selectDisplayFields("Emergency Contacts", "Name")
                .selectDisplayFields("Emergency Contacts", "Relationship")
                .selectDisplayFields("Emergency Contacts", "Home Telephone")
                .clickSaveReport();

        Assert.assertEquals(reportPage.getReportHeaderTitleText(), "Andrew Hisham report", "The report header title does not match the provided report configuration name.");
    }
}
