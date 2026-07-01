package org.example.time;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.BasePage;
import org.example.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static java.lang.invoke.MethodHandles.lookup;

public class TimePage extends BasePage {
    private static final Logger log = LogManager.getLogger(lookup().lookupClass());

    private final By editButton = By.xpath("//button[contains(.,'Edit')]");
    private final By submitButton = By.xpath("//button[contains(.,'Submit')]");
    private final By saveButton = By.xpath("//button[@type='submit'][contains(.,'Save')]");
    private final By projectInput = By.xpath("//input[contains(@placeholder,'Type for hints')]");
    private final By activityDropdown = By.xpath("//div[contains(@class,'oxd-select-text')]");
    private final By durationInputs = By.xpath("//td[contains(@class,'--duration-input')]//input");
    private final By employeeNameInput = By.xpath("//div[contains(@class,'oxd-autocomplete-text-input')]//input");
    private final By viewButton = By.xpath("//button[@type='submit'][contains(.,'View')]");
    private final By approveButton = By.xpath("//button[contains(.,'Approve')]");
    private final By userDropdown = By.className("oxd-userdropdown-name");
    private final By logoutLink = By.xpath("//a[text()='Logout']");
    private final By staticHoursCells = By.xpath("//div[@class='orangehrm-timesheet-table-body']//div[contains(@class,'orangehrm-timesheet-table-body-cell')][not(contains(.,':')) and not(input) and not(button)] | //tr[@class='orangehrm-timesheet-table-body-row'][1]//td[contains(@class,'--center') and not(input)]");
    private final By totalHoursCell = By.xpath("//div[contains(@class,'orangehrm-timesheet-table-body-cell')][last()] | //tr[contains(@class,'total')]//td[last()]");
    private final By liveDropdownOption = By.xpath("//div[contains(@class,'oxd-autocomplete-dropdown')]//div[contains(@class,'oxd-autocomplete-option')]");
    private final By invalidErrorLabel = By.xpath("//span[contains(@class,'oxd-input-field-error-message') or text()='Invalid']");
    private final By defaultAutocompleteOption = By.xpath("//div[contains(@class,'oxd-autocomplete-option')]");
    private final By successToast = By.className("oxd-toast");

    private By getProjectOptionLocator(String project) {
        return By.xpath("//div[contains(@class,'oxd-autocomplete-dropdown')]//*[contains(text(),'" + project + "')]");
    }

    private By getActivityOptionLocator(String activity) {
        return By.xpath("//div[@role='listbox']//*[contains(text(),'" + activity + "')]");
    }

    public TimePage(WebDriver driver) {
        super(driver);
    }

    public TimePage typeEmployeeName(String employeeName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(employeeNameInput));
        input.click();
        input.sendKeys(Keys.CONTROL, "a");
        input.sendKeys(Keys.DELETE);
        input.sendKeys(employeeName);
        return this;
    }

    public TimePage selectEmployeeFromAutocomplete(String employeeName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement employeeOption = wait.until(driverInstance -> {
            java.util.List<WebElement> options = driverInstance.findElements(defaultAutocompleteOption);
            if (options.isEmpty()) {
                return null;
            }
            String text = options.get(0).getText().trim();
            System.out.println("Option text = " + text);
            return text.equalsIgnoreCase(employeeName) ? options.get(0) : null;
        });
        employeeOption.click();
        return this;
    }

    public TimePage clickViewButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(viewButton)).click();
        return this;
    }

    public TimePage filterByEmployee(String employeeName) {
        typeEmployeeName(employeeName);
        selectEmployeeFromAutocomplete(employeeName);
        clickViewButton();
        return this;
    }

    public TimePage clickEditTimesheet() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
        return this;
    }

    public TimePage enterProjectName(String project) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement proj = wait.until(ExpectedConditions.elementToBeClickable(projectInput));
        proj.sendKeys(project);
        wait.until(ExpectedConditions.elementToBeClickable(getProjectOptionLocator(project))).click();
        return this;
    }

    public TimePage selectActivity(String activity) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(activityDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(getActivityOptionLocator(activity))).click();
        return this;
    }

    public TimePage enterDurationHours(String hoursMon, String hoursTue, String hoursWed, String hoursThu) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(durationInputs));
        java.util.List<WebElement> cells = driver.findElements(durationInputs);
        String[] hoursArray = {hoursMon, hoursTue, hoursWed, hoursThu};

        for (int i = 0; i < hoursArray.length; i++) {
            WebElement cell = cells.get(i);
            wait.until(ExpectedConditions.elementToBeClickable(cell));
            cell.click();
            cell.sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
            cell.sendKeys(hoursArray[i]);
        }
        return this;
    }

    public TimePage enterTimesheetRowData(String project, String activity, String hoursMon, String hoursTue, String hoursWed, String hoursThu) {
        enterProjectName(project);
        selectActivity(activity);
        enterDurationHours(hoursMon, hoursTue, hoursWed, hoursThu);
        return this;
    }

    public TimePage clickSaveTimesheetButton() {
        findElement(saveButton).click();
        return this;
    }

    public TimePage verifySaveSuccessToast() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
        return this;
    }

    public TimePage clickSaveTimesheet() {
        clickSaveTimesheetButton();
        verifySaveSuccessToast();
        return this;
    }

    public String getStaticHoursByDayIndex(int dayIndex) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(staticHoursCells));
        return driver.findElements(staticHoursCells).get(dayIndex).getText().trim();
    }

    public String getTotalHoursString() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.presenceOfElementLocated(totalHoursCell)).getText().trim();
    }

    public TimePage clickSubmitTimesheetButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        return this;
    }

    public TimePage verifySubmitSuccessToast() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
        return this;
    }

    public TimePage submitTimesheet() {
        clickSubmitTimesheetButton();
        verifySubmitSuccessToast();
        return this;
    }

    public TimePage clickApproveTimesheetButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(approveButton)).click();
        return this;
    }

    public TimePage verifyApproveSuccessToast() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
        return this;
    }

    public TimePage approveTimesheet() {
        clickApproveTimesheetButton();
        verifyApproveSuccessToast();
        return this;
    }

    public LoginPage logout() {
        findElement(userDropdown).click();
        findElement(logoutLink).click();
        return new LoginPage(driver);
    }
}