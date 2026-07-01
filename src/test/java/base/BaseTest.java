package base;

import io.qameta.allure.Allure;
import org.example.pages.LoginPage;
import reuse.JsonFileManager;
import reuse.CSVFileManager;
import reuse.Screenshot;
import reuse.WebDriverFactory;
import reuse.AllureReportListener;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;



@Listeners(AllureReportListener.class)
public class BaseTest {
    protected WebDriver driver;
    protected SoftAssert softAssert;
    protected LoginPage loginPage;
    protected JsonFileManager json;
    protected Map<String, String> testData;

    @BeforeClass
    public void setUp() {
        json = new JsonFileManager("src/main/resources/config.json");
        CSVFileManager csv = new CSVFileManager("src/main/resources/user_stories.csv");
        testData = csv.getRowData(0);

        driver = WebDriverFactory.getDriver(json.getValueByKey("browser"));
        String url = json.getValueByKey("url");
        driver.get(url);
        loginPage = new LoginPage(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void checkFailAndLog(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                File file = Screenshot.takeScreenshot(driver, "screenshots/" + result.getName() + ".png");
                Allure.addAttachment("screenshot", new FileInputStream(file));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        try {
            File logFile = new File("logs/application.log");
            if (logFile.exists()) {
                Allure.addAttachment("log file", new FileInputStream(logFile));
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        if (driver != null) {
            try {
                new org.example.pim.AddEmployeePage(driver).logout();
            } catch (Exception e) {
                driver.manage().deleteAllCookies();
            }
            driver.get(json.getValueByKey("url"));
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}