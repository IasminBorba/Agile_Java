package exercises.chess;
import exercises.util.StringUtil;
import junit.framework.TestCase;

public class BoardTest extends  TestCase{
    public Board board;


    public void setUp() {
        board = new Board();
    }
    public void testCreateBoard(){
        board.initialize();

//        assertEquals(8, board.pieceCount());
        assertEquals(16, board.getPiecesWhite());
        assertEquals(16, board.getPiecesBlack());

//        String secondRank = board.getRank(2);
//        assertEquals("pppppppp", secondRank);
//
//        String seventhRank = board.getRank(7);
//        assertEquals("PPPPPPPP", seventhRank);

        String blankRank = StringUtil.appendNewLine("........");
        assertEquals(
                StringUtil.appendNewLine("RNBQKBNR") +
                        StringUtil.appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        StringUtil.appendNewLine("pppppppp") +
                        StringUtil.appendNewLine("rnbqkbnr"),
                board.printBoard()
        );
    }
}
