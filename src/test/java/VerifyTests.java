import com.taucetisoftware.tellurium.Tellurium;
import com.taucetisoftware.tellurium.drivers.DriverFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Sergio on 6/24/2016.
 */
public class VerifyTests extends TestCore {

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
    public void testVerifyElement() {
        verifyElement(css("img#hplogo"));
    }

    @Test
    public void testVerifyElementText() {
        verifyElementText(css("a[href*='about.html']"), "about google");
    }

    @Test
    public void testContainsText() {
        verifyElementContains(css("a[href*='about.html']"), "about");
    }

    @Test
    public void testVerifyUrl() {
        verifyUrlContains("google.com");
    }

    @Test
    public void testVerifyTitle() {
        verifyTitle("Google");
    }

    @Test
    public void testVerifyTitleContains() {
        verifyTitleContains("oogle");
    }

    @Test
    public void testVerifyNotExist() {
        verifyElementNotPresent(css("fake css"));
    }



}
