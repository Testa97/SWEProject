package model;

import states.*; // Importiamo il pacchetto con i nuovi stati

public class Shipment {
    // Dati immutabili della spedizione
    private int id;
    private Hub origin;
    private Hub destination;
    private AbstractVehicle vehicle;

    // PATTERN STATE: Il cuore della modifica
    // Non usiamo più booleani o contatori qui, ma un oggetto che rappresenta lo stato
    private ShipmentState currentState;

    // Cache della distanza (calcolata una volta sola)
    private double distanceKm;

    public Shipment(Hub origin, Hub destination, AbstractVehicle vehicle) {
        this.id = (int) (Math.random() * 10000);
        this.origin = origin;
        this.destination = destination;
        this.vehicle = vehicle;

        // Calcoliamo subito la distanza e salviamola
        this.distanceKm = calculateDistance(origin, destination);

        // STATO INIZIALE: Appena creata, la spedizione è "In Attesa"
        this.currentState = new WaitingState();
    }

    // --- LOGICA CORE (PATTERN STATE) ---

    // Questo metodo viene chiamato dal LogisticsManager ogni ora.
    // Invece di fare calcoli qui, DELEGHIAMO il lavoro allo stato corrente.
    public void advanceOneHour() {
        currentState.advance(this);
    }

    // Questo metodo serve agli Stati per cambiare il contesto (es. da Attesa -> Viaggio)
    public void setState(ShipmentState newState) {
        this.currentState = newState;
    }

    // Helper per il LogisticsManager (per sapere quando smettere di controllarla)
    public boolean isCompleted() {
        return currentState instanceof DeliveredState;
    }

    public String getStatusDescription() {
        return currentState.getDescription();
    }

    // --- CALCOLI GEOGRAFICI (Preservati dal vecchio codice) ---

    private double calculateDistance(Hub h1, Hub h2) {
        double lat1 = Math.toRadians(h1.getLatitude());
        double lon1 = Math.toRadians(h1.getLongitude());
        double lat2 = Math.toRadians(h2.getLatitude());
        double lon2 = Math.toRadians(h2.getLongitude());

        double earthRadius = 6371; // km
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;

        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));
        double airDistance = c * earthRadius;

        // TRUCCO REALISMO: Moltiplichiamo per 1.3
        return airDistance * 1.3;
    }

    // --- GETTERS (Servono agli Stati per fare i calcoli) ---

    public int getId() { return id; }
    public AbstractVehicle getVehicle() { return vehicle; }
    public Hub getDestination() { return destination; }
    public double getDistance() { return distanceKm; }
}