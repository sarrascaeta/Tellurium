import com.taucetisoftware.tellurium.Tellurium;
import com.taucetisoftware.tellurium.drivers.DriverFactory;
import com.taucetisoftware.tellurium.utility.Screenshot;
import org.junit.*;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Created by Sergio on 6/25/2016.
 */
public class ScreenshotTests extends TestCore {
    private static WebDriver tempWebDriver;

    @BeforeClass
    public static void createDriver() {
        tempWebDriver = DriverFactory.createPhantomDriver();
    }

    @AfterClass
    public static void teardownDriver() {
        tempWebDriver.quit();
    }

    //This is done to get around the static BeforeClass
    @Before
    public void assignCorrectDriver() {
        driver = tempWebDriver;
        setGlobalWaitTime(5);

        goToGoogle();
    }

    @Test
    public void screenshotTest() {
        Screenshot.take(driver);

        assertScreenshotFile();
    }

    private void assertScreenshotFile() {
        File f = new File(Screenshot.getLastScreenshotFilepath());
        System.out.println(f.getAbsoluteFile());
        Assert.assertTrue(f.exists());
    }

    @Test
    public void testScreenshotCustomPath() {
        Screenshot.take(driver, getTempLocation() + "filename");

        assertScreenshotFile();
    }

    @Test
    public void testScreenshotCustomPathAutoName() {
        Screenshot.take(driver, getTempLocation() + Screenshot.getAutomaticName(driver));

        assertScreenshotFile();
        Assert.assertTrue(Screenshot.getLastScreenshotFilepath().contains("google_com"));
    }

    private String getTempLocation() {
        return System.getProperty("java.io.tmpdir");
    }
}
