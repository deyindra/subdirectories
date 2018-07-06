package com.salesforce.tests.fs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FolderTest {

    @Test
    public void rootFolderTest(){
        OperatingSystem os = new OperatingSystem();
        Assert.assertEquals(os.getCurrentFolder().toString(), "root");
    }

    @Test
    public void createFolder(){
        OperatingSystem os = new OperatingSystem();
        Assert.assertTrue(os.getCurrentFolder().addFolder("abc"));
        Assert.assertFalse(os.getCurrentFolder().addFolder("abc"));
        Assert.assertFalse(os.getCurrentFolder().addFolder("ABC"));
        Assert.assertFalse(os.getCurrentFolder().addFolder("Abc"));
    }


    @Test
    public void testFolderPath(){
        OperatingSystem os = new OperatingSystem();
        os.getCurrentFolder().addFolder("abc");
        Assert.assertEquals(os.getCurrentFolder().getChildFolders().get(0).toString(),"root\\abc");
    }
}
