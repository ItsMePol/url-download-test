public class URLExtractor {
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
        }else{
            url = url.replace('.', '-');
        }
        url = url.replaceAll("://", "-");
        url = url.replaceAll("/", "-");
        return url;
    }
}
