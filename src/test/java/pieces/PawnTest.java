package pieces;

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
}
