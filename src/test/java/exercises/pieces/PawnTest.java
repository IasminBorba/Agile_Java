package exercises.pieces;

import junit.framework.TestCase;

public class PawnTest extends TestCase {
    public void testCreate() {
        Pawn firstPawn = new Pawn("white");
        assertEquals("white",firstPawn.getColor());

        Pawn secondPawn = new Pawn("black");
        assertEquals("black",secondPawn.getColor());
    }

    public void testCreatePawnWithoutColor(){
        Pawn pawnWithoutColor = new Pawn();
        assertEquals("white", pawnWithoutColor.getColor());
    }

    public void testRepresentation(){
        Pawn testPawnWhiteRepresentation = new Pawn("white");
        assertEquals(testPawnWhiteRepresentation.representation,"p");

        Pawn testPawnBlackRepresentation = new Pawn("black");
        assertEquals(testPawnBlackRepresentation.representation,"P");
    }
}
