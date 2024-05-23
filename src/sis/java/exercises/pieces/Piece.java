package exercises.pieces;

import java.util.Objects;

public class Piece {
    private final String color;
    private final String name;
    public String representation;

    private Piece(String color, String name){
        this.color = color;
        this.name = name;

        if(color.equals("white")){
            switch (name) {
                case "pawn" -> this.representation = "p";
                case "knight" -> this.representation = "n";
                case "rook" -> this.representation = "r";
                case "bishop" -> this.representation = "b";
                case "queen" -> this.representation = "q";
                case "king" -> this.representation = "k";
            }
        } else if(color.equals("black")){
            switch (name) {
                case "pawn" -> this.representation = "P";
                case "knight" -> this.representation = "N";
                case "rook" -> this.representation = "R";
                case "bishop" -> this.representation = "B";
                case "queen" -> this.representation = "Q";
                case "king" -> this.representation = "K";
            }
        } else {
            this.representation = ".";
        }
    }

    public static Piece create(String color, String name){
        return new Piece(color, name);
    }
    public boolean isBlack(Piece piece){
        return Objects.equals(piece.getColor(), "black");
    }

    public boolean isWhite(Piece piece){
        return Objects.equals(piece.getColor(), "white");
    }
    public String getColor(){
        return  color;
    }
    public String getName(){
        return  name;
    }
    public String getRepresentation(){
        return  representation;
    }
}
