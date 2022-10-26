public class AngryBee extends HoneyBee{
    // represents bees that sting hornets

    // private fields
    private int attDamage;

    // public methods
    // Constructor
    public AngryBee(Tile tile, int attDamage){
        super(tile, 10, 1);
        this.attDamage = attDamage;
    }


    //override takeAction method - input int return bool
    @Override
    public boolean takeAction() {
        // No matter what bee does, it ends its action on the same tile as it was
        // at the beginning
        // DOES NOT modify the tile on which the bee is placed on

        // create iterator tile to move the angry bee
        Tile iterator = this.getPosition();

        // check if you're on the hive and there is a hornet - in this case the hornets win
        if(this.getPosition().isHive() && this.getPosition().getHornet() != null){
            return false;
        }

        // check if the angry bee is on the path or is on the hive
        if(this.getPosition().isOnThePath() || this.getPosition().isHive()){
            // check if the iterator is not null and that there is no hornet on the tile
            while(iterator != null && iterator.getHornet() == null){
                // since there is no hornet, move the angry bee one step closer to the nest
                iterator = iterator.towardTheNest();
            }
            // now if there is a hornet and the angry bee is not on the nest, attack the hornet
            if(iterator != null && !iterator.isNest()){
                iterator.getHornet().takeDamage(attDamage);
                return true;
            }
            // since there is no hornet, return false
            else{
                return false;
            }
        }
        // since it's not on the path or not on the hive, return false
        else{
            return false;
        }

    }


    public boolean equals(Object obj) {
        if((super.equals(obj)) && (this.attDamage == ((AngryBee) obj).attDamage)){
            return true;
        }else{
            return false;
        }
    }

}
