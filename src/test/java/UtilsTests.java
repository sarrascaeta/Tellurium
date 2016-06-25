import com.taucetisoftware.tellurium.utility.Util;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * Created by Sergio on 6/17/2016.
 */
public class UtilsTests {

	@Test
	public void generateRandomUsername() {
		String randomUsername = Util.generateRandomUsername();
		Assert.assertTrue(randomUsername.length() == 10);
	}

	@Test
	public void generateRandomUsername_Length() {
		String randomUsername = Util.generateRandomUsername(5);
		Assert.assertTrue(randomUsername.length() == 5);
	}

	@Test
	public void generateRandomPassword() {
		String randomPassword = Util.generateRandomPassword();

		Assert.assertTrue(randomPassword.endsWith(Util.passwordSuffix));
		Assert.assertTrue(containsAlphaNumeric(randomPassword));
	}


	@Test
	public void generateRandomPassword_Length() {
		String randomPassword = Util.generateRandomPassword(11);

		Assert.assertTrue((randomPassword.length() - Util.passwordSuffix.length()) == 11);
		Assert.assertTrue(randomPassword.endsWith(".!"));
		Assert.assertTrue(containsAlphaNumeric(randomPassword));
	}

	private boolean containsAlphaNumeric(String s) {
		return StringUtils.isAlphanumeric(s.replace(Util.passwordSuffix, ""));
	}

	@Test
	public void testGetDesktopPath() {
		String s = Util.getDesktopPath();
		Assert.assertTrue(s.contains(":") && s.contains("/Desktop"));
	}


}
