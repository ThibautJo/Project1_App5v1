package be.thomasmore.project1_app5v1.classes;

public class Conditie {

    private Long id;
    private String woord1;
    private String woord2;
    private String woord3;


    public Conditie() {
    }

    public Conditie(Long id, String woord1, String woord2, String woord3) {
        this.id = id;
        this.woord1 = woord1;
        this.woord2 = woord2;
        this.woord3 = woord3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWoord1() {
        return woord1;
    }

    public void setWoord1(String woord1) {
        this.woord1 = woord1;
    }

    public String getWoord2() {
        return woord2;
    }

    public void setWoord2(String woord2) {
        this.woord2 = woord2;
    }

    public String getWoord3() {
        return woord3;
    }

    public void setWoord3(String woord3) {
        this.woord3 = woord3;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
