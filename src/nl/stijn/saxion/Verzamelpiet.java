package nl.stijn.saxion;

/**
 * Created by Stijn on 3-12-2014.
 */
public class VerzamelPiet extends Piet {


    public VerzamelPiet(Main sharedMain){
        super(sharedMain);
    }

    @Override
    public void run() {

        while(true) {
            gaWerken();

            Logger.log("[Verzamelpiet] Verzamelpiet meld zich");

            // Verzamelpiet gaat in de wachtrij zitten
            sharedMain.aantalVerzamelpietenSemaphore.acquireUninterruptibly();
            sharedMain.aantalVerzamelpieten++;
            sharedMain.aantalVerzamelpietenSemaphore.release();

            // Verzamelpiet wacht tot hij door Sint uit de wachtrij wordt gehaald
            sharedMain.wachtOpVerzamelOverlegSemaphore.acquireUninterruptibly();

            // Verzamelpiet gaat overleggen. Hij releast de semaphore meteen weer
            // zodat deze klaar is voor de volgende ronde
            sharedMain.overlegSemaphore.acquireUninterruptibly();
            sharedMain.overlegSemaphore.release();
        }
    }
}
