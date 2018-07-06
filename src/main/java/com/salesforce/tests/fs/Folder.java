package com.salesforce.tests.fs;


import java.util.*;

/**
 * Folder structure which will be store the entire folder structure and set of child folders
 *
 */
public class Folder implements Comparable<Folder>{
    //Current Folder Name
    private String folderName;
    //this will be entire folder path starting from root folder e.g. root\abc\xyz
    private String folderPath;
    //Child Folders Name in a sorted order
    private SortedSet<Folder> childFolders = new TreeSet<>();
    //Parent Folder Reference
    private Folder parentFolder;

    /**
     * Only applicable for root folder
     */
    protected Folder() {
        this(OSConstant.ROOT_DIRECTORY_NAME,OSConstant.ROOT_DIRECTORY_NAME, null);
    }


    /**
     * @param folderName folder name
     * @param folderPath folder path e.g. 'root\abc\xyz'
     * @param parentFolder Parent Folder reference
     */
    private Folder(String folderName, String folderPath, Folder parentFolder) {
        this.folderName = folderName;
        this.folderPath = folderPath;
        this.parentFolder = parentFolder;
    }

    /**
     * Return un modifiable list of Child Folders
     * @return {@link Collections#unmodifiableList(List)}
     */
    public List<Folder> getChildFolders() {
        return Collections.unmodifiableList(new ArrayList<>(childFolders));
    }

    /**
     * Create Child folder and return either true or false. Also Set the path for the child folder
     * @param folderName Add child folder
     * @return return true if Child folder addition is successful
     */
    public boolean addFolder(String folderName){
        folderName = folderName.toLowerCase();
        String folderPath = this.folderName + OSConstant.SEPARATOR + folderName;
        return this.childFolders.add(new Folder(folderName,folderPath, this));
    }

    /**
     *
     * @return Return Folder path e.g. 'root\abc\xyz'
     */
    public String getFolderPath() {
        return folderPath;
    }

    /**
     *
     * @return Folder name e.g. 'root' or 'abc'
     */
    public String getFolderName() {
        return folderName;
    }

    /**
     *
     * @return return parent folder for a given child folder {@link Folder}, for Root Folder this will be null
     */
    public Folder getParentFolder() {
        return parentFolder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Folder folder = (Folder) o;
        return Objects.equals(folderName, folder.folderName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(folderName);
    }

    @Override
    public int compareTo(Folder o) {
        if(o == null){
            return 1;
        }else{
            if(this==o){
                return 0;
            }else{
                String thisFolderName = this.folderName;
                String anotherFolderName = o.folderName;
                if(thisFolderName==null){
                    return -1;
                }else{
                    return thisFolderName.compareTo(anotherFolderName);
                }
            }
        }
    }

    @Override
    public String toString() {
        return folderPath;
    }
}
