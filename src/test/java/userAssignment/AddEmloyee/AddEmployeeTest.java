package userAssignment.AddEmloyee;

import base.BaseTest;
import org.testng.annotations.Test;

public class AddEmployeeTest extends BaseTest {

    @Test
    public void verifyValidationWhenRequiredFieldsAreEmpty() {
        loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .clickAddEmployeeButton()
                .enterFirstName(json.getNestedValue("newEmployee", "firstName"))
                .enterLastName(json.getNestedValue("newEmployee", "lastName"))
                .toggleCreateLoginDetails()
                .enterUsername(json.getNestedValue("newEmployee", "username"))
                .enterEmployeeId(json.getNestedValue("newEmployee", "employeeId"))
                .enterPassword(json.getNestedValue("newEmployee", "password"))
                .enterConfirmPassword(json.getNestedValue("newEmployee", "confirmPassword"))
                .clickSaveButton();
    }
}
