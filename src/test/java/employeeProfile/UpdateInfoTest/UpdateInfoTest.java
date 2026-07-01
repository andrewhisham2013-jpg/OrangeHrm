package employeeProfile.UpdateInfoTest;

import base.BaseTest;
import employeeProfile.Verfication.VerficationTest;
import reuse.CSVFileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import java.util.Map;

public class UpdateInfoTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(VerficationTest.class);

    @Test
    public void verifyEmployeePersonalInformationUpdateFlow() {
        log.info("Starting Test Scenario: Employee Personal Information Update Flow");

        CSVFileManager csv = new CSVFileManager("src/main/resources/user_stories.csv");
        Map<String, String> testData = csv.getRowData(0);

        log.info("Attempting application login with employee username: {}", testData.get("username"));

        loginPage
                .enterUsername(testData.get("username"))
                .enterPassword(testData.get("password"))
                .clickLoginButton()
                .clickMyInfo()
                .updatePersonalDetails(testData.get("nationality"), testData.get("maritalStatus"))
                .navigateToContactDetails()
                .updateContactDetails(testData.get("street"))
                .navigateToEmergencyContacts()
                .addEmergencyContact(testData.get("emergencyName"), testData.get("emergencyRelationship"), testData.get("emergencyPhone"));

        log.info("Test Scenario Finished: All personal data entries successfully updated and saved!");
    }
}
