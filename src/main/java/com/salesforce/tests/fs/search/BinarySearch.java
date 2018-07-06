package com.salesforce.tests.fs.search;

import com.salesforce.tests.fs.Folder;

import java.util.List;

/**
 * @author indranil dey
 * Perform Binary Search which will take O(logn) time by folderName. In case folder is found it will return {@link Folder} or null
 * @see Search
 */
public class BinarySearch implements Search {
    @Override
    public Folder search(List<Folder> childFolders, String folderName) {
        int low=0;
        int high=childFolders.size()-1;
        while (low<=high){
            int middle = low + (high-low)/2;
            Folder middleFolder = childFolders.get(middle);
            String middleFolderName = middleFolder.getFolderName();
            if(folderName.equals(middleFolderName)){
                return middleFolder;
            }else if(folderName.compareTo(middleFolderName)>0){
                low = middle +1;
            }else{
                high = middle -1;
            }
        }
        return null;
    }
}
