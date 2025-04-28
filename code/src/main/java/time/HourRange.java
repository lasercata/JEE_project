package time;

public class HourRange {
    private Hour opening;
    private Hour ending;

    public HourRange(Hour opening, Hour ending) {
        this.opening = opening;
        this.ending = ending;
    }

    public Hour getOpening() {
        return this.opening;
    }

    public Hour getEnding() {
        return this.ending;
    }

    public void setOpening(Hour opening) {
        this.opening = opening;
    }

    public void setEnding(Hour ending) {
        this.ending = ending;
    }

    @Override
    public String toString() {
        return String.format("est ouvert(e) de %s à %s", this.getOpening().toString(), this.getEnding().toString());
    }
}