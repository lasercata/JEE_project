package time;

import java.util.ArrayList;

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
    
    public Schedule(ArrayList<String> openingHours, ArrayList<String> closingHours){
        for (int i = 0; i < 7; i++){
            String[] openingHourParts = openingHours.get(i).split(":");
            Hour openingHour = new Hour(Integer.parseInt(openingHourParts[0]),
                    Integer.parseInt(openingHourParts[1]));

            String[] closingHourParts = closingHours.get(i).split(":");
            Hour closingHour = new Hour(Integer.parseInt(closingHourParts[0]),
                    Integer.parseInt(closingHourParts[1]));

            this.schedule[i] = new HourRange(openingHour,closingHour);
        }
    }

    public HourRange[] getSchedule() {
        return this.schedule;
    }

    public void setSchedule(HourRange[] schedule) {
        this.schedule = schedule;
    }
    
    public String toSQL() {
        String res = "";
        for (int i = 0; i < 6; i++) {
            res += schedule[i].toSQL() + ",";
        }
        res += schedule[6].toSQL();

        return res;
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
