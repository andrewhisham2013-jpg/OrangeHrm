package userAssignment.Admin;

import base.BaseTest;
import org.testng.annotations.Test;

public class AdminTEST extends BaseTest {

    @Test
    public void verifyEmployeeCreationToAdminEditFlow() {
        loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .clickAddEmployeeButton()
                .enterFirstName(json.getNestedValue("newEmployee", "firstName"))
                .enterMiddleName("osama")
                .enterLastName(json.getNestedValue("newEmployee", "lastName"))
                .toggleCreateLoginDetails()
                .enterUsername(json.getNestedValue("newEmployee", "username"))
                .enterPassword(json.getNestedValue("newEmployee", "password"))
                .enterEmployeeId(json.getNestedValue("newEmployee", "employeeId"))
                .enterConfirmPassword(json.getNestedValue("newEmployee", "confirmPassword"))
                .clickSaveButton()
                .pressOnAdminPage()
                .searchUser(json.getNestedValue("newEmployee", "username"))
                .clickEditUser(json.getNestedValue("newEmployee", "username"));
    }
}
