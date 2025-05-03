package statistics;
import java.util.List;
import java.util.Map;

import characters.Show;
import characters.Character;
import time.EventTime;


public class Statistics {

    private List<Show> allShows;
    private List<Character> allCharacters;


    public Statistics(List<Show> shows, List<Character> characters) {
        this.allShows = shows;
        this.allCharacters = characters;
    }
//
//    public Map<String, List<Show>> computeSortedShowsPerDay() {
//        // compute and store in sortedShowsPerDay
//    }
//
//    public Map<Character, Integer> computeCharacterActivity() {
//        // compute and store in characterActivityMap
//    }

    public void getShowsByDuration() {
        // Trie la liste allShows par durée totale croissante
        for (int i = 0; i < allShows.size(); i++) {
            for (int j = i + 1; j < allShows.size(); j++) {
                int dureeI = 0;
                int dureeJ = 0;

                for (EventTime evt : allShows.get(i).getHoraires()) { // somme la duree du show a la position i
                    dureeI += evt.getDureeMinutes();
                }
                for (EventTime evt : allShows.get(j).getHoraires()) { // somme la duree du show a la position j
                    dureeJ += evt.getDureeMinutes();
                }

                if (dureeJ < dureeI) {
                    Show temp = allShows.get(i);
                 // on echange le show a la position i et celui a la position j
                    allShows.set(i, allShows.get(j)); 
                    allShows.set(j, temp); 
                }
            }
        }

        // Affichage des shows triés
        System.out.println("Spectacles triés par durée croissante :");
        for (int i = 0; i < allShows.size(); i++) {
            Show show = allShows.get(i);
            int totalDuree = 0;
            for (EventTime et : show.getHoraires()) {
                totalDuree += et.getDureeMinutes();
            }
            System.out.println("- " + show.getTitre() + " : " + totalDuree + " minutes");
        }
    }
    
    /**
     * lister le top 5 des personnages selon le temps d'activites par heure
     */
    public void listTopFiveCharacters() {
        for (int i = 0; i < allCharacters.size(); i++) {
            for (int j = i + 1; j < allCharacters.size(); j++) {
                if (allCharacters.get(j).getActivityPerWeek() > allCharacters.get(i).getActivityPerWeek()) {
                    // Échange
                    Character temp = allCharacters.get(i);
                    allCharacters.set(i, allCharacters.get(j));
                    allCharacters.set(j, temp);
                }
            }
        }

        // afficher les personnages et leur temps d'activites en heure
        System.out.println("Top 5 des personnages les plus actifs:");
        int count = Math.min(5, allCharacters.size());

        for (int i = 0; i < count; i++) {
            Character c = allCharacters.get(i);
            System.out.println((i + 1) + ". " + c.getName() + " - " + c.getActivityPerWeek() + " minutes");
        }
    }


}
