public class SwarmOfHornets {
    // group of hornets together
    // need to find a way to add a hornet to the first position
    // and remove the last hornet

    // private fields
    private Hornet[] hornets;
    private int sizeSwarm = 0;

    // public methods
    // Constructor
    public SwarmOfHornets(){
        // A constructor that takes no inputs and creates an empty swarm.
        // To do so, the fields should be initialized to reflect the fact that at
        // the moment there are no hornets in the swarm.
        this.hornets = new Hornet[0];

    }

    // sizeOfSwarm - no inputs - returns # hornets in this swarm
    public int sizeOfSwarm() {
        return this.sizeSwarm;
    }

    // getHornets - no inputs - returns array containing hornets in this swarm
    public Hornet[] getHornets() {
        // hornets should appear in order of when they have joined the swarm
        // array must contain as many elements as the number of hornets
        // no null elements
        return hornets;

    }

    // getFirstHornet  - no input - returns first hornet in this swarm
    public Hornet getFirstHornet(){
        // if no hornets in this swarm, return null
        // do not remove the hornet from the swarm
        if(this.sizeSwarm == 0){
            return null;
        }else {
            //return null;
            return hornets[0];
        }
    }

    // addHornet - input Hornet - void
    public void addHornet(Hornet newHornet){
        // adds hornet at the END of queue of hornets in this swarm
        // CHECK: no space (add space)
        // do not remove any hornet from swarm here
        if(newHornet == null){
            return;
        }

        Hornet[] tempHornets = new Hornet[this.hornets.length+1]; // new hornets array one size bigger

        for (int i = 0; i < this.hornets.length; i++) {
            // copy all original hornets to temp one, leaves the last spot open for the new one
            tempHornets[i] = this.hornets[i];
        }

        tempHornets[this.sizeSwarm] = newHornet;

        // Now that tempHornets is complete, make this.hornets equal to the temp one
        this.hornets = tempHornets;
        this.sizeSwarm++;

    }

    // removeHornet - input Hornet - returns boolean
    public boolean removeHornet(Hornet hornet){
        // remove the first occurrence hornet of this swarm (use == )
        // CHECK: if no such type of hornet exists, return false
        // if remove hornet, return true
        // NOTE: method compares hornets using directly their reference

        int index = -1; // index of hornet to be removed

        for (int i = 0; i < this.sizeSwarm; i++) {
            if(this.hornets[i] == hornet){ //checking if the current hornet of the swarm is equal to the one being removed
                this.hornets[i] = null; // make this index of the removed hornet null (removing hornet)
                index = i; // record the index of the removed hornet
                this.sizeSwarm--; // decrease the size of the swarm
                break;
            }
        }

        if(index == -1){ // make index = -1 at the beginning because it's an impossible index for the removed hornet to be
            // checking if the hornet was actually removed before remaking swarm
            return false;
        }

        // Remake swarm one spot less
        // Using a and b to make the new swarm of hornets without any null spots

        Hornet[] tempHornets = new Hornet[this.sizeSwarm];
        int a = 0;
        int b = 0;

        while(a < this.hornets.length && b < tempHornets.length){
            if(a != index){
                tempHornets[b] = this.hornets[a];
                a++;
                b++;
            }
            else{
                a++;
            }
        }
        this.hornets = tempHornets;

        return true; // only way its false is if the index is -1 (unchanged basically)

    }


}
