package exercises.pieces;

public class Pawn {
    public String color;

    public Pawn(String color){
        this.color = color;
    }

    public Pawn() {
        this.color = "white";
    }

    public String getColor(){
        return  color;
    }
}
