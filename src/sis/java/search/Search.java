package search;

import java.net.*;
import java.io.*;
import util.*;

public class Search {
    private final URL url;
    private final String searchString;
    private int matches = 0;
    private Exception exception = null;

    public Search(String urlString, String searchString) throws IOException {
        this.url = new URL(urlString);
        this.searchString = searchString;
    }

    public String getText(){
        return searchString;
    }

    public String getUrl(){
        return url.toString();
    }

    public int matches(){
        return matches;
    }

    public boolean errored(){
        return exception != null;
    }

    public Exception getError() {
        return exception;
    }

    public void execute() {
        try {
            searchUrl();
        } catch (IOException e){
            exception = e;
        }
    }

    private void searchUrl() throws IOException {
        URLConnection connection = url.openConnection();
        InputStream input = connection.getInputStream();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while((line = reader.readLine()) != null)
                matches += StringUtil.occurrences(line, searchString);

        } finally {
            if(reader != null)
                reader.close();
        }
    }
}























