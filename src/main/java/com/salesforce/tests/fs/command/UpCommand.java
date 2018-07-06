package com.salesforce.tests.fs.command;

import com.salesforce.tests.fs.Folder;
import com.salesforce.tests.fs.OperatingSystem;

import java.util.Collections;
import java.util.List;

/**
 * This command move the current directory pointer to one level up. In case it is already in root folder it can be be executed.
 */
public class UpCommand extends AbstractCommand {
    public UpCommand(OperatingSystem os) {
        super(os);
    }

    /**
     *
     * @return {@link List} of outputs. In case the command will be not executed it will return {@link Collections#singletonList(Object)}
     * with String 'Can not go up anymore!!'. This will only happen in case the pointer is in the root folder
     */
    @Override
    public List<String> executeCommand() {
        Folder parentFolder = os.getCurrentFolder().getParentFolder();
        /*
         *  Only applicable for Root folder
         */
        if(parentFolder==null){
            return Collections.singletonList("Can not go up anymore!!");
        }else{
            os.setCurrentFolder(parentFolder);
            return Collections.emptyList();
        }
    }
}
