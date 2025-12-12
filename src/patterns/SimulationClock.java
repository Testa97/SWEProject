package patterns;

import java.util.ArrayList;
import java.util.List;

public class SimulationClock {
    // SINGLETON: Unica istanza
    private static SimulationClock instance;

    private int currentHour = 0;
    private List<Observer> observers = new ArrayList<>();
    private boolean running = false;

    private SimulationClock() {} // Costruttore privato

    public static synchronized SimulationClock getInstance() {
        if (instance == null) {
            instance = new SimulationClock();
        }
        return instance;
    }

    // OBSERVER: Metodi per gestire gli iscritti
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer obs : observers) {
            obs.update(currentHour);
        }
    }

    // LOGICA TEMPO
    public void startSimulation() {
        running = true;
        // Facciamo partire un Thread separato per non bloccare il programma
        new Thread(() -> {
            while (running && currentHour < 24) { // Simuliamo un giorno (24 ore)
                try {
                    System.out.println("\n--- ORA: " + currentHour + ":00 ---");
                    notifyObservers();

                    currentHour++;
                    // Aspettiamo 2 secondi reali per ogni ora simulata (come Raspanti)
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("--- FINE GIORNATA LAVORATIVA ---");
        }).start();
    }

    public void stopSimulation() {
        running = false;
    }
}