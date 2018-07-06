package com.salesforce.tests.fs.command;

import com.salesforce.tests.fs.Folder;
import com.salesforce.tests.fs.OperatingSystem;

/**
 * @author indranil dey
 * Command Enum which will be used to detect the valid command, current 'dir', 'cd', 'up', 'mkdir' will be supported
 */
public enum CommandEnum {

    /*
     * dir command display all the subdirectories of the current directory. Please refer {@link OperatingSystem#getCurrentFolder()} for the
     * current directory
     */
    DIR(1)

    /*
     * up command move to the parent directory. Please refer {@link Folder#getParentFolder()} for parent directory
     */
    ,UP(1)

    /*
     * mkdir command Create Child folder, it will take one argument
     */
    ,MKDIR(2),

    /*
     * cd command change to the child directory. it will take one argument
     */
    CD(2);

    //Define total Command Length for example 'cd abc'; the length will be 2, for example 'up' the length will be 1
    private int commandLength;

    CommandEnum(int commandLength) {
        this.commandLength = commandLength;
    }

    public int getCommandLength() {
        return commandLength;
    }

    @Override
    public String toString() {
        return String.format("%-9s","Command:")+this.name().toLowerCase();
    }

    public static CommandEnum getCommand(String commandName){
        if(commandName!=null && !("").equals(commandName.trim())){
            commandName = commandName.trim();
            for(CommandEnum v:CommandEnum.values()){
                if(v.name().equalsIgnoreCase(commandName)){
                    return v;
                }
            }
        }
        return null;
    }
}
