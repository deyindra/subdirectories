package com.salesforce.tests.fs.command;

import com.salesforce.tests.fs.OSConstant;
import com.salesforce.tests.fs.OperatingSystem;

import java.util.Collections;
import java.util.List;

/**
 * this command will create a new folder. If the folder already exists or the total folder size is more than {@link OSConstant#TOTAL_SUBDIRECTORY}
 * then it will return error in the output else return empty output.
 */
public class MkDirCommand extends AbstractCommandWithArgument {
    public MkDirCommand(OperatingSystem os, String argument) {
        super(os, argument);
    }

    @Override
    public List<String> executeCommand() {
        if(os.getCurrentFolder().getChildFolders().size()>=OSConstant.TOTAL_SUBDIRECTORY){
            return Collections.singletonList(String.format("Total number of sub directory can not be more than %d",OSConstant.TOTAL_SUBDIRECTORY));
        }else{
            boolean isCreated = os.getCurrentFolder().addFolder(argument);
            if(isCreated)
                return Collections.emptyList();
            else
                return Collections.singletonList(String.format("Folder %s already exists",argument));
        }
    }
}
