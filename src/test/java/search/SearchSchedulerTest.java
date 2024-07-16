package search;

import junit.framework.*;
import util.*;

public class SearchSchedulerTest extends TestCase {
    private int actualResultsCount = 0;

    protected void setUp() throws Exception{
        TestUtil.delete(SearchTest.FILE);
        LineWriter.write(SearchTest.FILE, SearchTest.TEST_HTML);
    }

    protected void tearDown() throws Exception{
        TestUtil.delete(SearchTest.FILE);
    }

    public void testRepeatSearch() throws Exception{
        final int searchInterval = 3000;
        Search search = new Search(SearchTest.URL, "xxx");

        ResultsListener listener = new ResultsListener() {
            public void executed(Search search) {
                ++actualResultsCount;
            }
        };

        SearchScheduler scheduler = new SearchScheduler(listener);
        scheduler.repeat(search, searchInterval);

        final int expectedResultsCount = 3;
        Thread.sleep((expectedResultsCount - 1) * searchInterval + 1000);

        scheduler.stop();
        assertEquals(expectedResultsCount, actualResultsCount);
    }
}
