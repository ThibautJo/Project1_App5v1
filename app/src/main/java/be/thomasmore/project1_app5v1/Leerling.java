package be.thomasmore.project1_app5v1;

import java.io.Serializable;
import java.util.Date;

public class Leerling implements Serializable {

    private Long id;
    private String naam;
    private String voornaam;
    private int punten;

    private int klasId;
    private int groepId;


    public Leerling() {
    }

    public Leerling(Long id, String naam, String voornaam, int punten, int klasID, int groepID) {
        this.id = id;
        this.naam = naam;
        this.voornaam = voornaam;
        this.punten = punten;
        this.klasId = klasID;
        this.groepId = groepID;
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

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public int getPunten() {
        return punten;
    }

    public void setPunten(int punten) {
        this.punten = punten;
    }

    public int getKlasID() {
        return klasId;
    }

    public void setKlasID(int klasID) {
        this.klasId = klasID;
    }

    public int getGroepID() {
        return groepId;
    }

    public void setGroepID(int groepID) {
        this.groepId = groepID;
    }

    @Override
    public String toString() {
        return voornaam + " " + naam + " | Aantal punten: " + punten + " | Groep: ";
    }
}
