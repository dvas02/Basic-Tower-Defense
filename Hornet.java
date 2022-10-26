public class Hornet extends Insect{
    // private fields
    // int attack damage
    private int attDamage;


    // public methods
    // Constructor
    public Hornet(Tile tile, int hp, int attDamage){
        super(tile, hp);
        this.attDamage = attDamage;
    }

    // takeAction - override abstract method from Insect (input int, return bool)
    @Override
    public boolean takeAction() {
        // method
        // returns true if hornet stings bee or moves a tile
        // returns false otherwise
        // can assume: hornet never on a tile that isn't the bee hive, hornet nest,
        // or on the path

        if(super.getPosition() == null){
            return false;
        }

        if(super.getPosition().getBee() != null){
            super.getPosition().getBee().takeDamage(attDamage);
            return true;
        }

        else if(super.getPosition().isHive()){
            return false;
        }
        else{
            Tile temp = super.getPosition();
            super.getPosition().removeInsect(this);
            super.setPosition(temp.towardTheHive());
            super.getPosition().addInsect(this);
            return true;
        }

    }


    @Override
    public boolean equals(Object obj) {
        if((super.equals(obj)) && (this.attDamage == ((Hornet) obj).attDamage)){
            return true;
        }else{
            return false;
        }
    }
}
