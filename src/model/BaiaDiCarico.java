package model;

public class BaiaDiCarico {
    private int numero;
    private boolean occupata;

    public BaiaDiCarico(int numero) {
        this.numero = numero;
        this.occupata = false;
    }

    public boolean isOccupata() { return occupata; }
    public void setOccupata(boolean occupata) { this.occupata = occupata; }
    public int getNumero() { return numero; }
}