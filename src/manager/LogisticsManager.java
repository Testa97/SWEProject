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

public class LogisticsManager implements Observer {

    private List<Hub> hubs = new ArrayList<>();
    private List<Spedizione> spedizioni = new ArrayList<>();
    private VehicleFactory vehicleFactory = new VehicleFactory();

    // Inizializzazione: Carica i dati e si iscrive al Clock
    public void init() throws FileNotFoundException {
        loadHubsFromFile();

        // OBSERVER: Mi iscrivo al clock per ricevere notifiche ogni ora
        SimulationClock.getInstance().addObserver(this);
    }

    // Lettura file stile Raspanti (Scanner)
    private void loadHubsFromFile() throws FileNotFoundException {
        File file = new File("hubs.txt");
        Scanner scanner = new Scanner(file);

        // AGGIUNGI QUESTA RIGA QUI SOTTO:
        scanner.useLocale(java.util.Locale.US);

        System.out.println("--- CARICAMENTO HUB ---");
        while (scanner.hasNext()) {
            String name = scanner.next();
            int numBaie = scanner.nextInt();
            double lat = scanner.nextDouble(); // Se il tuo PC è in italiano usa la virgola nel file txt (45,46), se inglese il punto (45.46)
            double lon = scanner.nextDouble();

            Hub h = new Hub(name, numBaie, lat, lon);
            hubs.add(h);
            System.out.println("Caricato Hub: " + name);
        }
        scanner.close();
    }

    // FACTORY METHOD: Creazione spedizione intelligente
    public void creaNuovaSpedizione(String nomePartenza, String nomeDestinazione, double pesoMerce) {
        Hub start = findHub(nomePartenza);
        Hub end = findHub(nomeDestinazione);

        if (start != null && end != null) {
            // Qui usiamo la Factory per decidere il veicolo!
            AbstractVehicle veicolo = vehicleFactory.createVehicle(pesoMerce);

            // Simuliamo la preparazione (carico merce)
            veicolo.prepareForDeparture();

            Spedizione s = new Spedizione(start, end, veicolo);
            spedizioni.add(s);
            System.out.println("NUOVA SPEDIZIONE CREATA: Da " + start.getName() +
                    " a " + end.getName() + " con " + veicolo.getModelName());
        } else {
            System.err.println("Errore: Hub non trovati!");
        }
    }

    // OBSERVER: Questo metodo viene chiamato dal Clock ogni ora
    @Override
    public void update(int currentHour) {
        System.out.println("LogisticsManager: Controllo stato spedizioni...");

        for (Spedizione s : spedizioni) {
            if (!s.isCompletata()) {
                s.avanzaDiUnOra();

                if (s.isArrivata()) {
                    // Logica di arrivo: controlliamo le baie
                    gestisciArrivo(s);
                }
            }
        }
    }

    private void gestisciArrivo(Spedizione s) {
        Hub dest = s.getDestinazione();

        // Controllo se c'è posto per scaricare (Risorsa limitata)
        if (dest.isBaiaLiberaDisponibile()) {
            System.out.println(">>> ARRIVO: Il veicolo " + s.getVeicolo().getId() +
                    " ha scaricato a " + dest.getName() + "!");
            s.setCompletata(true);
        } else {
            System.out.println("... ATTESA: Il veicolo " + s.getVeicolo().getId() +
                    " è in coda fuori da " + dest.getName() + " (Baie piene).");
            // Nota: Nella realtà dovremmo occupare la baia e liberarla dopo X tempo.
            // Per ora semplifichiamo: se c'è posto scarica subito.
        }
    }

    private Hub findHub(String name) {
        for (Hub h : hubs) {
            if (h.getName().equalsIgnoreCase(name)) return h;
        }
        return null;
    }
}