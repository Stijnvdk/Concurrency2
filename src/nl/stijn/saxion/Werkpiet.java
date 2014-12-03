package nl.stijn.saxion;

/**
 * Created by Stijn on 3-12-2014.
 */
public class Werkpiet extends Piet {

    private boolean isZwart;

    public Werkpiet(Main sharedMain, boolean isZwart){
        super(sharedMain);
        this.isZwart = isZwart;
    }

    public boolean isZwart(){
        return isZwart;
    }


    @Override
    public void run() {
        while(true){

        }
    }
}
