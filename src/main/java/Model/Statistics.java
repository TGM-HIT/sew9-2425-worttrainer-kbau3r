package Model;

/**
 * Die Statistics-Klasse verwaltet die Anzahl der richtigen und falschen Versuche.
 */
public class Statistics {
    private int correctAttempts;
    private int wrongAttempts;

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
     * Gibt eine formatierte Zeichenkette mit der Anzahl der richtigen und falschen Versuche zurück.
     *
     * @return Formatierte Statistik als String
     */
    public String getStatistics() {
        return "Richtig: " + correctAttempts + ", Falsch: " + wrongAttempts;
    }
}
