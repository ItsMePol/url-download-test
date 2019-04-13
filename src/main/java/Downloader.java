import java.io.*;
import java.net.*;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
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
            if(urlExtracted[i].startsWith("sftp")){
                downloadFromSFTP(urlExtracted[i], path+"/"+extractor.urlToFileName(urlExtracted[i]));
            }else{
                downloadFromURL(urlExtracted[i], path+"/"+extractor.urlToFileName(urlExtracted[i]));
            }
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

    public boolean downloadFromSFTP(String url, String path){
        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        try{
            //Get information from URL
            URI uri = new URI(url);
            String user = uri.getUserInfo();
            String host = uri.getHost();
            int port = uri.getPort();

            JSch jsch = new JSch();
            session = jsch.getSession(user,host,port);
            session.setPassword("test-password");
            session.connect();

            channel = session.openChannel("sftp");
            channel.connect();

            channelSftp = (ChannelSftp) channel;
            channelSftp.get("remote-file",path );
            channelSftp.exit();
            session.disconnect();
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
