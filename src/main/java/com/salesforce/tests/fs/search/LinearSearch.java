package com.salesforce.tests.fs.search;

import com.salesforce.tests.fs.Folder;

import java.util.List;

/**
 * @author indranil dey
 * Perform Linear Search which will take O(n) time by folderName. In case folder is found it will return {@link Folder} or null
 * @see Search
 */
public class LinearSearch implements Search {
    @Override
    public Folder search(List<Folder> childFolders, String folderName) {
        for(Folder v:childFolders){
            if(v.getFolderName().equals(folderName)){
                return v;
            }
        }
        return null;
    }
}
