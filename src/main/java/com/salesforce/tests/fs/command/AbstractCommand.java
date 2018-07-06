package com.salesforce.tests.fs.command;

import com.salesforce.tests.fs.OperatingSystem;


/**
 * Abstract Command class which will be extended by the command that does not take any argument
 * @see DirCommand
 * @see UpCommand
 */
public abstract class AbstractCommand implements ICommand{
    //Underlying Instance of the operating System
    protected OperatingSystem os;

    protected AbstractCommand(OperatingSystem os) {
        this.os = os;
    }
}
