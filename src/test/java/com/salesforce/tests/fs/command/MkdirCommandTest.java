package com.salesforce.tests.fs.command;

import com.salesforce.tests.fs.OSConstant;
import com.salesforce.tests.fs.OperatingSystem;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class MkdirCommandTest {
    @Test(expected = IllegalArgumentException.class)
    public void testWithNullArgument(){
        new MkDirCommand(new OperatingSystem(),null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithEmptyArgument(){
        new MkDirCommand(new OperatingSystem(),"");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithMoreThanOneSpaceArgument(){
        new MkDirCommand(new OperatingSystem()," ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithLargeLengthArgument(){
        new MkDirCommand(new OperatingSystem(),"aaaaaaa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithInvalidArgument(){
        new MkDirCommand(new OperatingSystem(),"$a`^");
    }

    @Test
    public void testWithAlreadyExistingArgument(){
        OperatingSystem os = new OperatingSystem();
        os.getCurrentFolder().addFolder("abc");
        Assert.assertEquals(new MkDirCommand(os,"abc").executeCommand(),Collections.singletonList(String.format("Folder %s already exists","abc")));
        Assert.assertEquals(new MkDirCommand(os,"ABC").executeCommand(),Collections.singletonList(String.format("Folder %s already exists","abc")));
    }

    @Test
    public void testForMaxFolderLimit(){
        OperatingSystem os = new OperatingSystem();
        for(int i=0;i<OSConstant.TOTAL_SUBDIRECTORY;i++){
            os.getCurrentFolder().addFolder("test"+i);
        }
        Assert.assertEquals(new MkDirCommand(os,"abc").executeCommand(),
                Collections.singletonList(String.format("Total number of sub directory can not be more than %d",OSConstant.TOTAL_SUBDIRECTORY))
                );
    }

    @Test
    public void testSuccessFolderCreation(){
        OperatingSystem os = new OperatingSystem();
        Assert.assertEquals(new MkDirCommand(os,"abc").executeCommand(),
                Collections.emptyList()
        );
    }

}
