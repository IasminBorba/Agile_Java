package exercises.chess;

import exercises.pieces.Pawn;

import java.util.ArrayList;
import java.util.Objects;

public class Board {
    int pawnsWhite;
    int pawnsBlack;
    public static final ArrayList<Pawn> pawns = new ArrayList<>();
    public static final ArrayList<Pawn> rank = new ArrayList<>();
    static StringBuilder areasOfBoard = new StringBuilder();

    public static void addPawnsOfRank(String colorOfPawn, int positionOfPieces){
        final ArrayList<Pawn> rank = new ArrayList<>();
        for (int y = 0; y < 8; y++) {
            pawns.add(new Pawn(colorOfPawn));
            rank.add(pawns.get(positionOfPieces));
            positionOfPieces++;
        }
        for (Pawn pawn : rank) {
            areasOfBoard.append(pawn.representation);
        }
        areasOfBoard.append("\n");
    }
    public void initialize() {
        int positionOfPieces = 0;
        for (int x = 0; x < 8; x++) {
            if (Objects.equals(x, 6)) {
                Board.addPawnsOfRank("white", positionOfPieces);
                positionOfPieces += 8;
            } else if (Objects.equals(x,1)) {
                Board.addPawnsOfRank("black", positionOfPieces);
                positionOfPieces += 8;
            } else {
                Board.addPawnsOfRank(" ",positionOfPieces);
                positionOfPieces += 8;
            }
        }
    }

    public Board(int pawnsWhite, int pawnsBlack) {
        this.pawnsWhite = pawnsWhite;
        this.pawnsBlack = pawnsBlack;
    }

    int getNumberOfPawns(){
        return  pawns.size();
    }

    Pawn get(int index){
        return pawns.get(index);
    }

    int getPawnsWhite(){
        return pawnsWhite;
    }

    int getPawnsBlack(){
        return pawnsBlack;
    }
}
