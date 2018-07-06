package com.salesforce.tests.fs.search;

import com.salesforce.tests.fs.OperatingSystem;
import org.junit.Assert;
import org.junit.Test;

public class SearchUtilTest {
    @Test(expected = IllegalArgumentException.class)
    public void exceptionTestWithZeroSize(){
        new SearchUtil(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionTestWithNegativeSize(){
        new SearchUtil(-1);
    }

    @Test
    public void nullValueTest(){
        SearchUtil searchUtil = new SearchUtil(10);
        Assert.assertNull(searchUtil.search(null, "abc"));
        Assert.assertNull(searchUtil.search(new OperatingSystem().getCurrentFolder(), ""));
        Assert.assertNull(searchUtil.search(new OperatingSystem().getCurrentFolder(), " "));
        Assert.assertNull(searchUtil.search(new OperatingSystem().getCurrentFolder(), null));
        Assert.assertNull(searchUtil.search(new OperatingSystem().getCurrentFolder(), "abc"));
    }

}
