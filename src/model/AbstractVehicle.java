package model;

public abstract class AbstractVehicle {

    private int id;
    private int speed;      // km/h
    private int capacity;   // kg
    private String modelName;

    public AbstractVehicle() {
        // Genera un ID casuale come fa Raspanti [cite: 84]
        this.id = (int) (Math.random() * 10000);
    }

    // Metodo astratto che le sottoclassi devono implementare
    // Raspanti lo usa per simulare i tempi di attesa/controllo tecnico [cite: 89, 165]
    public abstract void prepareForDeparture();

    // Getters e Setters
    public int getId() { return id; }

    public int getSpeed() { return speed; }
    protected void setSpeed(int speed) { this.speed = speed; }

    public int getCapacity() { return capacity; }
    protected void setCapacity(int capacity) { this.capacity = capacity; }

    public String getModelName() { return modelName; }
    protected void setModelName(String modelName) { this.modelName = modelName; }
}