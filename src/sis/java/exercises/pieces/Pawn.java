package exercises.pieces;

public class Pawn {
    public String color;
    public String representation;

    public Pawn(String color){
        this.color = color;
        if(color.equals("white")){
            this.representation = "p";
        }
        if(color.equals("black")){
            this.representation = "P";
        }
    }

    public Pawn() {
        this.color = "white";
    }

    public String getColor(){
        return  color;
    }
}
