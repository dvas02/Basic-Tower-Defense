public abstract class HoneyBee extends Insect{
    // private fields
    // int food cost
    private int foodCost;


    // public methods
    // Constructor
    public HoneyBee(Tile tile, int hp, int foodCost){
        super(tile,hp);
        this.foodCost = foodCost;
    }

    //getCost returns cost of food
    public int getCost(){
        return this.foodCost;
    }



    @Override
    public boolean equals(Object obj) {
        if((super.equals(obj)) && (this.foodCost == ((HoneyBee) obj).getCost())){
            return true;
        }else{
            return false;
        }
    }
}
