package be.thomasmore.project1_app5v1;

import java.util.Date;

public class Leerling {

    private Long id;
    private String naam;
    private String voornaam;
    private Date geboortedatum;

    private int klasID;
    private int groepID;

    public Leerling() {
    }

    public Leerling(Long id, String naam, String voornaam, Date geboortedatum, int klasID, int groepID) {
        this.id = id;
        this.naam = naam;
        this.voornaam = voornaam;
        this.geboortedatum = geboortedatum;
        this.klasID = klasID;
        this.groepID = groepID;
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

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Date geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public int getKlasID() {
        return klasID;
    }

    public void setKlasID(int klasID) {
        this.klasID = klasID;
    }

    public int getGroepID() {
        return groepID;
    }

    public void setGroepID(int groepID) {
        this.groepID = groepID;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
