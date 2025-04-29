package time;

public class Hour {
    private int h;
    private int m;

    public Hour(int h, int m) {
        this.h = h;
        this.m = m;
    }

    public int getH() {
        return this.h;
    }

    public int getM() {
        return this.m;
    }

    public void setH(int h) {
        this.h = h;
    } 

    public void setM(int m) {
        this.m = m;
    }

    @Override
    public String toString() {
        return String.format("%dh%d", this.getH(), this.getM());
    }
}