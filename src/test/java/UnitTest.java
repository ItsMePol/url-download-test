import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {
    private Downloader download;
    private URLExtractor extractor;
    private DirectoryPathChooser directory;

    public UnitTest(){
        download = new Downloader();
        extractor = new URLExtractor();
        directory = new DirectoryPathChooser();
    }

    @Test
    public void extractSingleURL(){
        String[] answer = {"http://my.file.com/file"};
        assertEquals(answer[0], extractor.extractURLs("http://my.file.com/file")[0]);
    }

    @Test
    public void extractMultipleURLsNoComma(){
        String[] answer = {"http://my.file.com/file", "ftp://other.file.com/other", "sftp://and.also.this/ending"};
        String[] tester = extractor.extractURLs("http://my.file.com/file ftp://other.file.com/other sftp://and.also.this/ending");
        for(int i=0; i<answer.length; i++){
            assertEquals(answer[i], tester[i]);
        }
    }

    @Test
    public void extractMultipleURLsComma(){
        String[] answer = {"http://my.file.com/file", "ftp://other.file.com/other", "sftp://and.also.this/ending"};
        String[] tester = extractor.extractURLs("http://my.file.com/file,ftp://other.file.com/other,sftp://and.also.this/ending");
        for(int i=0; i<answer.length; i++){
            assertEquals(answer[i], tester[i]);
        }
    }

    @Test
    public void extractMultipleURLsCommaSpace(){
        String[] answer = {"http://my.file.com/file", "ftp://other.file.com/other", "sftp://and.also.this/ending"};
        String[] tester = extractor.extractURLs("http://my.file.com/file, ftp://other.file.com/other, sftp://and.also.this/ending");
        for(int i=0; i<answer.length; i++){
            assertEquals(answer[i], tester[i]);
        }
    }

    @Test
    public void getFileNameFromURL(){
        assertEquals("http-my-file-com-file.html", extractor.urlToFileName("http://my.file.com/file"));
    }

    @Test
    public void getFileNameFromURLwithFileType(){
        assertEquals("http-my-file-com-file.txt", extractor.urlToFileName("http://my.file.com/file.txt"));
    }

    @Test
    public void downloadFile(){
        String testURL = "https://s1.q4cdn.com/806093406/files/doc_downloads/test.pdf";
        String path = directory.getDirectoryPath(extractor.urlToFileName(testURL));
        String filePath = path+"/"+extractor.urlToFileName(testURL);
        File test = new File(filePath);
        download.downloadFromURL(testURL, filePath);
        assertEquals(true, test.exists());
    }
}
