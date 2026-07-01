package employeeDependents.ManageContacts;

import base.BaseTest;
import org.testng.annotations.Test;

public class ManageContactsTest extends BaseTest {

    @Test
    public void verifyEmergencyContactsManagement() {
        loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .searchAndOpenEmployeeDetails("Andrew Hisham")
                .navigateToEmergencyContacts()
                .addEmergencyContact(
                        json.getNestedValue("userStory3", "emergencyName"),
                        json.getNestedValue("userStory3", "emergencyRelationship"),
                        json.getNestedValue("userStory3", "emergencyMobile")
                )
                .editExistingEmergencyContact("0123456789");
    }
}