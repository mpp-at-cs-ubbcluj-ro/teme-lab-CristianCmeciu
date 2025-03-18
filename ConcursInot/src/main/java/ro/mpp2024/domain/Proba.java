package ro.mpp2024.domain;

public class Proba extends Entity<Integer>{
    private int distanta;
    private String stil;

    public Proba(int distanta, String stil) {
        this.distanta = distanta;
        this.stil = stil;
    }

    public int getDistanta() {
        return distanta;
    }

    public void setDistanta(int distanta) {
        this.distanta = distanta;
    }

    public String getStil() {
        return stil;
    }

    public void setStil(String stil) {
        this.stil = stil;
    }
}
