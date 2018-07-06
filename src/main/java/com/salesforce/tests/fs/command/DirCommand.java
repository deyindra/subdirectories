package com.salesforce.tests.fs.command;

import com.salesforce.tests.fs.Folder;
import com.salesforce.tests.fs.OSConstant;
import com.salesforce.tests.fs.OperatingSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * This command display the content of the a current Directory
 */
public class DirCommand extends AbstractCommand {
    public DirCommand(OperatingSystem os) {
        super(os);
    }

    /**
     *
     * @return {@link List} of output String
     */
    @Override
    public List<String> executeCommand() {
        List<String> output = new ArrayList<>();
        //1st output String will be "Directory of <<current folder name>>"
        output.add(String.format("Directory of  %s",os.getCurrentFolder().getFolderPath()));
        List<Folder> list = os.getCurrentFolder().getChildFolders();
        if(list.isEmpty()){
            //If this is empty it will add output String "No Sub directories found"
            output.add("No Sub directories found");
        }else{
            //Else it will add all the sub directory name in a sorted order. it the total number of sub directory is more than
            //TOTAL_SUBDIRECTORY_DISPLAY_IN_ROW then it will wrap the content in a new row.
            StringBuilder builder = new StringBuilder();
            String separator = "%-8s";
            int count=1;
            for(int i=0;i<list.size();i++,count++){

                if(count==OSConstant.TOTAL_SUBDIRECTORY_DISPLAY_IN_ROW || i==list.size()-1){
                   separator = "%s";
                   builder.append(String.format(separator,list.get(i).getFolderName()));
                   output.add(builder.toString());
                   count=0;
                   builder = new StringBuilder();
                   separator = "%-8s";
                }else if(count<OSConstant.TOTAL_SUBDIRECTORY_DISPLAY_IN_ROW) {
                    builder.append(String.format(separator, list.get(i).getFolderName()));
                }
            }
        }
        return output;
    }
}
