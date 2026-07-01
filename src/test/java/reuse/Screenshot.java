package reuse;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class Screenshot {
    public static File takeScreenshot(WebDriver driver,String path) throws IOException {
        TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
        File scrShoot=takesScreenshot.getScreenshotAs(OutputType.FILE);
        File dest=new File(path);
        FileHandler.copy(scrShoot,dest);
        return dest;
    }
}
