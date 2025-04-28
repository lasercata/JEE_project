package restaurants;

public enum Cuisine {
    FRENCH("Française"),
    ITALIAN("Italienne"), 
    CHINESE("Chinoise"),
    AMERICAN("Américaine"), 
    MEXICAN("Mexicaine"),
    GREEK("Grecque"),
    INDIAN("Indienne"),
    JAPANESE("Japonaise"),
    THAI("Thaïlandaise"),
    SPANISH("Espagnole"),
    MOROCCAN("Marocaine");

    private final String label;

    private Cuisine(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
