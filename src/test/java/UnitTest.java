import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {
    private DownloadSources download;

    public UnitTest(){
        download = new DownloadSources();
    }

    @Test
    public void extractSingleURL(){
        String[] answer = {"http://my.file.com/file"};
        assertEquals(answer[0], download.extractURLs("http://my.file.com/file")[0]);
    }

    @Test
    public void extractMultipleURLsNoComma(){
        String[] answer = {"http://my.file.com/file", "ftp://other.file.com/other", "sftp://and.also.this/ending"};
        String[] tester = download.extractURLs("http://my.file.com/file ftp://other.file.com/other sftp://and.also.this/ending");
        for(int i=0; i<answer.length; i++){
            assertEquals(answer[i], tester[i]);
        }
    }

    @Test
    public void extractMultipleURLsComma(){
        String[] answer = {"http://my.file.com/file", "ftp://other.file.com/other", "sftp://and.also.this/ending"};
        String[] tester = download.extractURLs("http://my.file.com/file,ftp://other.file.com/other,sftp://and.also.this/ending");
        for(int i=0; i<answer.length; i++){
            assertEquals(answer[i], tester[i]);
        }
    }

    @Test
    public void extractMultipleURLsCommaSpace(){
        String[] answer = {"http://my.file.com/file", "ftp://other.file.com/other", "sftp://and.also.this/ending"};
        String[] tester = download.extractURLs("http://my.file.com/file, ftp://other.file.com/other, sftp://and.also.this/ending");
        for(int i=0; i<answer.length; i++){
            assertEquals(answer[i], tester[i]);
        }
    }

    @Test
    public void getFileNameFromURL(){
        assertEquals("http-my-file-com-file.html", download.urlToFileName("http://my.file.com/file"));
    }

    @Test
    public void getFileNameFromURLwithFileType(){
        assertEquals("http-my-file-com-file.txt", download.urlToFileName("http://my.file.com/file.txt"));
    }

    @Test
    public void downloadFile(){
        String testURL = "https://s1.q4cdn.com/806093406/files/doc_downloads/test.pdf";
        String path = download.getDirectoryPath(download.urlToFileName(testURL));
        String filePath = path+"/"+download.urlToFileName(testURL);
        File test = new File(filePath);
        download.downloadFromURL(testURL, filePath);
        assertEquals(true, test.exists());
    }
}
