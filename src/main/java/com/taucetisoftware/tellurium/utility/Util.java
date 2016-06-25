package com.taucetisoftware.tellurium.utility;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sergio on 6/2/2016.
 */
public class Util {
	public static String passwordSuffix = ".!";

	/**
	 * Like {@link #generateRandomUsername(int)} but will default to a 10-character username
	 *
	 * @return
	 */
	public static String generateRandomUsername() {
		return generateRandomUsername(10);
	}

	/**
	 * Generates a random username made up of random letters in both lower/upper case
	 *
	 * @param usernameLength how long the random username should be
	 * @return the randomly generated username
	 */
	public static String generateRandomUsername(int usernameLength) {
		return RandomStringUtils.randomAlphabetic(usernameLength);
	}


	/**
	 * Like {@link #generateRandomPassword(int)} but will default to a password 10-characters long
	 * @return
	 */
	public static String generateRandomPassword() {
		return generateRandomPassword(10);
	}

	/**
	 * Generates a random password made up of random letters and numbers. The password will
	 * be prefixed by these chars to meet typical password requirements: .!
	 *
	 * @param passwordLength the length of the password
	 * @return the randomly generated password
	 */
	public static String generateRandomPassword(int passwordLength) {
		return RandomStringUtils.randomAlphanumeric(passwordLength) + ".!";
	}

	/**
	 * Wait for the specified amount of milliseconds
	 *
	 * @param sleepTime how long to sleep
	 */
    public static void sleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
    }

	public static String getDesktopPath() {
		return System.getProperty("user.home") + "/Desktop";
	}

	public static String getTimestamp() {
		return getTimestamp("hhmmss-MMddyyyy");
	}

	public static String getTimestamp(String format) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(date);
	}

}
