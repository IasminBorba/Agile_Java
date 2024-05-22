package exercises;

import exercises.chess.BoardTest;
import exercises.pieces.PieceTest;

public class AllTestsChess {
    public static junit.framework.TestSuite suite() {
        junit.framework.TestSuite suite = new junit.framework.TestSuite();
        suite.addTestSuite(BoardTest.class);
        suite.addTestSuite(PieceTest.class);
        suite.addTestSuite(CharacterTest.class);
        return suite;
    }
}
