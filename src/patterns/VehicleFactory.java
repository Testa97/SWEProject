package patterns;

import model.AbstractVehicle;
import model.Truck;
import model.Van;

public class VehicleFactory {

    // Logica decisionale basata sul peso della merce da spedire
    public AbstractVehicle createVehicle(double weightInKg) {
        if (weightInKg <= 800) {
            return new Van();
        } else {
            return new Truck();
        }
    }
}
