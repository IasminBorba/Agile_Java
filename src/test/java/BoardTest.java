import junit.framework.TestCase;

public class BoardTest extends  TestCase{
    private Board session;

    public void setUp() {
        session = new Board(0,0);
    }
    public void testCreateBoard(){
        assertEquals(0, session.getPawnsWhite());
        assertEquals(0, session.getPawnsBlack());
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
