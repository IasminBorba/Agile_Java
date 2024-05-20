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

        assertEquals(".", session.get(0).representation);

        assertEquals(64, session.getNumberOfPawns());

        String secondRank = session.areasOfBoard.substring(54,62);
        assertEquals("pppppppp", secondRank);

        String seventhRank = session.areasOfBoard.substring(9,17);
        assertEquals("PPPPPPPP", seventhRank);

        String areasOfBoard = session.areasOfBoard.toString();
        assertEquals(
        "........\n" +
                "PPPPPPPP\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "pppppppp\n" +
                "........\n",
                areasOfBoard
        );
        System.out.println(session.areasOfBoard.toString());
    }
}
