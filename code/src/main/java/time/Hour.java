package time;

public class Hour {
    private int h;
    private int m;

    public Hour(int h, int m) {
        this.h = h;
        this.m = m;
    }

    /**
     * Constructor of the Hour class, from a string
     *
     * @param hour - the hour in the format 'xx:xx', where 'x' can be replaced by any number.
     */
    public Hour(String hour) {
        String[] hourParts = hour.split(":");
        this.h = Integer.parseInt(hourParts[0]);
        this.m = Integer.parseInt(hourParts[1]);
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

    public String toSQL() {
        return "'" + h + ":" + m + "'";
    }
    
    @Override
    public String toString() {
        return String.format("%dh%d", this.getH(), this.getM());
    }
    public int toMinutes() {
        return this.h * 60 + this.m;
    }
}
