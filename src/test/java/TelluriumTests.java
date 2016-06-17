import com.taucetisoftware.tellurium.Tellurium;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Sergio on 6/2/2016.
 */
public class TelluriumTests {

    @Test
    public void testSleep() {
	long startTime = System.currentTimeMillis();

	new Tellurium().sleep(1000);

	long nowTime = System.currentTimeMillis();

	Assert.assertTrue(nowTime > (startTime + 999));
    }
}
