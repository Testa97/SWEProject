package model;

import java.util.ArrayList;
import java.util.List;

public class Hub {
    private String name;
    private double latitude;
    private double longitude;
    private List<LoadingBay> bays; // Le "LandingStrip" di Raspanti

    public Hub(String name, int bayCount, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bays = new ArrayList<>();

        // Crea le baie di carico (inizialmente tutte libere)
        for (int i = 0; i < bayCount; i++) {
            bays.add(new LoadingBay(i + 1));
        }
    }

    public String getName() { return name; }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }

    public List<LoadingBay> getBays() { return bays; }

    // Metodo helper per sapere se c'è almeno una baia libera (Simile a getFullLanding di Raspanti)
    public boolean isBayAvailable() {
        for (LoadingBay b : bays) {
            if (!b.isOccupied()) return true;
        }
        return false;
    }
}