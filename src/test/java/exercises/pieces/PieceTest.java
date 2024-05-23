package exercises.pieces;

import junit.framework.TestCase;

public class PieceTest extends TestCase {
    private Piece createPieceWhite() {
        return Piece.create("white", "pawn");
    }

    private Piece createPieceBlack() {
        return Piece.create("black", "king");
    }
    public void testCreate() {
        Piece firstPiece = createPieceWhite();
        assertEquals("white",firstPiece.getColor());
        assertEquals("pawn", firstPiece.getName());
        assertEquals("p", firstPiece.getRepresentation());
        assertTrue(firstPiece.isWhite(firstPiece));

        Piece secondPiece = createPieceBlack();
        assertEquals("black",secondPiece.getColor());
        assertEquals("king", secondPiece.getName());
        assertEquals("K", secondPiece.getRepresentation());
        assertTrue(secondPiece.isBlack(secondPiece));
    }
}
