import manager.LogisticsManager;
import patterns.SimulationClock;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            LogisticsManager manager = new LogisticsManager();

            // 1. Inizializza (legge file)
            manager.init();

            // 2. Crea qualche spedizione di prova
            // Caso 1: Pacco leggero (Userà Furgone)
            manager.creaNuovaSpedizione("Milano", "Roma", 50.0);

            // Caso 2: Pacco pesante (Userà Tir)
            manager.creaNuovaSpedizione("Napoli", "Milano", 5000.0);

            // 3. Avvia il tempo (Singleton)
            System.out.println("\n--- AVVIO SIMULAZIONE ---");
            SimulationClock.getInstance().startSimulation();

            // Tieni vivo il main (opzionale, solo per non farlo chiudere subito se non ci sono thread daemon)
            new Scanner(System.in).nextLine();

        } catch (FileNotFoundException e) {
            System.err.println("File hubs.txt non trovato! Assicurati che sia nella cartella del progetto.");
        }
    }
}