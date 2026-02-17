package manager;

import model.Hub;
import model.Spedizione;
import model.AbstractVehicle;
import patterns.SimulationClock;
import patterns.Observer;
import patterns.VehicleFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Locale;

public class LogisticsManager implements Observer {

    private List<Hub> hubs = new ArrayList<>();
    private List<Spedizione> spedizioni = new ArrayList<>();
    private VehicleFactory vehicleFactory = new VehicleFactory();

    // Inizializzazione
    public void init() throws FileNotFoundException {
        loadHubsFromFile();
        SimulationClock.getInstance().addObserver(this);
    }

    private void loadHubsFromFile() throws FileNotFoundException {
        File file = new File("hubs.txt");
        Scanner scanner = new Scanner(file);
        scanner.useLocale(Locale.US); // Importante per leggere i punti decimali

        System.out.println("--- CARICAMENTO HUB ---");
        while (scanner.hasNext()) {
            String name = scanner.next();
            int numBaie = scanner.nextInt();
            double lat = scanner.nextDouble();
            double lon = scanner.nextDouble();
            hubs.add(new Hub(name, numBaie, lat, lon));
            System.out.println("Caricato Hub: " + name);
        }
        scanner.close();
    }

    public void creaNuovaSpedizione() {
        if (hubs.size() < 2) {
            System.out.println("Errore: Servono almeno 2 Hub per una spedizione!");
            return;
        }

        // Scegliamo due hub a caso
        Hub partenza = hubs.get((int) (Math.random() * hubs.size()));
        Hub destinazione;
        do {
            destinazione = hubs.get((int) (Math.random() * hubs.size()));
        } while (partenza == destinazione);

        // Creiamo un peso casuale per decidere il veicolo
        double pesoMerce = 100 + (Math.random() * 25000); // Da 100kg a 25 tonnellate

        // FACTORY PATTERN: Creazione del veicolo
        AbstractVehicle veicolo = vehicleFactory.createVehicle(pesoMerce);
        veicolo.prepareForDeparture();

        // Creiamo la spedizione (che nasce in stato "In Attesa")
        Spedizione s = new Spedizione(partenza, destinazione, veicolo);
        spedizioni.add(s);

        System.out.println("Nuova spedizione creata: Da " + partenza.getName() + " a " + destinazione.getName() +
                " (" + String.format("%.2f", s.getDistanza()) + " km)");
    }

    // OBSERVER PATTERN: Chiamato ogni ora dal Clock
    @Override
    public void update(int currentHour) {
        System.out.println("LogisticsManager: Aggiornamento stato spedizioni (Ore " + currentHour + ")...");

        // Usiamo un ciclo for normale per evitare problemi di concorrenza
        for (int i = 0; i < spedizioni.size(); i++) {
            Spedizione s = spedizioni.get(i);

            // Se non è finita, la facciamo avanzare
            if (!s.isCompletata()) {
                // STATE PATTERN: Non controlliamo noi cosa fare. Diciamo solo "avanza".
                // Sarà lo Stato corrente (InAttesa o InViaggio) a decidere cosa succede.
                s.avanzaDiUnOra();
            }
        }
    }
}