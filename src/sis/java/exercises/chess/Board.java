package exercises.chess;

import exercises.pieces.Piece;
import exercises.util.StringUtil;

import java.util.ArrayList;

public class Board {
    int piecesWhite;
    int piecesBlack;
    private final ArrayList<Piece> pieces = new ArrayList<>();
    private final ArrayList<ArrayList<Piece>> rank = new ArrayList<>();
    public static final StringBuilder areasOfBoard = new StringBuilder();

    public Board(){
        initialize();
    }

    public void initialize() {
        this.piecesWhite = 16;
        this.piecesBlack = 16;
    }

    public String printBoard(){
        rank.add(addFirstAndSeventhRank(new ArrayList<>(), "black"));
        addFirstAndSeventhRank(pieces, "black");
        ArrayList<Piece> firstRank = rank.getFirst();
        for (Piece pieces: firstRank) {
            areasOfBoard.append(pieces.getRepresentation());
        }
        areasOfBoard.append(StringUtil.NEWLINE);


        rank.add(addPiecesPawnsRank(new ArrayList<>(), "black"));
        addPiecesPawnsRank(pieces, "black");
        ArrayList<Piece> firstRankPawns = rank.get(1);
        for (Piece pieces: firstRankPawns) {
            areasOfBoard.append(pieces.getRepresentation());
        }
        areasOfBoard.append(StringUtil.NEWLINE);

        for(int x = 0; x < 4; x++){
            areasOfBoard.append(".".repeat(8));
            areasOfBoard.append(StringUtil.NEWLINE);
        }


        rank.add(addPiecesPawnsRank(new ArrayList<>(), "white"));
        addPiecesPawnsRank(pieces, "white");
        ArrayList<Piece> secondRankPawns = rank.get(2);
        for (Piece pieces: secondRankPawns) {
            areasOfBoard.append(pieces.getRepresentation());
        }
        areasOfBoard.append(StringUtil.NEWLINE);


        rank.add(addFirstAndSeventhRank(new ArrayList<>(), "white"));
        addFirstAndSeventhRank(pieces, "white");
        ArrayList<Piece> seventhRank = rank.get(3);
        for (Piece pieces: seventhRank) {
            areasOfBoard.append(pieces.getRepresentation());
        }
        areasOfBoard.append(StringUtil.NEWLINE);

        return areasOfBoard.toString();
    }

    private ArrayList<Piece> addFirstAndSeventhRank(ArrayList<Piece> aux, String color) {
        aux.add(Piece.create(color, "rook"));
        aux.add(Piece.create(color, "knight"));
        aux.add(Piece.create(color, "bishop"));
        aux.add(Piece.create(color, "queen"));
        aux.add(Piece.create(color, "king"));
        aux.add(Piece.create(color, "bishop"));
        aux.add(Piece.create(color, "knight"));
        aux.add(Piece.create(color, "rook"));
        return aux;
    }

    private ArrayList<Piece> addPiecesPawnsRank(ArrayList<Piece> aux, String color) {
        for (int z = 0; z < 8; z++) {
            aux.add(Piece.create(color, "pawn"));
        }
        return aux;
    }

    int pieceCount(){
        return pieces.size();
    }

    String getRank(int index) {
        ArrayList<Piece> aux = rank.get(index - 1);
        StringBuilder aux2 = new StringBuilder();
        for (Piece piece : aux) {
            aux2.append(piece != null ? piece.getRepresentation() : ".");
        }
        return aux2.toString();
    }
    int getPiecesWhite(){
        return piecesWhite;
    }

    int getPiecesBlack(){
        return piecesBlack;
    }
}
