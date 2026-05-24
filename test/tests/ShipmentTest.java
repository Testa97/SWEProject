package tests;

import infrastructure.Hub;
import shipment.Shipment;
import fleet.Van;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShipmentTest {

    @Test
    public void testDistanceCalculation() {
        // Coordinate reali approssimative
        // Roma Fiumicino
        Hub roma = new Hub("Roma", 5, 41.8003, 12.2389);
        // Milano Malpensa
        Hub milano = new Hub("Milano", 5,45.6301 , 8.7231);
        Van van = new Van();

        Shipment shipment = new Shipment(roma, milano, van);

        // La distanza in linea d'aria Roma-Milano è di circa 510 km.
        // Il costruttore di Shipment applica un moltiplicatore di 1.3 per simulare il percorso stradale.
        // Risultato atteso: circa 660-670 km.
        double distance = shipment.getDistance();

        assertTrue(distance > 600 && distance < 750,
                "Il calcolo geodetico deve restituire una distanza realistica tra i 600 km e i 750 km.");
    }
}