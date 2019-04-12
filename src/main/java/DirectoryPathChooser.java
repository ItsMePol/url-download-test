import javax.swing.*;
import java.io.File;

public class DirectoryPathChooser {
    public String getDirectoryPath(String filename){
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(System.getProperty("user.home") + "/Desktop"));
        chooser.setDialogTitle("Select files destination");
        chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setSelectedFile(new File(filename));
        chooser.showSaveDialog(null);
        return chooser.getCurrentDirectory().getAbsolutePath();
    }
}
