package model;

public class Spedizione {
    private int id;
    private Hub partenza;
    private Hub destinazione;
    private AbstractVehicle veicolo;
    private int oreViaggioTotali;
    private int oreViaggioTrascorse;
    private boolean completata;

    public Spedizione(Hub partenza, Hub destinazione, AbstractVehicle veicolo) {
        this.id = (int) (Math.random() * 10000);
        this.partenza = partenza;
        this.destinazione = destinazione;
        this.veicolo = veicolo;
        this.oreViaggioTrascorse = 0;
        this.completata = false;

        // Calcoliamo subito la distanza e quanto tempo ci vuole
        double distanzaKm = calcolaDistanza(partenza, destinazione);
        // Tempo = Spazio / Velocità (arrotondato per eccesso)
        this.oreViaggioTotali = (int) Math.ceil(distanzaKm / veicolo.getSpeed());
    }

    // Formula "Harversine" semplificata per calcolare km tra due coordinate
    // (Simile a quella usata da Raspanti)
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

        // TRUCCO REALISMO: Le strade non sono dritte!
        // Moltiplichiamo per 1.3 per simulare curve, uscite autostradali, ecc.
        double distanzaSuStrada = distanzaLineaDaria * 1.3;

        return distanzaSuStrada;
    }

    // Metodo per far avanzare il camion
    public void avanzaDiUnOra() {
        if (!completata) {
            oreViaggioTrascorse++;
        }
    }

    public boolean isArrivata() {
        return oreViaggioTrascorse >= oreViaggioTotali;
    }

    // Getter
    public int getId() { return id; }
    public boolean isCompletata() { return completata; }
    public void setCompletata(boolean completata) { this.completata = completata; }
    public Hub getPartenza() { return partenza; }
    public Hub getDestinazione() { return destinazione; }
    public AbstractVehicle getVeicolo() { return veicolo; }
}
