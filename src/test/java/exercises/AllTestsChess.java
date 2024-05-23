package exercises;

import exercises.chess.BoardTest;
import exercises.pieces.PieceTest;
import exercises.util.StringUtilTest;

public class AllTestsChess {
    public static junit.framework.TestSuite suite() {
        junit.framework.TestSuite suite = new junit.framework.TestSuite();
        suite.addTestSuite(BoardTest.class);
        suite.addTestSuite(PieceTest.class);
        suite.addTestSuite(CharacterTest.class);
        suite.addTestSuite(StringUtilTest.class);
        return suite;
    }
}
