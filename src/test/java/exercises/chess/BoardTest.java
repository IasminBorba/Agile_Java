package exercises.chess;
import exercises.util.StringUtil;
import junit.framework.TestCase;

public class BoardTest extends  TestCase{
    public Board board;


    public void setUp() {
        board = new Board();
    }
    public void testCreateBoard(){
        assertEquals(32, board.pieceCount());
        assertEquals(16, board.getPiecesWhite());
        assertEquals(16, board.getPiecesBlack());

        String blankRank = StringUtil.appendNewLine("........");
        assertEquals(
                StringUtil.appendNewLine("RNBQKBNR") +
                        StringUtil.appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        StringUtil.appendNewLine("pppppppp") +
                        StringUtil.appendNewLine("rnbqkbnr"),
                board.print()
        );

        String secondRank = board.getRank(2);
        assertEquals("PPPPPPPP", secondRank);

        String seventhRank = board.getRank(3);
        assertEquals("pppppppp", seventhRank);

        assertEquals(32, board.pieceCount());
        System.out.println(board.pieceCount());
    }
}
