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
        Logger.log("[Piet] piet gaat werken");
        try {
            Thread.sleep((int) (Math.random() * 10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
