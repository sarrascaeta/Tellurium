import com.taucetisoftware.tellurium.Tellurium;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.taucetisoftware.tellurium.Selector.css;

/**
 * Created by Sergio on 6/2/2016.
 */
public class TelluriumTests extends Tellurium {


	@BeforeClass
	public void CreateDriver() {

	}

	@Test
	public void TestClick() {

	}


    @Test (expected=RuntimeException.class)
	public void testClick_NoDriver() {
		click(css("Some CSS"));
	}


}
