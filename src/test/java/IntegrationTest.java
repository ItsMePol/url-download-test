import org.junit.jupiter.api.Test;

public class IntegrationTest {
    private Downloader download;

    public IntegrationTest(){
        download = new Downloader();
    }

    @Test
    public void downloadSingleURLHTTP(){
        String input = "https://s1.q4cdn.com/806093406/files/doc_downloads/test.pdf";
        download.downloadFromSources(input);
    }

    @Test
    public void downloadSingleURLFTP(){
        String input = "ftp://ftp.adobe.com/armdl-test.txt";
        download.downloadFromSources(input);
    }

    @Test
    public void downloadSingleURLSFTP(){
        String input = "sftp://example.com//home/joe/employee.csv";
        download.downloadFromSources(input);
    }

    @Test
    public void downloadMultipleURLs(){
        String input = "http://i.imgur.com/I86rTVl.jpg, ftp://ftp.adobe.com/armdl-test.txt, sftp://example.com//home/joe/employee.csv";
        download.downloadFromSources(input);
    }
}

