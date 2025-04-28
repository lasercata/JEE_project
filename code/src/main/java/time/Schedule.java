package time;

public class Schedule {
    private HourRange[] schedule = new HourRange[7];

    public Schedule(HourRange monday, HourRange tuesday, HourRange wednesday, HourRange thursday, HourRange friday, HourRange saturday, HourRange sunday) {
        this.schedule[0] = monday;
        this.schedule[1] = tuesday;
        this.schedule[2] = wednesday;
        this.schedule[3] = thursday;
        this.schedule[4] = friday;
        this.schedule[5] = saturday;
        this.schedule[6] = sunday;
    }

    public HourRange[] getSchedule() {
        return this.schedule;
    }

    public void setSchedule(HourRange[] schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        String sch = "";

        String days[] = {"lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche"};

        for (int i = 0; i < 7; i++) {
            sch += String.format("%s le %s\n", this.schedule[i], days[i]);
        }
        
        return sch;
    }
}