package be.thomasmore.project1_app5v1;

public class Groep {

    private Long id;
    private int conditie_id1;
    private int conditie_id2;
    private int conditie_id3;

    public Groep() {
    }

    public Groep(Long id, int conditie_id1, int conditie_id2, int conditie_id3) {
        this.id = id;
        this.conditie_id1 = conditie_id1;
        this.conditie_id2 = conditie_id2;
        this.conditie_id3 = conditie_id3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getConditie_id1() {
        return conditie_id1;
    }

    public void setConditie_id1(int conditie_id1) {
        this.conditie_id1 = conditie_id1;
    }

    public int getConditie_id2() {
        return conditie_id2;
    }

    public void setConditie_id2(int conditie_id2) {
        this.conditie_id2 = conditie_id2;
    }

    public int getConditie_id3() {
        return conditie_id3;
    }

    public void setConditie_id3(int conditie_id3) {
        this.conditie_id3 = conditie_id3;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
