package com.salesforce.tests.fs.command;

import com.salesforce.tests.fs.OperatingSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Main entry point for all command line Syntax
 *
 */
public class CommandFactory {
    public static List<String> executeCommand(OperatingSystem os, String command){
        List<String> outputs = new ArrayList<>();
        if(command==null || ("").equals(command.trim())){
            outputs.add("Invalid Command");
        }else{
            command = command.trim();
            String[] array = command.split("\\s+");
            if(array.length>2){
                outputs.add("Invalid Command");
            }else{
                CommandEnum commandEnum = CommandEnum.getCommand(array[0]);
                if(commandEnum == null){
                    outputs.add("Invalid Command");
                }else{
                   if(commandEnum.getCommandLength()!=array.length){
                       outputs.add("Invalid Command argument");
                   }else{
                       if(array.length==2) {
                           outputs.add(String.format("%-17s", commandEnum.toString()) + array[1]);
                       }else {
                           outputs.add(commandEnum.toString());
                       }
                       parseCommand(os,commandEnum,array,outputs);
                   }
                }
            }

        }
        return outputs;
    }


    private static void parseCommand(OperatingSystem os, CommandEnum commandEnum,String[] args, List<String> outputs){
        try{
            switch (commandEnum)
            {
                case CD: outputs.addAll(new CdCommand(os,args[1]).executeCommand());
                         break;
                case MKDIR: outputs.addAll(new MkDirCommand(os,args[1]).executeCommand());
                            break;
                case DIR: outputs.addAll(new DirCommand(os).executeCommand());
                          break;
                case UP: outputs.addAll(new UpCommand(os).executeCommand());
                         break;
                default: break;
            }
        }catch (Exception e){
            outputs.add(e.getMessage());
        }
    }
}
