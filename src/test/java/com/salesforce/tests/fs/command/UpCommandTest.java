package com.salesforce.tests.fs.command;

import com.salesforce.tests.fs.OperatingSystem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RunWith(Parameterized.class)
public class UpCommandTest extends AbstractCommandTest {
    private boolean createAndChAndFolder;
    private List<String> expectedOutput;

    public UpCommandTest(OperatingSystem os, boolean createAndChAndFolder, List<String> expectedOutput) {
        super(os);
        this.createAndChAndFolder = createAndChAndFolder;
        this.expectedOutput = expectedOutput;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { new OperatingSystem(), false, Collections.singletonList("Can not go up anymore!!") },
                { new OperatingSystem(),true, Collections.emptyList() }
        });
    }

    @Test
    public void upCommandTest(){
        if(createAndChAndFolder){
           os.getCurrentFolder().addFolder("abc");
           os.setCurrentFolder(os.getCurrentFolder().getChildFolders().get(0));
        }
        Assert.assertEquals(expectedOutput,new UpCommand(os).executeCommand());
    }


}
