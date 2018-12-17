package be.thomasmore.project1_app5v1;

import java.io.Serializable;

public class Klas implements Serializable {

    public Long id;
    public String naam;
    public int jaar;

    public Klas() {
    }

    public Klas(Long id, String naam, int jaar) {
        this.id = id;
        this.naam = naam;
        this.jaar = jaar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    @Override
    public String toString() {

        return "Klas " + jaar + naam;
    }
}
