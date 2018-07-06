package com.salesforce.tests.fs.command;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CommandEnumTest {
    private String input;
    private CommandEnum expectedOutput;

    public CommandEnumTest(String input, CommandEnum expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {null, null},
                { "", null },
                { " ", null },
                { "abc", null },
                { " abc ", null },
                { " dir ", CommandEnum.DIR },
                { "dir", CommandEnum.DIR }
        });
    }

    @Test
    public void commandEnumTest(){
        CommandEnum commandEnum = CommandEnum.getCommand(input);
        if(expectedOutput==null){
            Assert.assertNull(commandEnum);
        }else{
            Assert.assertSame(commandEnum, expectedOutput);
        }

    }

}
