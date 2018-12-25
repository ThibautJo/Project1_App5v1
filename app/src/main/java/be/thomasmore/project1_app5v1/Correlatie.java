package be.thomasmore.project1_app5v1;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Correlatie {

    //
    // deze klasse is expliciet voor oefening 4
    // correlatie tussen da afbeelding zoeken van d.m.v het doelwoord
    // Zie Afbeeldingen oefening 4 document
    //

    //lijst van afbeeldingen die er moeten zijn [.jpg of .png] --> naamgeving foto's
    //
    //duikbril, ogen, zee, zwemmen, schrijven
    //klimtouw, klimmen, sterk, turnzaal, zwembad
    //kroos, groen, vijver, lamp, eend
    //riet, vijver, eend, bos, bril
    //val, pijn, voorval, pleister, appel
    //kompas, wandelen, rugzak, landkaart, bad
    //steil, berg, beklimmen, trap, bloem
    //zwaan, vijver, vleugels, wit, boek
    //kamp, tent, kampvuur, slaapzak, deur
    //zaklamp, licht, betterij, donker, paard

    // woordBenaming is het woord met lidwoord bij en word niet gebruikt om de foto op te halen

    private Long id;
    private String doelwoord;
    private String woordJuist1;
    private String woordJuist2;
    private String woordJuist3;
    private String woordFout;

    public Correlatie() {
    }

    public Correlatie(Long id, String doelwoord, String woordJuist1, String woordJuist2, String woordJuist3, String woordFout) {
        this.id = id;
        this.doelwoord = doelwoord;
        this.woordJuist1 = woordJuist1;
        this.woordJuist2 = woordJuist2;
        this.woordJuist3 = woordJuist3;
        this.woordFout = woordFout;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoelwoord() {
        return doelwoord;
    }

    public void setDoelwoord(String doelwoord) {
        this.doelwoord = doelwoord;
    }

    public String getWoordJuist1() {
        return woordJuist1;
    }

    public void setWoordJuist1(String woordJuist1) {
        this.woordJuist1 = woordJuist1;
    }

    public String getWoordJuist2() {
        return woordJuist2;
    }

    public void setWoordJuist2(String woordJuist2) {
        this.woordJuist2 = woordJuist2;
    }

    public String getWoordJuist3() {
        return woordJuist3;
    }

    public void setWoordJuist3(String woordJuist3) {
        this.woordJuist3 = woordJuist3;
    }

    public String getWoordFout() {
        return woordFout;
    }

    public void setWoordFout(String woordFout) {
        this.woordFout = woordFout;
    }

    //return woorden in arraylist
    public ArrayList<String> getCorrelatieArraylist(){
        ArrayList<String> correlatie = new ArrayList<>(4);
        correlatie.add(getWoordJuist1());
        correlatie.add(getWoordJuist2());
        correlatie.add(getWoordJuist3());
        correlatie.add(getWoordFout());

        return correlatie;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
