package employeeDependents.ManageDependents;

import base.BaseTest;
import org.example.myinfo.MyInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ManageDependentsTest extends BaseTest {

    @Test
    public void verifyEmployeeDependentsManagement() {
        MyInfoPage verificationPage = loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .searchAndOpenEmployeeDetails("Andrew Hisham")
                .navigateToDependents()
                .addDependent(
                        json.getNestedValue("userStory3", "dependentName"),
                        json.getNestedValue("userStory3", "dependentRelationship"),
                        json.getNestedValue("userStory3", "dependentDob")
                );

        Assert.assertTrue(verificationPage.isDependentPresent(json.getNestedValue("userStory3", "dependentName"), json.getNestedValue("userStory3", "dependentRelationship")),
                "Saved dependent record could not be found under table entries!");
    }
}