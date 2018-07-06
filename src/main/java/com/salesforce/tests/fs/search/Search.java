package com.salesforce.tests.fs.search;

import com.salesforce.tests.fs.Folder;

import java.util.List;


/**
 * Folder Child Folder Search by Name. in case folders size is less than thresholds value then it will perform linear search else binary search
 * @see LinearSearch
 * @see BinarySearch
 */
public interface Search {
    Folder search(List<Folder> childFolders, String folderName);
}
