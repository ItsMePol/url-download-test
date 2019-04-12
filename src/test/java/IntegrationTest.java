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
    public void downloadMultipleURLsHTTP(){
        String input = "https://s1.q4cdn.com/806093406/files/doc_downloads/test.pdf http://i.imgur.com/I86rTVl.jpg";
        download.downloadFromSources(input);
    }
}

