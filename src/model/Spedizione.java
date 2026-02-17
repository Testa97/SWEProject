package model;

import states.*; // Importiamo il pacchetto con i nuovi stati

public class Spedizione {
    // Dati immutabili della spedizione
    private int id;
    private Hub partenza;
    private Hub destinazione;
    private AbstractVehicle veicolo;

    // PATTERN STATE: Il cuore della modifica
    // Non usiamo più booleani o contatori qui, ma un oggetto che rappresenta lo stato
    private StatoSpedizione statoCorrente;

    // Cache della distanza (calcolata una volta sola)
    private double distanzaKm;

    public Spedizione(Hub partenza, Hub destinazione, AbstractVehicle veicolo) {
        this.id = (int) (Math.random() * 10000);
        this.partenza = partenza;
        this.destinazione = destinazione;
        this.veicolo = veicolo;

        // Calcoliamo subito la distanza e salviamola
        this.distanzaKm = calcolaDistanza(partenza, destinazione);

        // STATO INIZIALE: Appena creata, la spedizione è "In Attesa"
        this.statoCorrente = new StatoInAttesa();
    }

    // --- LOGICA CORE (PATTERN STATE) ---

    // Questo metodo viene chiamato dal LogisticsManager ogni ora.
    // Invece di fare calcoli qui, DELEGHIAMO il lavoro allo stato corrente.
    public void avanzaDiUnOra() {
        statoCorrente.avanza(this);
    }

    // Questo metodo serve agli Stati per cambiare il contesto (es. da Attesa -> Viaggio)
    public void setStato(StatoSpedizione nuovoStato) {
        this.statoCorrente = nuovoStato;
    }

    // Helper per il LogisticsManager (per sapere quando smettere di controllarla)
    public boolean isCompletata() {
        return statoCorrente instanceof StatoConsegnato;
    }

    public String getStatoLeggibile() {
        return statoCorrente.getDescrizione();
    }

    // --- CALCOLI GEOGRAFICI (Preservati dal vecchio codice) ---

    private double calcolaDistanza(Hub h1, Hub h2) {
        double lat1 = Math.toRadians(h1.getLatitude());
        double lon1 = Math.toRadians(h1.getLongitude());
        double lat2 = Math.toRadians(h2.getLatitude());
        double lon2 = Math.toRadians(h2.getLongitude());

        double raggioTerra = 6371; // km
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;

        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));
        double distanzaLineaDaria = c * raggioTerra;

        // TRUCCO REALISMO: Moltiplichiamo per 1.3
        return distanzaLineaDaria * 1.3;
    }

    // --- GETTERS (Servono agli Stati per fare i calcoli) ---

    public int getId() { return id; }
    public AbstractVehicle getVeicolo() { return veicolo; }
    public Hub getDestinazione() { return destinazione; }
    public double getDistanza() { return distanzaKm; }
}