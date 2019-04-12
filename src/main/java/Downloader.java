import java.io.*;
import java.net.*;
import org.apache.commons.io.*;

public class Downloader {

    public void downloadFromSources(String urls){
        URLExtractor extractor = new URLExtractor();
        DirectoryPathChooser directoryPathChooser = new DirectoryPathChooser();
        String[] urlExtracted = extractor.extractURLs(urls);
        String path = directoryPathChooser.getDirectoryPath(extractor.urlToFileName(urlExtracted[0]));
        for(int i = 0; i<urlExtracted.length; i++){
            System.out.println("Downloading from : "+urlExtracted[i]);
            //Download file
            downloadFromURL(urlExtracted[i], path+"/"+extractor.urlToFileName(urlExtracted[i]));
            System.out.println("=========Downloaded=======");
        }
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
