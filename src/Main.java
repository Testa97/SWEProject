import manager.LogisticsManager;
import patterns.SimulationClock;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Benvenuto nel Logistics Management System (Modo #1)");

            LogisticsManager manager = new LogisticsManager();

            // 1. Inizializza (legge il file hubs.txt e carica la mappa)
            manager.init();

            // 2. Crea spedizioni casuali (Ora il metodo è automatico!)
            System.out.println("\n--- CREAZIONE SPEDIZIONI ---");
            manager.createNewShipment(); // Crea una spedizione random
            manager.createNewShipment(); // Ne crea un'altra
            manager.createNewShipment(); // Ne crea una terza

            // 3. Avvia il tempo (Singleton)
            // Il clock inizierà a ticchettare e gli Observer (Manager -> Stati) reagiranno
            System.out.println("\n--- AVVIO SIMULAZIONE ---");
            SimulationClock.getInstance().startSimulation();

            // Tieni vivo il programma finché non premi Invio
            new Scanner(System.in).nextLine();

        } catch (FileNotFoundException e) {
            System.err.println("ERRORE CRITICO: File hubs.txt non trovato!");
            System.err.println("Assicurati che il file sia nella cartella principale del progetto (sopra 'src').");
        }
    }
}