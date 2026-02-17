package manager;

import model.Hub;
import model.Shipment;
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
    private List<Shipment> shipments = new ArrayList<>();
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

    public void createNewShipment() {
        if (hubs.size() < 2) {
            System.out.println("Errore: Servono almeno 2 Hub per una spedizione!");
            return;
        }

        // Scegliamo due hub a caso
        Hub origin = hubs.get((int) (Math.random() * hubs.size()));
        Hub destination;
        do {
            destination = hubs.get((int) (Math.random() * hubs.size()));
        } while (origin == destination);

        // Creiamo un peso casuale per decidere il veicolo
        double pesoMerce = 100 + (Math.random() * 25000); // Da 100kg a 25 tonnellate

        // FACTORY PATTERN: Creazione del veicolo
        AbstractVehicle vehicle = vehicleFactory.createVehicle(pesoMerce);
        vehicle.prepareForDeparture();

        // Creiamo la spedizione (che nasce in stato "In Attesa")
        Shipment s = new Shipment(origin, destination, vehicle);
        shipments.add(s);

        System.out.println("Nuova spedizione creata: Da " + origin.getName() + " a " + destination.getName() +
                " (" + String.format("%.2f", s.getDistance()) + " km)");
    }

    // OBSERVER PATTERN: Chiamato ogni ora dal Clock
    @Override
    public void update(int currentHour) {
        System.out.println("LogisticsManager: Aggiornamento stato spedizioni (Ore " + currentHour + ")...");

        // Usiamo un ciclo for normale per evitare problemi di concorrenza
        for (int i = 0; i < shipments.size(); i++) {
            Shipment s = shipments.get(i);

            // Se non è finita, la facciamo avanzare
            if (!s.isCompleted()) {
                // STATE PATTERN: Non controlliamo noi cosa fare. Diciamo solo "avanza".
                // Sarà lo Stato corrente (InAttesa o InViaggio) a decidere cosa succede.
                s.advanceOneHour();
            }
        }
    }
}