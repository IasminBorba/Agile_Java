package chess;
import junit.framework.TestCase;
import pieces.Pawn;

public class BoardTest extends  TestCase{
    public Board session;

    public void setUp() {
        session = new Board(0,0);
    }
    public void testCreateBoard(){
        Board board = new Board(1,4);
        assertEquals(1, board.getPawnsWhite());
        assertEquals(4, board.getPawnsBlack());
    }

    public void testAddPawns(){
        Pawn pawnWhiteFirst = new Pawn("white");
        assertEquals("white",pawnWhiteFirst.getColor());

        session.enroll(pawnWhiteFirst);
        assertEquals(1, session.getNumberOfPawns());
        assertEquals(1, session.getPawnsWhite());
        assertEquals(0, session.getPawnsBlack());

        assertEquals(1, session.getNumberOfPawns());
        assertEquals(pawnWhiteFirst, session.get(0));


        Pawn pawnBlackFirst = new Pawn("black");
        assertEquals("black",pawnBlackFirst.getColor());

        session.enroll(pawnBlackFirst);
        assertEquals(1, session.getPawnsWhite());
        assertEquals(1, session.getPawnsBlack());

        assertEquals(2, session.getNumberOfPawns());
        assertEquals(pawnBlackFirst, session.get(1));
    }
}
