import java.io.*;
import java.net.*;
import org.apache.commons.io.*;
import javax.swing.*;

public class DownloadSources {
    public void downloadSource(String urls){
        String[] urlExtracted = extractURLs(urls);
        String path;
        for(int i = 0; i<urlExtracted.length; i++){
            System.out.println("Downloading from : "+urlExtracted[i]);
            path = getDirectoryPath(urlToFileName(urlExtracted[i]));
            //Download file
            downloadFromURL(urlExtracted[i], path+"/"+urlToFileName(urlExtracted[i]));
            System.out.println("=========Downloaded=======");
        }
    }

    public String[] extractURLs(String urls) {
        //Generalize input
        urls = urls.replaceAll(", ", " ");
        urls = urls.replaceAll(",", " ");
        //Count how many sources
        int lastIndex = 0;
        int countSource = 1;
        while(lastIndex != -1){
            lastIndex = urls.indexOf(" ",lastIndex);
            if(lastIndex != -1){
                countSource++;
                lastIndex++;
            }
        }
        System.out.println(countSource);
        //Extract sources string into separated urls.
        String singleURL = "";
        int pointer = 0;
        int countAdded = 0;
        String[] result = new String[countSource];
        do{
            if (urls.indexOf(" ", pointer) != -1) {
                singleURL = urls.substring(pointer, urls.indexOf(" ", pointer));
            } else {
                singleURL=urls.substring(pointer);
            }
            pointer = urls.indexOf(" ", pointer) + 1;
            result[countAdded] = singleURL;
            countAdded++;
        }while(pointer!=0);
        return result;
    }

    public String urlToFileName(String url){
        int lastSlash = url.lastIndexOf("/");
        //Check if there are dot file type (eg. .txt, .html) available
        if(lastSlash!=-1 && url.indexOf(".", lastSlash) != -1){
            int fileTypeDotPosition = url.indexOf(".", lastSlash);
            String temp = url.substring(0,fileTypeDotPosition);
            temp = temp.replace('.', '-');
            url = url.replaceAll(url.substring(0,fileTypeDotPosition), temp);
            url = url.replaceAll("://", "-");
            url = url.replaceAll("/", "-");
        }else{
            url = url.replace('.', '-');
            url = url.replaceAll("://", "-");
            url = url.replaceAll("/", "-");
            url = url+".html";
        }

        return url;
    }

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

    public boolean downloadFromURL(String url, String path){
        try{
            FileUtils.copyURLToFile(new URL(url), new File(path));
            return true;
        }catch(Exception e){
            File uncompletedFile = new File(path);
            if(uncompletedFile.exists()){
                uncompletedFile.delete();
            }
            System.out.println("ERROR : "+e);
            return false;
        }
    }
}
