package userAssignment.EditUserFinalTest;

import base.BaseTest;
import org.testng.annotations.Test;

public class EditUserTest extends BaseTest {

    @Test
    public void verifyEmployeeCreationToAdminEditFlow() {
        String uniqueUsername = "andrew" + System.currentTimeMillis();

        loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .clickAddEmployeeButton()
                .enterFirstName(json.getNestedValue("newEmployee", "firstName"))
                .enterMiddleName("osama")
                .enterLastName(json.getNestedValue("newEmployee", "lastName"))
                .enterEmployeeId(json.getNestedValue("newEmployee", "employeeId"))
                .toggleCreateLoginDetails()
                .enterUsername(uniqueUsername)
                .enterPassword(json.getNestedValue("newEmployee", "password"))
                .enterConfirmPassword(json.getNestedValue("newEmployee", "confirmPassword"))
                .clickSaveButton()
                .pressOnAdminPage()
                .searchUser(uniqueUsername)
                .clickEditUser(uniqueUsername)
                .selectUserRole("Admin")
                .clickSave()
                .clickLogout()
                .enterUsername(uniqueUsername)
                .enterPassword(json.getNestedValue("newEmployee", "password"))
                .clickLoginButton()
                .pressOnAdminPage()
                .isAdminButtonDisplayed();
    }
}
