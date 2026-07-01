package employeeDependents.Validation;

import base.BaseTest;
import org.example.myinfo.MyInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidationTest extends BaseTest {

    @Test
    public void FullVerfication() {
        MyInfoPage verificationPage = loginPage
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
                .editExistingEmergencyContact("0123456789")
                .navigateToDependents()
                .addDependent(
                        json.getNestedValue("userStory3", "dependentName"),
                        json.getNestedValue("userStory3", "dependentRelationship"),
                        json.getNestedValue("userStory3", "dependentDob")
                )
                .logout()
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .searchAndOpenEmployeeDetails("Andrew Hisham")
                .navigateToEmergencyContacts();

        Assert.assertTrue(verificationPage.isEmergencyContactPresent(
                        json.getNestedValue("userStory3", "emergencyName"),
                        json.getNestedValue("userStory3", "emergencyRelationship"),
                        "0123456789"),
                "Saved emergency contact record could not be found under table entries!");

        Assert.assertTrue(verificationPage.navigateToDependents().isDependentPresent(
                        json.getNestedValue("userStory3", "dependentName"),
                        json.getNestedValue("userStory3", "dependentRelationship")),
                "Saved dependent record could not be found under table entries!");
    }
}