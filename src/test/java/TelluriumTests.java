import com.taucetisoftware.tellurium.Tellurium;
import com.taucetisoftware.tellurium.drivers.DriverFactory;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.taucetisoftware.tellurium.Selector.css;

/**
 * Created by Sergio on 6/2/2016.
 */
public class TelluriumTests extends Tellurium {
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
	}

	@Test
	public void testClick() {
		goToGoogle();
		click(css("a[href*='about.html']"));

		Assert.assertTrue(getTitle().toLowerCase().contains("about google"));
	}

	@Test
	public void testGo() {
		goToGoogle();
		Assert.assertTrue(getUrl().contains("google"));
	}


    @Test (expected=RuntimeException.class)
	public void testClick_NoDriver() {
		click(css("Some CSS"));
	}

	@Test
	public void testGetTitle() {
		goToGoogle();
		Assert.assertTrue(getTitle().toLowerCase().contains("google"));
	}

	@Test
	public void testGetSource() {
		goToGoogle();
		Assert.assertTrue(getPageSource().contains("</html>") && getPageSource().contains("</body>"));
	}

	@Test
	public void testGoBack() {
		goBackToGoogle();
	}

	@Test
	public void testRefresh() {
		goToGoogle();
		refresh();
		Assert.assertTrue(getUrl().contains("google"));
	}

	@Test
	public void testGoForward() {
		goBackToGoogle();
		goForward();
		Assert.assertTrue(getUrl().contains("microsoft"));
	}

	@Test
	public void executeJs(){
		goToGoogle();
		executeJavascript("window.location.href = 'http://github.com';");
		sleep(2000);
		Assert.assertTrue(getUrl().contains("microsoft"));
	}

	private void goBackToGoogle() {
		goToGoogle();
		go("http://www.microsoft.com");
		goBack();
		Assert.assertTrue(getUrl().contains("google"));
	}

	private void goToGoogle() {
		go("http://www.google.com");
	}
}
