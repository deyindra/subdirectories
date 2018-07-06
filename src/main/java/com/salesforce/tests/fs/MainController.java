package com.salesforce.tests.fs;


import com.salesforce.tests.fs.command.CommandFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Controller class which will take file containing all command as inputs and return the list of output
 */
public class MainController {
    public static List<String> outputs(OperatingSystem os, String filePath){

            if (filePath == null || ("").equals(filePath.trim())) {
                throw new IllegalArgumentException("Invalid input path");
            } else {
                List<String> outputList = new ArrayList<>();
                try(Stream<String> stream = Files.lines(Paths.get(filePath.trim()))){
                    stream.forEach(x->outputList.addAll(CommandFactory.executeCommand(os,x.trim())));
                    return outputList;
                }catch (Exception e){
                    throw new IllegalArgumentException(e);
                }
            }
    }

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.err.println("Invalid Command!! Usage : command <<input file name>>");
            } else {
                String filePath = args[0];
                for(String v:outputs(OSConstant.OS,filePath.trim())){
                    System.out.println(v);
                }
            }
        }catch (Exception e){
            System.err.print(e.getMessage());
        }
    }
}
