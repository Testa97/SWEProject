package tests.fleet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import fleet.*;

public class VehicleFactoryTest {

    @Test
    public void testVehicleCreation() {
        VehicleFactory factory = new VehicleFactory();

        // Caso 1: Merce leggera (500kg) -> Deve creare un Furgone
        AbstractVehicle v1 = factory.createVehicle(500);
        assertTrue(v1 instanceof Van, "Dovrebbe essere un Van");

        // Caso 2: Merce pesante (5000kg) -> Deve creare un Camion
        AbstractVehicle v2 = factory.createVehicle(5000);
        assertTrue(v2 instanceof Truck, "Dovrebbe essere un Truck");
    }
}