public class Tile {
    // think of Tile as a square on the board

    // private fields
    private int foodOnTile; // needed for players to add bees to game

    private boolean beeHiveBuilt;
    private boolean hornetNestBuilt;
    private boolean tileOnPath; // tile on path from hornet nest to bee hive

    private Tile nestToHive; // reference to next tile on path nest to hive, null if not on path or at the end
    private Tile hiveToNest; // same as nextToHive but hive to nest, null if not on or end

    private HoneyBee beeOnTile; // indicates the type of bee on tile

    private SwarmOfHornets hornets; // all the hornets positioned on the tile

    // public methods
    // Constructor 1 - no inputs and creates a new tile with nothing on it and not on path
    public Tile(){
        // a new tile does not have:
        // no food
        this.foodOnTile = 0;
        // bee hive
        this.beeHiveBuilt = false;
        // hornet nest
        this.hornetNestBuilt = false;
        // not on the path from one to the other
        this.tileOnPath = false;
        // no bee
        this.beeOnTile = null;
        // no hornets
        this.hornets = new SwarmOfHornets(); // empty bc new swarms are empty
    }

    // Constructor 2 - all inputs needed to initialize fields from this class
    public Tile(int food, Boolean hornetNestBuilt, Boolean beeHiveBuilt, Boolean onPathHornetToBee, Tile pathHornetToBee, Tile pathBeeToHornet,
                HoneyBee beeOnTile, SwarmOfHornets hornetsOnTile){
        this.foodOnTile = food;
        this.nestToHive = pathHornetToBee;
        this.hiveToNest = pathBeeToHornet;
        this.beeOnTile = beeOnTile;
        this.hornets = hornetsOnTile;
        this.hornetNestBuilt = hornetNestBuilt;
        this.beeHiveBuilt = beeHiveBuilt;
        this.tileOnPath = onPathHornetToBee;
    }

    // isHive - no input - boolean if bee hive is built on this tile or not
    public boolean isHive(){
        return this.beeHiveBuilt;
    }

    // isNest - no input - boolean if hornet nest is built on this tile or not
    public boolean isNest(){
        return this.hornetNestBuilt;
    }

    // buildHive - no input - void updates field indicating bee hive build
    public void buildHive(){
        this.beeHiveBuilt = true;
    }

    // buildNest - no input - void updates field indicating hornet nest build
    public void buildNest(){
        this.hornetNestBuilt = true;
    }

    // isOnThePath - returns whether this tile is part of the path (nest to hive)
    public boolean isOnThePath(){
        return this.tileOnPath;
    }

    // towardTheHive - Boolean (true/false/null)
    public Tile towardTheHive(){
        return this.nestToHive;
    }

    // towardTheNest - Boolean (true/false/null)
    public Tile towardTheNest(){
        return this.hiveToNest;
    }

    // createPath - input two Tiles - void
    public void createPath(Tile nestToHive, Tile hiveToNest){
        // updates respective fields making THIS tile become a tile that is part
        // of the path that leads from the hornet nest to bee hive (onPath)
        this.nestToHive = nestToHive;
        this.hiveToNest = hiveToNest;
        this.tileOnPath = true;
    }

    // collectFood - no input - return int representing food stored on tile
    public int collectFood(){
        // Tile is left with no food after this method is called
        int tempFood = this.foodOnTile;
        this.foodOnTile = 0;
        return tempFood;
    }

    // storeFood - input int - void
    public void storeFood(int food){
        // takes int food as input which is the amount of food received and adds
        // to tile
        this.foodOnTile = this.foodOnTile + food;
    }

    // getBee - no input - returns bee stored on tile
    public HoneyBee getBee(){
        return this.beeOnTile;
    }

    // getHornet - no input - returns first hornet in swarm on this tile
    public Hornet getHornet(){
        // get first hornet using method in SwarmOfHornets class
        return this.hornets.getFirstHornet();
    }

    // getNumOfHornets - no input - return int number of hornets in this swarm
    public int getNumOfHornets(){
        // return num of hornets using method in SwarmOfHornets class
        return this.hornets.sizeOfSwarm();
    }

    // addInsect - input Insect - return true/false boolean
    public boolean addInsect(Insect insect){
        // adds insect to tile - bee or hornet
        // returns true if insect is added
        // returns false otherwise
        // note: adding an insect to tile not only changes the tile but
        // also the properties of the insect (insect now positioned on tile)
        // don't make copy of input object

        //IF insect = honeybee
        if (insect instanceof HoneyBee) {
            // Conditions:
            // no other bee on tile
            // cannot be placed on hornet nest
            if(this.getBee() == null && !this.isNest()){
                this.beeOnTile = (HoneyBee) insect;
                insect.setPosition(this);
                return true;
            }
        }

        // IF insect = hornet
        if(insect instanceof Hornet) {
            // Conditions:
            // no limit to number of hornets on one tile
            // can only be placed on hornet nest
            // or bee hive
            // or on tile that is on path from nest to hive

            if(this.isHive() || this.isNest() || this.isOnThePath()){
                insect.setPosition(this);
                this.hornets.addHornet((Hornet) insect);
                return true;
            }
        }

        return false;
    }

    // removeInsect - input Insect - return boolean true/false
    public boolean removeInsect(Insect insect){
        // removes the insect from the tile
        // return true if successfully removes insect, false if not
        // note: removing an insect from the tile changes properties of insect too
        // can indicate this by updating the insect's position to null
        if(insect.getPosition() != null) {

            if(insect instanceof HoneyBee){
                this.beeOnTile = null;
                insect.setPosition(null);
                return true;
            }
            if(insect instanceof Hornet){
                this.hornets.removeHornet((Hornet) insect);
                insect.setPosition(null);
                return true;
            }

        }

        return false;

    }


}
