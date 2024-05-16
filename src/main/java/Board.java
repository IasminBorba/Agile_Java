import java.util.ArrayList;
import java.util.Objects;

public class Board {
    int pawnsWhite;
    int pawnsBlack;
    private final ArrayList<Pawn> pawns = new ArrayList<>();
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
