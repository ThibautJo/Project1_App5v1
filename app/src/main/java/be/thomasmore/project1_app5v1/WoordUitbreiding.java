package be.thomasmore.project1_app5v1;

import java.io.Serializable;

public class WoordUitbreiding implements Serializable {

    //
    // Audio file naam -> context zin Juist EINDIGT op 1 context zin Fout audio file naam eindigt met 2
    //

    private Long id;
    private String woord; // met lidwoord
    private String defenitie;
    private String contextZinJuist;
    private String contextZinFout;
    private String semantischWeb;
    private String tweelettergreep;

    //positie welke zin eerst zal gezegd worden, zodat niet altijd de juist of foute zin éérst aan bod komt
    private int startValue;

    public WoordUitbreiding() {
    }

    public WoordUitbreiding(Long id, String woord, String defenitie, String contextZinJuist, String contextZinFout, String semantischWeb, String tweelettergreep, int startValue) {
        this.id = id;
        this.woord = woord;
        this.defenitie = defenitie;
        this.contextZinJuist = contextZinJuist;
        this.contextZinFout = contextZinFout;
        this.semantischWeb = semantischWeb;
        this.tweelettergreep = tweelettergreep;
        this.startValue = startValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWoord() {
        return woord;
    }

    public void setWoord(String woord) {
        this.woord = woord;
    }

    public String getDefenitie() {
        return defenitie;
    }

    public void setDefenitie(String defenitie) {
        this.defenitie = defenitie;
    }

    public String getContextZinJuist() {
        return contextZinJuist;
    }

    public void setContextZinJuist(String contextZinJuist) {
        this.contextZinJuist = contextZinJuist;
    }

    public String getContextZinFout() {
        return contextZinFout;
    }

    public void setContextZinFout(String contextZinFout) {
        this.contextZinFout = contextZinFout;
    }

    public String getSemantischWeb() {
        return semantischWeb;
    }

    public void setSemantischWeb(String semantischWeb) {
        this.semantischWeb = semantischWeb;
    }

    public String getTweelettergreep() {
        return tweelettergreep;
    }

    public void setTweelettergreep(String tweelettergreep) {
        this.tweelettergreep = tweelettergreep;
    }

    public int getStartValue() {
        return startValue;
    }

    public void setStartValue(int startValue) {
        this.startValue = startValue;
    }

    @Override
    public String toString() {
        return woord;
    }
}
