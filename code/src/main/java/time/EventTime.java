package time;

public class EventTime {
    private String day;
    private HourRange time;

    public EventTime(String day, HourRange time) {
        this.day = day;
        this.time = time;
    }

    public String getDay() {
        return this.day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public HourRange getTime() {
        return this.time;
    }

    public void setTime(HourRange time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "le " + this.day + " de " + this.time.getOpening() + " à " + this.time.getEnding();
    }
    public int getDureeMinutes() { // retourne la duree du show en minutes
        return this.time.getDuration();
    }

}