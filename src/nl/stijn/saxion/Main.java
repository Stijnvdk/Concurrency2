package nl.stijn.saxion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {

    public static int AANTAL_VERZAMELPIETEN = 10;
    public static int AANTAL_WERKPIETEN = 5;
    public static int AANTAL_ZWARTE_WERKPIETEN = 5;

    public static int AANTAL_WERKPIETEN_OVERLEG = 3;
    public static int AANTAL_VERZAMELPIETEN_OVERLEG = 3;
    public static int AANTAL_ZWARTE_WERKPIETEN_OVERLEG = 1;

    public int aantalVerzamelpieten, aantalWerkpieten, aantalZwarteWerkpieten;

    public boolean overlegGaande;

    public Semaphore aantalVerzamelpietenSemaphore, aantalWerkpietenSemaphore, aantalZwarteWerkpietenSemaphore, pietMeldZichSemaphore, overlegGaandeSemaphore, wachtOpVerzamelOverlegSemaphore, overlegSemaphore;


    public static void main (String[] args){
        Main main = new Main();
        main.startMain();
    }

    public Main(){

        // Gemelde pieten tellers
        aantalVerzamelpieten = 0;
        aantalZwarteWerkpieten = 0;
        aantalWerkpieten = 0;

        // Gemelde pieten tellers Sempahoren
        aantalVerzamelpietenSemaphore = new Semaphore(1, true);
        aantalWerkpietenSemaphore = new Semaphore(1, true);
        aantalZwarteWerkpietenSemaphore = new Semaphore(1, true);

        // Piet maakt Sint wakker als hij zich meld
        pietMeldZichSemaphore = new Semaphore(0, true);

        // Is er een overleg gaande semaphore
        overlegGaandeSemaphore = new Semaphore(1, true);

        // Verzamelpieten wachten tot ze worden geroepen door Sint om te gaan overleggen
        wachtOpVerzamelOverlegSemaphore = new Semaphore(0, true);

        // Semaphore voor tijdens een overleg
        overlegSemaphore = new Semaphore(0, true);
    }

    public void startMain(){

        Sint sint = new Sint(this);

        List<Piet> pieten = new ArrayList<>();

        for (int i = 0; i < AANTAL_WERKPIETEN; i++){
            pieten.add(new Werkpiet(this, false));
        }

        for (int i = 0; i < AANTAL_ZWARTE_WERKPIETEN; i++){
            pieten.add(new Werkpiet(this, true));
        }

        for (int i = 0; i < AANTAL_VERZAMELPIETEN; i++){
            pieten.add(new VerzamelPiet(this));
        }

        sint.start();

        for (Piet piet: pieten){
            piet.start();
        }


    }


}
