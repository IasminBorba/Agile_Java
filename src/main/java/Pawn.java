class Pawn {
    String color;

    Pawn(String color){
        this.color = color;
    }

    public Pawn() {
        this.color = "white";
    }

    String getColor(){
        return  color;
    }
}
