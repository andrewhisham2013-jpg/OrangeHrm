package timeSheet.TimeSheetSubmission;

import base.BaseTest;
import org.testng.annotations.Test;

public class TimesheetSubmissionTest extends BaseTest {

    @Test
    public void verifyEmployeeFillsTimesheet() {
        loginPage
                .enterUsername(json.getNestedValue("newEmployee", "username"))
                .enterPassword(json.getNestedValue("newEmployee", "password"))
                .clickLoginButton()
                .pressOnTimeButton()
                .clickEditTimesheet()
                .enterTimesheetRowData(
                        json.getNestedValue("userStory4", "projectName"),
                        json.getNestedValue("userStory4", "activityName"),
                        "8", "8", "6", "9"
                )
                .clickSaveTimesheet();
    }
}
