package characters;

import java.util.ArrayList;


import time.EventTime;

public class Character {
    private String name;
    private ArrayList<EventTime> schedule;

    public Character(String name, ArrayList<EventTime> schedule) {
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

    // =================== Other methods ====================

    public void addCharacterToEvent(Show show) {
        ArrayList<EventTime> showTimes = show.getHoraires();
        // Checking if the character is available
        for (EventTime showInstance : showTimes) {
            int showStart = showInstance.getTime().getOpening().toMinutes();
            int showEnd = showInstance.getTime().getEnding().toMinutes();
            for (EventTime eventTime : this.schedule) {
                if (eventTime.getDay().equals(showInstance.getDay())) {
                    int eventStart = eventTime.getTime().getOpening().toMinutes();
                    int eventEnd = eventTime.getTime().getEnding().toMinutes();
                    if ((eventStart >= showStart && eventStart <= showEnd)
                            || (eventEnd >= showStart && eventEnd <= showEnd)
                            || (eventStart <= showStart && eventEnd >= showEnd)
                            || (showStart - eventEnd <= 30)
                            || (eventStart - showEnd <= 30)) {
                        System.out.println(
                                "Le personnage " + this.name + " est déjà occupé pendant le spectacle "
                                        + show.getTitre() + " ayant lieu " + show.getHoraires().toString() + ".\n");
                        return;
                    }
                }
            }
            // If the character is available for all instances of the show, we add them all to their schedule
            for (EventTime validShowInstances : showTimes) {
                this.schedule.add(validShowInstances);
            }
            show.addCharacter(this);
        }
    }

    public int getActivityPerWeek() {
        int totalMinutes = 0;
        for (EventTime eventTime : this.schedule) {
            totalMinutes += eventTime.getTime().getDuration();
        }
        return totalMinutes;
    }
}