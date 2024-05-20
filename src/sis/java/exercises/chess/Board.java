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
        for (int y = 0; y < 8; y++) {
            pawns.add(new Pawn(colorOfPawn));
            rank.add(pawns.get(positionOfPieces));
            areasOfBoard.append(rank.get(positionOfPieces).representation);
            positionOfPieces++;
        }
        areasOfBoard.append("\n");
    }
    public void initialize() {
        int positionOfPieces = 0;
        for (int x = 0; x < 7; x++) {
            if (Objects.equals(x, 6)) {
                Board.addPawnsOfRank("white", positionOfPieces);
                positionOfPieces += 8;
            } if (Objects.equals(x,1)) {
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

    void enroll(Pawn pawnsColor){
        String aux = pawnsColor.color;

        pawns.add(pawnsColor);
        if (Objects.equals(aux, "white")) {
            this.pawnsWhite += 1;
        }
        if (Objects.equals(aux, "black")) {
            this.pawnsBlack += 1;
        }
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
