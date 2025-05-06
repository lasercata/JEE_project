package shows;

import java.util.ArrayList;

import time.EventTime;

public class Character {
    private int id;
    private String name;
    private ArrayList<EventTime> schedule;

    public Character(int id, String name, ArrayList<EventTime> schedule) {
        this.id = id;
        this.name = name;
        this.schedule = schedule;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<EventTime> getSchedule() {
        return this.schedule;
    }

    public void setSchedule(ArrayList<EventTime> schedule) {
        this.schedule = schedule;
    }

    /**
     * Calculates a string containing the SQL fields.
     */
    public static String sqlFields() {
        return "id, name";
    }

    /**
     * Returns the name of the associated sql table
     */
    public static String getTblName() {
        return "characters";
    }

    /**
     * Calculates a string containing all the attributes of the class, separated with commas.
     * It's useful to add the class into the SQL database.
     */
    public String toSQL() {
        return id + ", '" + name + "',";
    }

    @Override
    public String toString() {
        return this.name;
    }

    // =================== Other methods ====================

    public void addCharacterToEvent(Show show) {
        EventTime showTime = show.getSchedule();

        // Checking if the character is available
        int showStart = showTime.getTime().getOpening().toMinutes();
        int showEnd = showTime.getTime().getEnding().toMinutes();

        for (EventTime eventTime : this.schedule) {
            if (eventTime.getDay().equals(showTime.getDay())) {
                int eventStart = eventTime.getTime().getOpening().toMinutes();
                int eventEnd = eventTime.getTime().getEnding().toMinutes();

                if ((eventStart >= showStart && eventStart <= showEnd)
                        || (eventEnd >= showStart && eventEnd <= showEnd)
                        || (eventStart <= showStart && eventEnd >= showEnd)
                        || (showStart - eventEnd <= 30)
                        || (eventStart - showEnd <= 30)) {
                    System.out.println(
                            "Le personnage " + this.name + " est déjà occupé pendant le spectacle "
                                    + show.getTitre() + " ayant lieu " + show.getSchedule().toString() + ".\n");
                    return;
                }
            }
        }

        // If the character is available for all instances of the show, we add them all to their schedule
        this.schedule.add(showTime);
        show.addCharacter(this);
    }

    public int getActivityPerWeek() {
        int totalMinutes = 0;
        for (EventTime eventTime : this.schedule) {
            totalMinutes += eventTime.getTime().getDuration();
        }
        return totalMinutes;
    }
}
