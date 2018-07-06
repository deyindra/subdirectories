package com.salesforce.tests.fs.search;

import com.salesforce.tests.fs.Folder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author indranil dey
 * Child Folders Search Utility based on name
 * It will perform either linear or binary search based on current child folder size refer {@link SearchUtil#totalChildFolderSize}
 * @see BinarySearch
 * @see LinearSearch
 */
public class SearchUtil {
    // if the child folder size greater than or equal to totalChildFolderSize then perform bin search else linear search
    private int totalChildFolderSize;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @param totalChildFolderSize totalChildFolderSize beyond which it will perform binary search
     * @throws IllegalArgumentException in case totalChildFolderSize is less than or equal to 0
     */
    public SearchUtil(int totalChildFolderSize) {
        if(totalChildFolderSize<=0){
            throw new IllegalArgumentException("Invalid child folder size");
        }
        this.totalChildFolderSize = totalChildFolderSize;
    }

    /**
     * it will return a valid child folder or null in case it is not found, or parent folder is null or empty of
     * child folder name is null or empty
     * @param parentFolder Parent {@link Folder} where one will be searching child folders by name
     * @param childFolderName child folder name
     * @return {@link Folder} child folder
     */
    public Folder search(Folder parentFolder, String childFolderName){
        //return null in case parent folder is null or childFolderName is null or empty
        if(parentFolder==null || childFolderName == null || ("").equals(childFolderName.trim())){
            return null;
        }else{
            List<Folder> folders = parentFolder.getChildFolders();
            childFolderName = childFolderName.trim().toLowerCase();
            //return null if folders is empty
            if(folders.isEmpty()){
                return null;
            }else if(folders.size()>=totalChildFolderSize){
                logger.info("Inside Binary Search");
                return new BinarySearch().search(folders,childFolderName);
            }else{
                logger.info("Inside Linear Search");
                return new LinearSearch().search(folders,childFolderName);
            }
        }
    }
}
