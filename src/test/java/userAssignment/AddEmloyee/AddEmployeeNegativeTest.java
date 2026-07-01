package userAssignment.AddEmloyee;

import base.BaseTest;
import org.example.pim.AddEmployeePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddEmployeeNegativeTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(AddEmployeeNegativeTest.class);

    @Test(priority = 1)
    public void verifyValidationWhenRequiredFieldsAreEmpty() {
        log.info("Starting negative test: Blank fields submission validation");

        AddEmployeePage addEmployeePage = loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .clickAddEmployeeButton()
                .enterFirstName("")
                .enterLastName("")
                .clickSaveExpectingFailure();

        Assert.assertEquals(addEmployeePage.getFirstNameValidationText(),
                "Required",
                "Empty field validation message mismatch!");

        log.info("Finished negative test: Blank fields submission validation");
    }

    @Test(priority = 2)
    public void verifyValidationWhenPasswordContainsNumbersOnly() {
        log.info("Starting negative test: Weak password validation containing digits only");

        AddEmployeePage addEmployeePage = loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .clickAddEmployeeButton()
                .enterFirstName(json.getNestedValue("newEmployee", "firstName"))
                .enterLastName(json.getNestedValue("newEmployee", "lastName"))
                .toggleCreateLoginDetails()
                .enterUsername("InvalidUser123")
                .enterPassword("12345678")
                .enterConfirmPassword("12345678")
                .clickSaveExpectingFailure();

        Assert.assertEquals(addEmployeePage.getPasswordValidationText(),
                "Your password must contain minimum 1 lower-case letter",
                "Password strength validation check failed!");

        log.info("Finished negative test: Weak password validation containing digits only");
    }

    @Test(priority = 3)
    public void verifyValidationWhenFirstNameIsEmpty() {
        log.info("Starting negative test: Missing first name field validation");

        AddEmployeePage addEmployeePage = loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .clickAddEmployeeButton()
                .enterFirstName("")
                .enterLastName(json.getNestedValue("newEmployee", "lastName"))
                .clickSaveExpectingFailure();

        Assert.assertEquals(addEmployeePage.getFirstNameValidationText(),
                "Required",
                "First name required message validation missing!");

        log.info("Finished negative test: Missing first name field validation");
    }

    @Test(priority = 4)
    public void verifyValidationWhenUsernameIsEmpty() {
        log.info("Starting negative test: Missing username field validation");

        AddEmployeePage addEmployeePage = loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .clickAddEmployeeButton()
                .enterFirstName(json.getNestedValue("newEmployee", "firstName"))
                .enterLastName(json.getNestedValue("newEmployee", "lastName"))
                .toggleCreateLoginDetails()
                .enterUsername("")
                .enterPassword(json.getNestedValue("newEmployee", "password"))
                .enterConfirmPassword(json.getNestedValue("newEmployee", "confirmPassword"))
                .clickSaveExpectingFailure();

        Assert.assertEquals(addEmployeePage.getUsernameValidationText(),
                "Required",
                "Username field required message missing!");

        log.info("Finished negative test: Missing username field validation");
    }

    @Test(priority = 5)
    public void verifyValidationWhenPasswordsDoNotMatch() {
        log.info("Starting negative test: Mismatched password validation");

        AddEmployeePage addEmployeePage = loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .clickAddEmployeeButton()
                .enterFirstName(json.getNestedValue("newEmployee", "firstName"))
                .enterLastName(json.getNestedValue("newEmployee", "lastName"))
                .toggleCreateLoginDetails()
                .enterUsername("InvalidUser123")
                .enterPassword(json.getNestedValue("newEmployee", "password"))
                .enterConfirmPassword("DifferentPassword123!")
                .clickSaveExpectingFailure();

        Assert.assertEquals(addEmployeePage.getConfirmPasswordValidationText(),
                "Passwords do not match",
                "Password matching system verification mismatch!");

        log.info("Finished negative test: Mismatched password validation");
    }

    @Test(priority = 6)
    public void verifyValidationWhenPasswordIsSpecialCharactersOnly() {
        log.info("Starting negative test: Password complexity violation with symbols only");

        AddEmployeePage addEmployeePage = loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .clickAddEmployeeButton()
                .enterFirstName(json.getNestedValue("newEmployee", "firstName"))
                .enterLastName(json.getNestedValue("newEmployee", "lastName"))
                .toggleCreateLoginDetails()
                .enterUsername("InvalidUser123")
                .enterPassword("!@#$%^&*")
                .enterConfirmPassword("!@#$%^&*")
                .clickSaveExpectingFailure();

        Assert.assertEquals(addEmployeePage.getPasswordValidationText(),
                "Your password must contain minimum 1 lower-case letter",
                "Password strength validation check failed for symbols-only field input!");

        log.info("Finished negative test: Password complexity violation with symbols only");
    }

    @Test(priority = 7)
    public void verifyValidationWhenPasswordIsArabicText() {
        log.info("Starting negative test: Arabic text password charset policy rejection mapping");

        AddEmployeePage addEmployeePage = loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .clickAddEmployeeButton()
                .enterFirstName(json.getNestedValue("newEmployee", "firstName"))
                .enterLastName(json.getNestedValue("newEmployee", "lastName"))
                .toggleCreateLoginDetails()
                .enterUsername("InvalidUser123")
                .enterPassword("كلمةالمرور١٢٣")
                .enterConfirmPassword("كلمةالمرور١٢٣")
                .clickSaveExpectingFailure();

        Assert.assertEquals(addEmployeePage.getPasswordValidationText(),
                "Your password must contain minimum 1 lower-case letter",
                "System engine failed to throw an alpha-numeric policy restriction message for Arabic character strings!");

        log.info("Finished negative test: Arabic text password charset policy rejection mapping");
    }
}
