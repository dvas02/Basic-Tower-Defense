public class TankyBee extends HoneyBee{
    // represent slow bees but strong - stall hornets

    // private fields
    private int attDamage;
    private int armor;

    // public fields
    // Constructor

    public TankyBee(Tile tile, int attDamage, int armor) {
        super(tile, 30, 3);
        this.attDamage = attDamage;
        this.armor = armor;
    }


    //override takeAction - input int return bool
    @Override
    public boolean takeAction() {
        // tanky bees can only sting hornets that are on the same tile
        // stings first hornet in swarm
        // return true if stings
        // IF no hornet, do nothing and return false
        if(this.getPosition().getHornet() != null){
            this.getPosition().getHornet().takeDamage(this.attDamage);
            return true;
        }else {
            return false;
        }
    }

    // override takeDamage - input int damage received - void
    @Override
    public void takeDamage(int damageRec) {
        // before applying damage, (same way original one does),
        // multiply damage by a multiplier depending on bee's armor
        // multiplier is 100/(100+armor) - remove decimal (force it to be an int)
        //int newDam = damageRec*((100/(100+armor)));

        double newDamage = Math.floor(damageRec * (100.0/(100.0 + this.armor)));
        int newDamageINT = (int) newDamage;
        super.takeDamage(newDamageINT);
    }

    //override equals - type/position/health/food cost/attack damage/armor
    public boolean equals(Object obj) {
        if((super.equals(obj)) && (this.armor == ((TankyBee) obj).armor) && (this.attDamage == ((TankyBee) obj).attDamage)){
            return true;
        }else{
            return false;
        }
    }
}
