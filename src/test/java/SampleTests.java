import com.taucetisoftware.tellurium.Tellurium;
import com.taucetisoftware.tellurium.drivers.DriverFactory;
import org.junit.Test;

/**
 * Created by Sergio on 7/4/2016.
 */
public class SampleTests extends Tellurium {

    @Test
    public void sampleTest() {
        setDriver(DriverFactory.createChromeDriver());

        goTo("https://www.wikipedia.org/");

        verifyUrlContains("wikipedia.org");

        select(id("searchLanguage"), "es");

        clickSearchButton();

        verifyElementText(id("firstHeading"), "Buscar");

        goBack();

        sendTo(id("searchInput"), "Selenium");

        select(id("searchLanguage"), "en");

        clickSearchButton();

        verifyElement(id("firstHeading"));
    }

    private void clickSearchButton() {
        click(xpath("//*[@id=\"search-form\"]/fieldset/button"));
    }
}
