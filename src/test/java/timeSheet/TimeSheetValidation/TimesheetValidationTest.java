package timeSheet.TimeSheetValidation;

import base.BaseTest;
import org.example.time.TimePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TimesheetValidationTest extends BaseTest {

    @Test
    public void verifyAdminValidatesEmployeeTimesheet() {
        TimePage timePage = loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnTimeButton()
                .filterByEmployee(json.getNestedValue("newEmployee", "firstName") + " " + json.getNestedValue("newEmployee", "lastName"));

        Assert.assertEquals(timePage.getStaticHoursByDayIndex(0), "08:00", "Monday hours mismatch!");
        Assert.assertEquals(timePage.getStaticHoursByDayIndex(1), "08:00", "Tuesday hours mismatch!");
        Assert.assertEquals(timePage.getStaticHoursByDayIndex(2), "06:00", "Wednesday hours mismatch!");
        Assert.assertEquals(timePage.getStaticHoursByDayIndex(3), "09:00", "Thursday hours mismatch!");
        Assert.assertEquals(timePage.getTotalHoursString(), "31:00", "Total accumulated hours column mismatch!");

        timePage.submitTimesheet();
    }
}
