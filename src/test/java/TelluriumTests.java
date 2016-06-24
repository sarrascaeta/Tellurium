import com.taucetisoftware.tellurium.Tellurium;
import com.taucetisoftware.tellurium.drivers.DriverFactory;
import org.junit.*;
import org.openqa.selenium.WebDriver;

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

		goToGoogle();
	}

	@Test
	public void testCssClick() {
		click(css("a[href*='about.html']"));

		assertAboutGoogle();
	}


	@Test
	public void testXpathClick() {
		click(xpath("//a[contains(@href, 'about.html')]"));

		assertAboutGoogle();
	}

	@Test
	public void testPartialLinkTextClick() {
		click(partialLinkText("Abou"));

		assertAboutGoogle();
	}

	@Test
	public void testLinkTextClick() {
		go("https://www.google.com/search?q=wikipedia");
		click(linkText("Wikipedia"));

		Assert.assertTrue(getUrl().contains("wikipedia"));
	}



	@Test
	public void testGo() {
		Assert.assertTrue(getUrl().contains("google"));
	}


    @Test (expected=RuntimeException.class)
	public void testClick_NoDriver() {
		click(css("Some CSS"));
	}

	@Test
	public void testGetTitle() {
		Assert.assertTrue(getTitle().toLowerCase().contains("google"));
	}

	@Test
	public void testGetSource() {
		Assert.assertTrue(getPageSource().contains("</html>") && getPageSource().contains("</body>"));
	}

	@Test
	public void testGoBack() {
		goBackToGoogle();
	}

	@Test
	public void testRefresh() {
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
		if (driver.toString().toLowerCase().contains("phantom"))
			return;

		executeJavascript("window.location.href = 'http://github.com';");
		sleep(1000);
		Assert.assertTrue(getUrl().contains("github.com"));
	}

	private void goBackToGoogle() {
		go("http://www.microsoft.com");
		goBack();
		Assert.assertTrue(getUrl().contains("google"));
	}

	private void goToGoogle() {
		go("http://www.google.com");
	}

	private void assertAboutGoogle() {
		Assert.assertTrue(getTitle().toLowerCase().contains("about google"));
	}
}
