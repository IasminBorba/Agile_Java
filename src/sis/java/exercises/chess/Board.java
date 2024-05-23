package exercises.chess;

import exercises.pieces.Piece;
import exercises.util.StringUtil;

import java.util.ArrayList;
import java.util.Objects;

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
        addPiecesToTheBoard();
        this.piecesWhite = getPiecesWhite();
        this.piecesBlack = getPiecesBlack();
    }
    public void addPieces(String piece, String color, int index){
        if (Objects.equals(piece, "")){
            for(int x = 0; x < 4; x++){
                piecesOnTheBoard.append(".".repeat(8));
                piecesOnTheBoard.append(StringUtil.NEWLINE);
                ranks.add(new ArrayList<>());
            }
        } else {
            ranks.add(addPiecesOfRank(new ArrayList<>(), color, piece));
            addPiecesOfRank(pieces, color, piece);
            ArrayList<Piece> firstRankPawns = ranks.get(index);
            for (Piece pieces: firstRankPawns) {
                piecesOnTheBoard.append(pieces.getRepresentation());
                if (Objects.equals(color, "white")){
                    piecesWhite++;
                } else {
                    piecesBlack++;
                }
            }
            piecesOnTheBoard.append(StringUtil.NEWLINE);
        }
    }
    public void addPiecesToTheBoard(){
        addPieces("others", "black", 0);
        addPieces("pawn", "black", 1);

        addPieces("", "black", 0);

        addPieces("pawn", "white",6);
        addPieces("others", "white",7);
    }

    public String print(){
        return piecesOnTheBoard.toString();
    }

    private ArrayList<Piece> addPiecesOfRank(ArrayList<Piece> aux, String color, String piece) {
        if (Objects.equals(piece, "others")) {
            aux.add(Piece.create(color, "rook"));
            aux.add(Piece.create(color, "knight"));
            aux.add(Piece.create(color, "bishop"));
            aux.add(Piece.create(color, "queen"));
            aux.add(Piece.create(color, "king"));
            aux.add(Piece.create(color, "bishop"));
            aux.add(Piece.create(color, "knight"));
            aux.add(Piece.create(color, "rook"));
        } else {
            for (int z = 0; z < 8; z++) {
                aux.add(Piece.create(color, "pawn"));
            }
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
