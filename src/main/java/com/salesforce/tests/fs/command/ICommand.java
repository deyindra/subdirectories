package com.salesforce.tests.fs.command;

import java.util.List;


/**
 * Super Interface which will be implemented by all the supported command
 * @see AbstractCommand
 * @see AbstractCommandWithArgument
 * @see DirCommand
 * @see CdCommand
 * @see MkDirCommand
 * @see UpCommand
 */
public interface ICommand {
    /**
     * @return set of outputs String as part of the command output
     */
    List<String> executeCommand();
}
