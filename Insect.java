public abstract class Insect {
    // Private fields
    // A tile representing the position of the insect
    private Tile tile;
    // An int representing the health points of the insect
    private int hp;

    // public methods
    // Constructor
    public Insect(Tile tile, int hp){
        this.tile = tile;
        this.hp = hp;

        // make sure when insect is added with a position, it is added to the tile
        // not always possible (many exceptions, like more than 1 bee per tile)
        // if not possible, throw exception IllegalArgumentException


        if(!this.tile.addInsect(this)){
            throw new IllegalArgumentException("Cannot place insect here");
        }
    }

    // final getPosition() to get position of this insect
    public final Tile getPosition(){
        return this.tile;
    }

    // final getHealth() of this insect
    public final int getHealth(){
        return this.hp;
    }

    // setPosition(Tile tile){} updates position
    public void setPosition(Tile tile){
        this.tile = tile;
    }

    // takeDamage - input int damaged received - void
    public void takeDamage(int damageRec){
        // applies damage to insect by modiyfing its health
        // subtract damage from insect's hp
        // IF insect is on bee hive, damage reduced by 10%
        // IF hp is below/equal to zero, insect is killed
        // remove insect from game (from tile)

        if(this.tile == null){
            return;
        }


        if(this instanceof HoneyBee && this.tile.isHive()){
            double newDamage = Math.floor(damageRec - (damageRec * 1.0/10.0));
            int newDamageINT = (int) newDamage;
            this.hp = this.hp - newDamageINT;
        }
        else{
            this.hp = this.hp - damageRec;
        }

        if(this.hp <= 0) {
            this.tile.removeInsect(this);
        }

    }

    // takeAction - no input - returns boolean
    public abstract boolean takeAction();


    @Override
    public boolean equals(Object obj) {
        if((obj instanceof Insect) && (this.hp == ((Insect) obj).getHealth()) && (this.tile == ((Insect) obj).getPosition())){
            return true;
        }
        return false;
    }

}
