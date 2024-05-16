import chess.BoardTest;
import pieces.PawnTest;

public class AllTestsChess {
    public static junit.framework.TestSuite suite() {
        junit.framework.TestSuite suite = new junit.framework.TestSuite();
        suite.addTestSuite(BoardTest.class);
        suite.addTestSuite(PawnTest.class);
        return suite;
    }
}
