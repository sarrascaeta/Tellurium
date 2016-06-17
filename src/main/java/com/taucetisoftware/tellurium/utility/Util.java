package com.taucetisoftware.tellurium.utility;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by Sergio on 6/2/2016.
 */
public class Util {
	public static String passwordSuffix = ".!";

	public static void sleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

	public static String generateRandomUsername() {
		return generateRandomUsername(10);
	}

	public static String generateRandomUsername(int usernameLength) {
		return RandomStringUtils.randomAlphabetic(usernameLength);
	}

	public static String generateRandomPassword() {
		return generateRandomPassword(10);
	}

<<<<<<< HEAD
	public static String generateRandomPassword(int passwordLength) {
		return RandomStringUtils.randomAlphanumeric(passwordLength) + ".!";
	}
=======
    public static void sleep(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
    }
>>>>>>> d9cd1b5a00e73d4ea1d8d3ac0ba78d080a0fac1d
}
