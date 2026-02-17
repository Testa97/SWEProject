package model;

public class LoadingBay {
    private int capacity;
    private boolean isOccupied;

    public LoadingBay(int capacity) {
        this.capacity = capacity;
        this.isOccupied = false;
    }

    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean occupied) { this.isOccupied = occupied; }
    public int getCapacity() { return capacity; }
}