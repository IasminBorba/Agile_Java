package exercises.chess;

import exercises.pieces.Piece;
import exercises.util.StringUtil;

import java.util.ArrayList;

public class Board {
    int piecesWhite;
    int piecesBlack;
    private final ArrayList<Piece> pieces = new ArrayList<>();
    private final ArrayList<ArrayList<Piece>> ranks = new ArrayList<>();
    public static final StringBuilder piecesOnTheBoard = new StringBuilder();

    public Board(){
        initialize();
    }

    public void initialize() {
        this.piecesWhite = 16;
        this.piecesBlack = 16;

        addPiecesToTheBoard();
    }

    public void addPiecesToTheBoard(){
        ranks.add(addFirstAndSeventhRank(new ArrayList<>(), "black"));
        addFirstAndSeventhRank(pieces, "black");
        ArrayList<Piece> firstRank = ranks.getFirst();
        for (Piece pieces: firstRank) {
            piecesOnTheBoard.append(pieces.getRepresentation());
        }
        piecesOnTheBoard.append(StringUtil.NEWLINE);

        ranks.add(addPawnPiecesRank(new ArrayList<>(), "black"));
        addPawnPiecesRank(pieces, "black");
        ArrayList<Piece> firstRankPawns = ranks.get(1);
        for (Piece pieces: firstRankPawns) {
            piecesOnTheBoard.append(pieces.getRepresentation());
        }
        piecesOnTheBoard.append(StringUtil.NEWLINE);

        for(int x = 0; x < 4; x++){
            piecesOnTheBoard.append(".".repeat(8));
            piecesOnTheBoard.append(StringUtil.NEWLINE);
        }

        ranks.add(addPawnPiecesRank(new ArrayList<>(), "white"));
        addPawnPiecesRank(pieces, "white");
        ArrayList<Piece> secondRankPawns = ranks.get(2);
        for (Piece pieces: secondRankPawns) {
            piecesOnTheBoard.append(pieces.getRepresentation());
        }
        piecesOnTheBoard.append(StringUtil.NEWLINE);

        ranks.add(addFirstAndSeventhRank(new ArrayList<>(), "white"));
        addFirstAndSeventhRank(pieces, "white");
        ArrayList<Piece> seventhRank = ranks.get(3);
        for (Piece pieces: seventhRank) {
            piecesOnTheBoard.append(pieces.getRepresentation());
        }
        piecesOnTheBoard.append(StringUtil.NEWLINE);
    }

    public String print(){
        return piecesOnTheBoard.toString();
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

    private ArrayList<Piece> addPawnPiecesRank(ArrayList<Piece> aux, String color) {
        for (int z = 0; z < 8; z++) {
            aux.add(Piece.create(color, "pawn"));
        }
        return aux;
    }

    int pieceCount(){
        return pieces.size();
    }

    String getRank(int index) {
        ArrayList<Piece> boardRank = ranks.get(index - 1);
        StringBuilder rankPiece = new StringBuilder();
        for (Piece piece : boardRank) {
            rankPiece.append(piece != null ? piece.getRepresentation() : ".");
        }
        return rankPiece.toString();
    }
    int getPiecesWhite(){
        return piecesWhite;
    }

    int getPiecesBlack(){
        return piecesBlack;
    }
}
