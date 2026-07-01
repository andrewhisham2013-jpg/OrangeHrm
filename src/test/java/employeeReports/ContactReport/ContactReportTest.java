package employeeReports.ContactReport;

import base.BaseTest;
import org.testng.annotations.Test;

public class ContactReportTest extends BaseTest {

    @Test
    public void createCustomEmployeeReport() {
        loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .navigateToPimReports()
                .clickAddReport()
                .configureReportDetails("Andrew Hisham report")
                .enterEmployeeCriterion(json.getNestedValue("newEmployee", "firstName") + " " + json.getNestedValue("newEmployee", "lastName"))
                .selectDisplayFields("Personal", "Employee First Name")
                .selectDisplayFields("Personal", "Nationality")
                .selectDisplayFields("Personal", "Marital Status")
                .selectDisplayFields("Emergency Contacts", "Name")
                .selectDisplayFields("Emergency Contacts", "Relationship")
                .selectDisplayFields("Emergency Contacts", "Home Telephone")
                .clickSaveReport();
    }
}
