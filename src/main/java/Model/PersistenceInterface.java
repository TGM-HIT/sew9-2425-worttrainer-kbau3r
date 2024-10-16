package Model;

import java.util.List;

/**
 * Das PersistenceStrategy-Interface definiert die Methoden, die jede Persistenzstrategie implementieren muss,
 * um Daten und Statistiken zu speichern, zu laden und zu löschen.
 */
public interface PersistenceInterface {

    /**
     * Speichert eine Liste von Daten in einer Datei.
     *
     * @param dataList Die Liste der zu speichernden Daten
     * @param path Der Pfad zur Datei, in der die Daten gespeichert werden sollen
     */
    void saveData(List<Data> dataList, String path);

    /**
     * Lädt eine Liste von Daten aus einer Datei.
     *
     * @param path Der Pfad zur Datei, aus der die Daten geladen werden sollen
     * @return Die Liste der geladenen Daten
     */
    List<Data> loadData(String path);

    /**
     * Speichert die Statistiken in einer Datei.
     *
     * @param statistics Die zu speichernden Statistiken
     * @param path Der Pfad zur Datei, in der die Statistiken gespeichert werden sollen
     */
    void saveStatistics(Statistics statistics, String path);

    /**
     * Lädt die Statistiken aus einer Datei.
     *
     * @param path Der Pfad zur Datei, aus der die Statistiken geladen werden sollen
     * @return Die geladenen Statistiken
     */
    Statistics loadStatistics(String path);

    /**
     * Löscht den Inhalt der Datei.
     *
     * @param path Der Pfad zur Datei, deren Inhalt gelöscht werden soll
     */
    void clear(String path);
}
