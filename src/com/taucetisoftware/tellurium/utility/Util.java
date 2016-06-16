package com.taucetisoftware.tellurium.utility;

/**
 * Created by Sergio on 6/2/2016.
 */
public class Util {

    public static void sleep(int sleepTime) {
	try {
	    Thread.sleep(sleepTime);
	} catch (InterruptedException e) {
	    System.err.println(e.getMessage());
	}
    }
}
