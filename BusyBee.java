public class BusyBee extends HoneyBee{
    // represents bees that collect pollen
    // no fields

    // public methods
    // Constructor
    public BusyBee(Tile tile){
        super(tile, 5, 2);
    }


    //override take action
    // takeAction - input int return bool
    @Override
    public boolean takeAction() {
        // add 2 foods to the tile where the bee is positioned
        this.getPosition().storeFood(2);
        // returns true
        return true;
    }
}
