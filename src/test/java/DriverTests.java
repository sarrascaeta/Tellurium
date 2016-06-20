import com.taucetisoftware.tellurium.drivers.DriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * Created by Sergio on 6/19/2016.
 */
public class DriverTests {
	WebDriver driver;

	@After
	public void teardownDriver() {
		driver.quit();
	}

	@Test
	public void testChromeDriver() {
		driver = DriverFactory.createChromeDriver();
		assertDriver("chrome");
	}

	@Test
	public void testChromeDriver_CustomOptions() {
		ChromeOptions options = new ChromeOptions();
		driver = DriverFactory.createChromeDriver(options);

		assertDriver("chrome");
	}

	@Test
	public void testFirefoxDriver() {
		driver = DriverFactory.createFirefoxDriver();
		System.out.println(driver.toString());
		assertDriver("Firefox");
	}

	@Test
	public void testFirefoxDriver_CustomProfile() {
		FirefoxProfile profile = new FirefoxProfile();
		driver = DriverFactory.createFirefoxDriver(profile);

		assertDriver("Firefox");
	}

	@Test
	public void testPhantomDriver() {
		driver = DriverFactory.createPhantomDriver();
		System.out.println(driver.toString());
		assertDriver("Phantom");
	}

	@Test
	public void testIEDriver() {
		driver = DriverFactory.createIEDriver();

		Assert.assertTrue(driver.toString().contains("internet explorer"));
	}



	private void assertDriver(String browserName) {
		String driverName = driver.toString().toLowerCase();
		Assert.assertTrue(driverName.contains(browserName.toLowerCase()));
	}
}
