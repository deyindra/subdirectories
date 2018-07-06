package com.salesforce.tests.fs.command;

import com.salesforce.tests.fs.OperatingSystem;

public abstract class AbstractCommandWithArgumentTest extends AbstractCommandTest {
    protected String argument;
    protected boolean isError;
    public AbstractCommandWithArgumentTest(OperatingSystem os, String argument, boolean isError) {
        super(os);
        this.argument = argument;
        this.isError = isError;
    }
}
