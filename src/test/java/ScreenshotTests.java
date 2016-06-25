import com.taucetisoftware.tellurium.Tellurium;
import com.taucetisoftware.tellurium.drivers.DriverFactory;
import com.taucetisoftware.tellurium.utility.Screenshot;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

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
    }
}
