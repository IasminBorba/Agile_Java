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

        String firstRank = board.getRank(1);
        assertEquals("RNBQKBNR", firstRank);

        String secondRank = board.getRank(2);
        assertEquals("PPPPPPPP", secondRank);

        String emptyRank = board.getRank(3);
        assertEquals("", emptyRank);

        String seventhRank = board.getRank(7);
        assertEquals("pppppppp", seventhRank);


        String eighthRank = board.getRank(8);
        assertEquals("rnbqkbnr", eighthRank);

        String blankRank = StringUtil.appendNewLine("........");
        assertEquals(
                StringUtil.appendNewLine("RNBQKBNR") +
                        StringUtil.appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
                        StringUtil.appendNewLine("pppppppp") +
                        StringUtil.appendNewLine("rnbqkbnr"),
                board.print()
        );

        System.out.println(board.pieceCount());
    }
}
