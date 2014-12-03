package nl.stijn.saxion;

/**
 * Created by Stijn on 3-12-2014.
 */
public class Sint extends Thread {

    private enum OverlegType{

        WERK("Werk overleg"), VERZAMEL("Verzamel overleg");

        private String overlegType;

        private OverlegType(String type){
            this.overlegType = type;
        }

        public String getType(){
            return this.overlegType;
        }
    }

    private Main sharedMain;

    public Sint(Main sharedMain){
        this.sharedMain = sharedMain;
    }

    @Override
    public void run() {
        while (true){

            // Piet maakt Sint wakker als hij zich meld zodat Sint kan checken
            sharedMain.pietMeldZichSemaphore.acquireUninterruptibly();

            Logger.log("[Sint] Controleer of we kunnen vergaderen");

            // Aantal wachtende pieten mag niet meer veranderen, dus locken we alle tellers
            lockAdministratie();

            if (kanVerzamelOverlegPlaatsvinden()){
                // neem 1 zwarte werkpiet, de rest gaat weer werken
                // neem alle verzamelpieten
                releaseAdministratie();
                // ga overleggen
            } else if (kanWerkOverlegPlaatsvinden()) {
                // Neem 3 werkpieten
                releaseAdministratie();
                // ga vergaderen
            }
            releaseAdministratie();
            sharedMain.pietMeldZichSemaphore.release();
        }
    }

    private boolean kanVerzamelOverlegPlaatsvinden() {
        return sharedMain.aantalVerzamelpieten >= Main.AANTAL_VERZAMELPIETEN_OVERLEG && sharedMain.aantalZwarteWerkpieten >= Main.AANTAL_ZWARTE_WERKPIETEN_OVERLEG;
    }

    private boolean kanWerkOverlegPlaatsvinden() {
        return sharedMain.aantalWerkpieten >= 3;
    }

    /**
     * Methode om de administratie van de gemelde pieten te locken, zodat Sint kan kijken of ze kunnen vergaderen
     */
    private void lockAdministratie(){
        sharedMain.aantalZwarteWerkpietenSemaphore.acquireUninterruptibly();
        sharedMain.aantalWerkpietenSemaphore.acquireUninterruptibly();
        sharedMain.aantalVerzamelpietenSemaphore.acquireUninterruptibly();
    }

    /**
     * Methode om de administratie weer te releasen
     */
    private void releaseAdministratie(){
        sharedMain.aantalZwarteWerkpietenSemaphore.release();
        sharedMain.aantalWerkpietenSemaphore.release();
        sharedMain.aantalVerzamelpietenSemaphore.release();
    }

    private void startOverleg(OverlegType type){

        try {
            switch (type) {
                case WERK:

                    Thread.sleep((int) (Math.random() * 2000));
                    break;
                case VERZAMEL:
                    Thread.sleep((int) (Math.random() * 1000));
                    break;
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
