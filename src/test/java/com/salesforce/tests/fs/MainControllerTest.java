package com.salesforce.tests.fs;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainControllerTest {

    @Test
    public void inputTest(){
        String input = MainController.class.getResource("/inputs.txt").getPath();
        List<String> finalOutputs = MainController.outputs(new OperatingSystem(),input);
        String output = MainController.class.getResource("/output.txt").getPath();
        try(Stream<String> stream = Files.lines(Paths.get(output.trim()))){
            Assert.assertEquals(finalOutputs,stream.collect(Collectors.toList()));
        }catch (Exception e){
            throw new IllegalArgumentException(e);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionTestWithNull(){
        MainController.outputs(new OperatingSystem(),null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionTestWithEmpty1(){
        MainController.outputs(new OperatingSystem(),"");
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionTestWithEmpty2(){
        MainController.outputs(new OperatingSystem()," ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionTestWithInvalidPath(){
        MainController.outputs(new OperatingSystem(),"anc");
    }

}
