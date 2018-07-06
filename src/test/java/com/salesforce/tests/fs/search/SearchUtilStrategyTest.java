package com.salesforce.tests.fs.search;

import com.salesforce.tests.fs.OperatingSystem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class SearchUtilStrategyTest {
    private List<String> childFolders;
    private String childFolderName;
    private boolean expectedResult;
    private int searchThreasold;

    public SearchUtilStrategyTest(List<String> childFolders, String childFolderName, int searchThreasold, boolean expectedResult) {
        this.childFolders = childFolders;
        this.childFolderName = childFolderName;
        this.expectedResult = expectedResult;
        this.searchThreasold = searchThreasold;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { Arrays.asList("abc","cde","xyz"), "ABC", 4, true },
                { Arrays.asList("abc","cde","xyz"), "cde", 4, true },
                { Arrays.asList("abc","cde","xyz"), "mnc", 4, false },
                { Arrays.asList("abc1","abc2","abc3","xuz"), "ABC1", 3, true },
                { Arrays.asList("abc1","abc2","abc3","xuz"), "abc1", 3, true },
                { Arrays.asList("abc1","abc2","abc3","abc6","abc7"), "ABC1", 3, true },
                { Arrays.asList("abc1","abc2","abc3","abc6","abc7"), "abc6", 3, true },
                { Arrays.asList("abc1","abc2","abc3","abc6","abc7"), "zuz", 3, false }
        });
    }

    @Test
    public void searchTest(){
        OperatingSystem os = new OperatingSystem();
        for(String str:childFolders){
            os.getCurrentFolder().addFolder(str);
        }
        SearchUtil searchUtil = new SearchUtil(searchThreasold);
        if(expectedResult){
            Assert.assertNotNull(searchUtil.search(os.getCurrentFolder(),childFolderName));
        }else{
            Assert.assertNull(searchUtil.search(os.getCurrentFolder(),childFolderName));
        }
    }
}
