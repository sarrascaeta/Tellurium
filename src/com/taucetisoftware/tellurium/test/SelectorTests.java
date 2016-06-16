package com.taucetisoftware.tellurium.test;

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
        Assert.assertTrue(element instanceof  By);
    }

    @Test
    public void testXpath() {
        By element = Selector.xpath("Test");
        Assert.assertTrue(element instanceof  By);
    }

    @Test
    public void testId() {
        By element = Selector.id("Test");
        Assert.assertTrue(element instanceof  By);
    }

    @Test
    public void testName() {
        By element = Selector.name("Test");
        Assert.assertTrue(element instanceof  By);
    }

}
