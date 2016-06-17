import com.taucetisoftware.tellurium.Selector;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * Created by Sergio on 6/2/2016.
 */
public class SelectorTests {

    @Test
    public void testCss() {
        By element = Selector.css("Test");

        assertBy(element, "By.cssSelector");
    }

    @Test
    public void testXpath() {
        By element = Selector.xpath("Test");

        assertBy(element, "By.xpath");
    }

    @Test
    public void testId() {
        By element = Selector.id("Test");

        assertBy(element, "By.id");
    }

    @Test
    public void testName() {
        By element = Selector.name("Test");

        assertBy(element, "By.name");
    }

    private void assertBy(By element, String selectorType) {
        Assert.assertTrue(element instanceof  By);
        Assert.assertTrue(element.toString().contains(selectorType));
    }
}
