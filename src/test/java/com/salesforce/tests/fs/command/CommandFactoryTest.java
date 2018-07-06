package com.salesforce.tests.fs.command;

import com.salesforce.tests.fs.OperatingSystem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

@RunWith(Parameterized.class)
public class CommandFactoryTest {
    private List<String> inputs;
    private List<String> expectedOutput;

    public CommandFactoryTest(List<String> inputs, List<String> expectedOutput) {
        this.inputs = inputs;
        this.expectedOutput = expectedOutput;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { Arrays.asList(null,""," ","abc abc abc","abc cde","dir xyz","cd"),
                  Arrays.asList("Invalid Command","Invalid Command","Invalid Command","Invalid Command",
                          "Invalid Command","Invalid Command argument","Invalid Command argument")
                },
                {
                    inputs(),
                    outputs()
                }
        });
    }

    private static List<String> inputs(){
        return Arrays.asList("dir","up", "mkdir sub001", "cd sub001","cd sub002","dir","up","dir","up",
                "mkdir sub02","mkdir sub03","mkdir sub004","mkdir sub005","mkdir sub006",
                "mkdir sub007","mkdir sub008","mkdir sub009",
                "mkdir sub10","mkdir sub11","mkdir sub102","mkdir sub13","mkdir sub14","mkdir sub15","mkdir sub16",
                "mkdir sub17","mkdir sub18","mkdir sub19","mkdir sub20","mkdir sub201","mkdir sub201","dir","cd sub16",
                "mkdir sub116","cd sub116","dir"
                );
    }
    private static List<String> outputs(){


        List<String> subList = Arrays.asList(String.format("%-9s","Command:")+CommandEnum.DIR.name().toLowerCase(),
                "Directory of  root",
                "No Sub directories found",
                String.format("%-9s","Command:")+CommandEnum.UP.name().toLowerCase(),
                "Can not go up anymore!!",
                String.format("%-17s", String.format("%-9s","Command:")+CommandEnum.MKDIR.name().toLowerCase())+"sub001",
                String.format("%-17s", String.format("%-9s","Command:")+CommandEnum.CD.name().toLowerCase())+"sub001",
                String.format("%-17s", String.format("%-9s","Command:")+CommandEnum.CD.name().toLowerCase())+"sub002",
                "Invalid Folder!!... sub002",
                String.format("%-9s","Command:")+CommandEnum.DIR.name().toLowerCase(),
                "Directory of  root\\sub001",
                "No Sub directories found",
                String.format("%-9s","Command:")+CommandEnum.UP.name().toLowerCase(),
                String.format("%-9s","Command:")+CommandEnum.DIR.name().toLowerCase(),
                "Directory of  root",
                "sub001",
                String.format("%-9s","Command:")+CommandEnum.UP.name().toLowerCase(),
                "Can not go up anymore!!"
        );

        List<String> list = new ArrayList<>(subList);

        String[] array = {
                "sub02","sub03","sub004","sub005","sub006",
                "sub007","sub008","sub009",
                "sub10","sub11","sub102","sub13","sub14","sub15","sub16",
                "sub17","sub18","sub19","sub20","sub201","sub201"
        };

        for(String v:array){
            list.add(String.format("%-17s", String.format("%-9s","Command:")+CommandEnum.MKDIR.name().toLowerCase())+v);
        }
        list.add("Folder sub201 already exists");
        list.add(String.format("%-9s","Command:")+CommandEnum.DIR.name().toLowerCase());
        list.add("Directory of  root");
        list.add("sub001  sub004  sub005  sub006  sub007  sub008  sub009  sub02   sub03   sub10");
        list.add("sub102  sub11   sub13   sub14   sub15   sub16   sub17   sub18   sub19   sub20");
        list.add("sub201");
        list.add(String.format("%-17s", String.format("%-9s","Command:")+CommandEnum.CD.name().toLowerCase())+"sub16");
        list.add(String.format("%-17s", String.format("%-9s","Command:")+CommandEnum.MKDIR.name().toLowerCase())+"sub116");
        list.add(String.format("%-17s", String.format("%-9s","Command:")+CommandEnum.CD.name().toLowerCase())+"sub116");
        list.add(String.format("%-9s","Command:")+CommandEnum.DIR.name().toLowerCase());
        list.add("Directory of  root\\sub16\\sub116");
        list.add("No Sub directories found");
        return list;
    }



    @Test
    public void testOutput(){
        List<String> list = new ArrayList<>();
        OperatingSystem os = new OperatingSystem();
        for(String command:inputs){
            list.addAll(CommandFactory.executeCommand(os,command));
        }
        for(int i=0;i<list.size();i++){
            Assert.assertEquals(list.get(i),expectedOutput.get(i));
        }
    }


}
