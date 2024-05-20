package exercises.chess;
import junit.framework.TestCase;

public class BoardTest extends  TestCase{
    public Board session;


    public void setUp() {
        session = new Board(8,8);
        session.initialize();
    }
    public void testCreateBoard(){
        assertEquals(8, session.getPawnsWhite());
        assertEquals(8, session.getPawnsBlack());
    }
}
