package employeeProfile.Verfication;

import base.BaseTest;
import org.example.pim.PimPage;
import org.example.myinfo.MyInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class VerficationTest extends BaseTest {

    @Test
    public void verifyEmployeePersonalInformationUpdateFlow() {
        PimPage pimPage = loginPage
                .enterUsername(testData.get("username"))
                .enterPassword(testData.get("password"))
                .clickLoginButton()
                .clickMyInfo()
                .updatePersonalDetails(testData.get("nationality"), testData.get("maritalStatus"))
                .navigateToContactDetails()
                .updateContactDetails(testData.get("street"))
                .navigateToEmergencyContacts()
                .addEmergencyContact(testData.get("emergencyName"), testData.get("emergencyRelationship"), testData.get("emergencyPhone"))
                .logout()
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLoginButton()
                .pressOnPimButton();

        MyInfoPage verificationPage = pimPage.searchAndOpenEmployeeDetails("Andrew Hisham");

        Assert.assertEquals(verificationPage.getSelectedNationality(), testData.get("nationality"), "Nationality mismatch!");
        Assert.assertEquals(verificationPage.getSelectedMaritalStatus(), testData.get("maritalStatus"), "Marital Status mismatch!");

        verificationPage.navigateToContactDetails();
        Assert.assertEquals(verificationPage.getStreetAddressValue(), testData.get("street"), "Street Address mismatch!");

        verificationPage.navigateToEmergencyContacts();
        Assert.assertTrue(verificationPage.isEmergencyContactPresent(testData.get("emergencyName"), testData.get("emergencyRelationship"), testData.get("emergencyPhone")),
                "Saved emergency contact record could not be found under table entries!");
    }
}
