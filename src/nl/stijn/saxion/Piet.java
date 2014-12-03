package nl.stijn.saxion;

import java.util.Random;

/**
 * Created by Stijn on 3-12-2014.
 */
public class Piet extends Thread{


    protected Main sharedMain;

    public Piet(Main sharedMain){
        this.sharedMain = sharedMain;
    }

    public void gaWerken(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
