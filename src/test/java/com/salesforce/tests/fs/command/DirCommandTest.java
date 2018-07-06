package com.salesforce.tests.fs.command;

import com.salesforce.tests.fs.Folder;
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
public class DirCommandTest extends AbstractCommandTest{
    private List<String> directories;
    private int expectedOutputSize;

    public DirCommandTest(OperatingSystem os, List<String> directories, int expectedOutputSize) {
        super(os);
        this.directories = directories;
        this.expectedOutputSize = expectedOutputSize;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { new OperatingSystem(), Collections.emptyList(),2 },
                { new OperatingSystem(), Arrays.asList("rand1","rand2","rand3","rand4","rand5","rand6","rand7",
                        "rand8","rand9","rand10","rand11","rand12","rand13","rand14","rand15",
                        "rand16","rand17","rand18","rand19","rand20","rand21"),
                    4
                }
        });
    }

    @Test
    public void dirTest(){
        Folder f = os.getCurrentFolder();
        for(String v:directories){
            f.addFolder(v);
        }
        Assert.assertEquals(new DirCommand(os).executeCommand().size(), expectedOutputSize);
    }
}
