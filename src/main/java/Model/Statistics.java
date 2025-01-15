package Model;

/**
 * Die Statistics-Klasse verwaltet die Anzahl der richtigen und falschen Versuche.
 * @author Kevin Bauer
 * @version 16-10-2024
 */
public class Statistics {
    private int correctAttempts;
    private int wrongAttempts;
    private boolean[] allAnimal = {false, false, false};

    /**
     * Konstruktor, der die Statistik mit 0 richtigen und falschen Versuchen initialisiert.
     */
    public Statistics() {
        this.correctAttempts = 0;
        this.wrongAttempts = 0;
    }

    /**
     * Konstruktor, der die Statistik mit einer bestimmten Anzahl von richtigen und falschen Versuchen initialisiert.
     *
     * @param correctAttempts Anzahl der richtigen Versuche
     * @param wrongAttempts Anzahl der falschen Versuche
     */
    public Statistics(int correctAttempts, int wrongAttempts) {
        this.correctAttempts = correctAttempts;
        this.wrongAttempts = wrongAttempts;
    }

    /**
     * Gibt die Anzahl der richtigen Versuche zurück.
     *
     * @return Anzahl der richtigen Versuche
     */
    public int getCorrectAttempts() {
        return correctAttempts;
    }

    /**
     * Gibt die Anzahl der falschen Versuche zurück.
     *
     * @return Anzahl der falschen Versuche
     */
    public int getWrongAttempts() {
        return wrongAttempts;
    }

    /**
     * Aktualisiert die Statistik basierend darauf, ob der Versuch korrekt war.
     *
     * @param isCorrect true, wenn der Versuch korrekt war, sonst false
     */
    public void update(boolean isCorrect) {
        if (isCorrect) {
            correctAttempts++;

        } else {
            wrongAttempts++;
        }
    }

    /**
     * Aktualisiert die Statistik basierend darauf, ob der Versuch korrekt war.
     *
     * @param isCorrect true, wenn der Versuch korrekt war, sonst false
     */
    public void update(boolean isCorrect, String userInput) {
        if (isCorrect) {
            correctAttempts++;
            if(userInput.equalsIgnoreCase("hund")) {
                this.allAnimal[0] = true;
            } else if (userInput.equalsIgnoreCase("katze")) {
                this.allAnimal[1] = true;
            } else {
                this.allAnimal[2] = true;
            }
        } else {
            wrongAttempts++;
        }
    }

    /**
     * Gibt eine formatierte Zeichenkette mit der Anzahl der richtigen und falschen Versuche zurück.
     *
     * @return Formatierte Statistik als String
     */
    public String getStatistics() {
        return "Richtig: " + correctAttempts + ", Falsch: " + wrongAttempts;
    }

    public boolean allAnimalCheck() {
        for(boolean bool : this.allAnimal) {
            if(bool==false) {
                return false;
            }
        }
        return true;
    }
}