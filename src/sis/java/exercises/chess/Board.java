package exercises.chess;

import exercises.pieces.Pawn;

import java.util.ArrayList;
import java.util.Objects;

public class Board {
    int pawnsWhite;
    int pawnsBlack;
    public final ArrayList<Pawn> pawns = new ArrayList<>();
    public final ArrayList<Pawn> row = new ArrayList<Pawn>();
    public final ArrayList<String> board = new ArrayList<String>();
    public void initialize() {
        int aux = 8;
        for (int x = 0; x < aux; x++) {
            if (Objects.equals(x, 1)){
                row.add(new Pawn("black"));
                board.add("PPPPPPPP");
            } if (Objects.equals(x,5)){
                row.add(new Pawn("white"));
                board.add("pppppppp");
            } else {
                row.add(new Pawn(" "));
                board.add("........");
            }
            System.out.println(board.get(x));
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
        } else {
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
