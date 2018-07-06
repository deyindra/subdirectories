package com.salesforce.tests.fs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author indranil dey
 * OperatingSystem Class which will maintain the current folder pointer
 */
@SuppressWarnings("ALL")
public class OperatingSystem {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //Set to Root Folder
    private Folder currentFolder;

    public OperatingSystem(){
        logger.info("Initializing OS....!!");
        //Set the current folder pointer to root
        currentFolder = new Folder();
    }

    //Get the current folder reference
    public Folder getCurrentFolder() {
        return currentFolder;
    }

    //Set the current folder reference used by 'cd' and 'up' command
    public void setCurrentFolder(Folder currentFolder) {
        this.currentFolder = currentFolder;
    }
}
