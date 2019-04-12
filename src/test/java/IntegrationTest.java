import org.junit.jupiter.api.Test;

public class IntegrationTest {
    private DownloadSources download;

    public IntegrationTest(){
        download = new DownloadSources();
    }

    @Test
    public void downloadSingleURL(){
        String input = "https://s1.q4cdn.com/806093406/files/doc_downloads/test.pdf";
        download.downloadSource(input);
    }

    @Test
    public void downloadMultipleURLs(){
        String input = "https://s1.q4cdn.com/806093406/files/doc_downloads/test.pdf http://i.imgur.com/I86rTVl.jpg";
        download.downloadSource(input);
    }
}
