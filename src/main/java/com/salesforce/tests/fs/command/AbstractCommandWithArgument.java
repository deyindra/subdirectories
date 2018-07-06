package com.salesforce.tests.fs.command;

import com.salesforce.tests.fs.OSConstant;
import com.salesforce.tests.fs.OperatingSystem;


/**
 * Abstract command class which will be extended by all the commands that take 1 argument
 * @see CdCommand
 * @see MkDirCommand
 */
public abstract class AbstractCommandWithArgument extends AbstractCommand{
    protected String argument;

    /**
     * It will set the underlying OS and Argument as part of constructor
     * @param os underlying {@link OperatingSystem}
     * @param argument command line argument
     * @throws IllegalArgumentException in case it is null or empty or the length is greater than {@link OSConstant#SUB_DIRECTORY_LENGTH} and does not
     * confirm the regular expression {@link OSConstant#SUB_DIRECTORY_REGEX}
     */
    public AbstractCommandWithArgument(OperatingSystem os, String argument) {
        super(os);
        validateArgument(argument);
    }

    /**
     * @param argument Command line argument which will be sub directory name
     * @throws IllegalArgumentException in case it is null or empty or the length is greater than {@link OSConstant#SUB_DIRECTORY_LENGTH} and does not
     * confirm the regular expression {@link OSConstant#SUB_DIRECTORY_REGEX}
     */
    private void validateArgument(String argument){
        if(argument==null || ("").equals(argument.trim())){
            throw new IllegalArgumentException("Invalid Folder Name");
        }else{
            argument = argument.trim().toLowerCase();
            if(argument.length()>OSConstant.SUB_DIRECTORY_LENGTH){
                throw new IllegalArgumentException("Invalid Folder Name");
            }else{
                if(!argument.matches(OSConstant.SUB_DIRECTORY_REGEX)){
                    throw new IllegalArgumentException("Invalid Folder Name");
                }else{
                    this.argument = argument;
                }
            }
        }
    }

}
