package Homework;

public class Supervisor_PlaceModel {
    Integer r;
    Integer e;
    Integer l;

    Supervisor_PlaceModel(Integer r, Integer e, Integer l) {
        this.r = r;
        this.e = e;
        this.l = l;
    }

    public String Print() {
        return "[" +
                "Supervisor place template:" +
                "\tr" + "=" + this.r +
                "\te" + "=" + this.e +
                "\tl" + "=" + this.l +
        "]";
    }
}
