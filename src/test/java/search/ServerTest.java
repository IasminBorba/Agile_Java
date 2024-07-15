package search;

import junit.framework.TestCase;
import util.LineWriter;
import util.TestUtil;

public class ServerTest extends TestCase {
    private int numberOfResults = 0;
    private Server server;
    private static final long TIMEOUT = 3000L;
    private static final String[] URLS = {
            SearchTest.URL, SearchTest.URL, SearchTest.URL
    };

    protected void setUp() throws Exception {
        TestUtil.delete(SearchTest.FILE);
        LineWriter.write(SearchTest.FILE, SearchTest.TEST_HTML);

        ResultsListener listener = new ResultsListener() {
            public void executed(Search search){
                numberOfResults++;
            }
        };
        server = new Server(listener);
    }

    protected void tearDown() throws Exception {
        TestUtil.delete(SearchTest.FILE);
    }

    public void testSearch() throws Exception {
        long start = System.currentTimeMillis();
        for (String url: URLS)
            server.add(new Search(url, "xxx"));

        long elapsed = System.currentTimeMillis() - start;
        long averageLatency = elapsed/URLS.length;

        assertTrue(averageLatency < 20);
        assertTrue(waitForResults());
    }

    private boolean waitForResults() {
        long start = System.currentTimeMillis();
        while (numberOfResults < URLS.length){
            try { Thread.sleep(1);}
            catch (InterruptedException e) {}
            if(System.currentTimeMillis() - start > TIMEOUT)
                return false;
        }
        return true;
    }
}
