package com.salesforce.tests.fs;


import com.salesforce.tests.fs.search.SearchUtil;

/**
 * @author indranil dey
 * Later this can be part of property file
 */
public interface OSConstant {
    //Max number of folders can be created
    int TOTAL_SUBDIRECTORY = 5000;
    //Root Folder Name
    String ROOT_DIRECTORY_NAME = "root";
    //Total Sub folders can be displayed in a row
    int TOTAL_SUBDIRECTORY_DISPLAY_IN_ROW = 10;
    //Sub folder regular expression
    String SUB_DIRECTORY_REGEX = "^([a-z_0-9])+$";
    //Sub Folder Name
    int SUB_DIRECTORY_LENGTH = 6;
    //Folder Path Separator
    String SEPARATOR = "\\";

    //Initialize Operating System
    OperatingSystem OS = new OperatingSystem();
    //Initialize Search Instance
    SearchUtil SEARCH = new SearchUtil(10);
}
