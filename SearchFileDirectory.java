import java.io.File;
import java.util.*;
public class SearchFileDirectory {
    /**
    * @param roots directories to start searching from 
    * @param regex regular expression used to find good matches 
    * @return a Collection of all File objects in each directory and 
    * subdirectory of roots whose filename matches regex. 
    */ 
    public static final Collection<File> getFiles(final String[] roots, final String regex) { 
        // storage of good File matches 
        final ArrayList<File> fileList = new ArrayList<File>(); 
        // continue only if roots contains some data 
        if (roots != null && roots.length > 0) { 
            // current working Stack of files yet-to-be-searched 
            final Stack<File> fileStack = new Stack<File>(); 
            // add all root directories to file stack 
            for (String r : roots) { 
                fileStack.push(new File(r)); 
            } 
            // start processing our file stack 
            File[] files; 
            while (!fileStack.isEmpty()) { 
                // add all subdirectories of the current file to files 
                files = fileStack.pop().listFiles(); 
                // if files is null, then one of our roots was not a directory 
                if (files != null) { 
                    // search through files 
                    for (File file : files) { 
                        // add any matches to our good file listing 
                        if (file.getName().matches(regex)) fileList.add(file); 
                        // add any subdirectories to the file stack 
                        if (file.isDirectory()) fileStack.push(file); 
                    } 
                } 
            } 
        } 
        return fileList; 
    }
    
    // Example main method to run the code
    public static void main(String[] args) {
        String[] roots = {"C:\\Users\\YourName\\Documents"};
        String regex = ".*\\.txt"; // Find all .txt files
        Collection<File> files = SearchFileDirectory.getFiles(roots, regex);
        for (File f : files) {
            System.out.println(f.getAbsolutePath());
        }
    }
}