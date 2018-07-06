package com.salesforce.tests.fs.command;

import com.salesforce.tests.fs.Folder;
import com.salesforce.tests.fs.OSConstant;
import com.salesforce.tests.fs.OperatingSystem;

import java.util.Collections;
import java.util.List;

/**
 * Command for changing to subfolder. After changing the {@link OperatingSystem#currentFolder} path will change
 */
public class CdCommand extends AbstractCommandWithArgument{
    public CdCommand(OperatingSystem os, String argument) {
        super(os, argument);
    }

    /**
     *
     * @return output of the command, if it will be unable to cd it will return error in the output. Else it will return empty output.
     */
    @Override
    public List<String> executeCommand() {
        Folder childFolder = OSConstant.SEARCH.search(os.getCurrentFolder(),argument);
        if(childFolder == null){
            return Collections.singletonList("Invalid Folder!!... "+argument);
        }else{
            os.setCurrentFolder(childFolder);
            return Collections.emptyList();
        }
    }
}
