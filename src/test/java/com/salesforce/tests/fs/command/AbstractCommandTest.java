package com.salesforce.tests.fs.command;

import com.salesforce.tests.fs.OperatingSystem;

public abstract class AbstractCommandTest {
    protected OperatingSystem os;
    protected AbstractCommandTest(OperatingSystem os) {
        this.os = os;
    }



}
