package model;

import java.util.ArrayList;
import java.util.List;

public class Hub {
    private String name;
    private double latitude;
    private double longitude;
    private List<BaiaDiCarico> baie; // Le "LandingStrip" di Raspanti

    public Hub(String name, int numeroBaie, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.baie = new ArrayList<>();

        // Crea le baie di carico (inizialmente tutte libere)
        for (int i = 0; i < numeroBaie; i++) {
            baie.add(new BaiaDiCarico(i + 1));
        }
    }

    public String getName() { return name; }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }

    public List<BaiaDiCarico> getBaie() { return baie; }

    // Metodo helper per sapere se c'è almeno una baia libera (Simile a getFullLanding di Raspanti)
    public boolean isBaiaLiberaDisponibile() {
        for (BaiaDiCarico b : baie) {
            if (!b.isOccupata()) return true;
        }
        return false;
    }
}