package com.salesforce.tests.fs.command;

import com.salesforce.tests.fs.OperatingSystem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class CdCommandTest extends AbstractCommandWithArgumentTest {
    private int expectedOutputLength;
    private boolean isCreateDir;

    public CdCommandTest(OperatingSystem os, String argument, boolean isError, int expectedOutputLength, boolean isCreateDir) {
        super(os, argument, isError);
        this.expectedOutputLength = expectedOutputLength;
        this.isCreateDir = isCreateDir;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { new OperatingSystem(), null, true, -1, false },
                { new OperatingSystem(), "", true, -1, false },
                { new OperatingSystem(), " ", true, -1, false },
                { new OperatingSystem(), "aaaaaaa", true, -1, false },
                { new OperatingSystem(), "^1bac$", true, -1, false },
                { new OperatingSystem(), "abc", false, 1, false },
                { new OperatingSystem(), "abc", false, 0, true },

        });
    }


    @Test
    public void testCDCommand(){
        try{
            AbstractCommandWithArgument command = new CdCommand(os,argument);
            if(isCreateDir){
                os.getCurrentFolder().addFolder(argument);
            }
            Assert.assertEquals(command.executeCommand().size(),expectedOutputLength);
            if(isCreateDir){
                Assert.assertEquals(os.getCurrentFolder().getFolderPath(),"root\\"+argument);
            }
        }catch (Exception e){
            if(isError)
                Assert.assertTrue(e instanceof IllegalArgumentException);
            else
                throw e;
        }
    }
}
