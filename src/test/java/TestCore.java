import com.taucetisoftware.tellurium.Tellurium;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

/**
 * Created by Sergio on 6/24/2016.
 */
public abstract class TestCore extends Tellurium {
    protected static WebDriver tempWebDriver;

    protected void goBackToGoogle() {
        goTo("http://www.microsoft.com");
        goBack();
        Assert.assertTrue(getUrl().contains("google"));
    }

    protected void goToGoogle() {
        goTo("http://www.google.com");
    }

    protected void assertAboutGoogle() {
        Assert.assertTrue(getTitle().toLowerCase().contains("about google"));
    }
}
