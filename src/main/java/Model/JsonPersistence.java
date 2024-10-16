package Model;

import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Die JsonPersistenceStrategy implementiert das PersistenceStrategy-Interface und bietet Methoden zum Speichern
 * und Laden von Daten und Statistiken im JSON-Format.
 * @author Kevin Bauer
 * @version 16-10-2024
 */
public class JsonPersistence implements PersistenceInterface {

    /**
     * Speichert eine Liste von Daten in einer Datei im JSON-Format.
     *
     * @param dataList Die Liste der zu speichernden Daten
     * @param path Der Pfad zur Datei, in der die Daten gespeichert werden sollen
     */
    @Override
    public void saveData(List<Data> dataList, String path) {
        try (FileWriter fileWriter = new FileWriter(path, false)) {
            for (Data data : dataList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", data.getName());
                jsonObject.put("url", data.getUrl().toString());
                fileWriter.write(jsonObject.toString() + System.lineSeparator());
            }
            System.out.println("Data has been written to the file: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lädt eine Liste von Daten aus einer Datei im JSON-Format.
     *
     * @param path Der Pfad zur Datei, aus der die Daten geladen werden sollen
     * @return Die Liste der geladenen Daten
     */
    @Override
    public List<Data> loadData(String path) {
        List<Data> dataList = new ArrayList<>();
        File file = new File(path);

        if (!file.exists()) {
            System.out.println("Path isn't working. Re-update it, please.");
            return dataList;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                currentLine = currentLine.trim();
                if (!currentLine.isEmpty()) {
                    JSONObject jsonObject = new JSONObject(currentLine);
                    dataList.add(new Data(jsonObject.getString("name"), jsonObject.getString("url")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;
    }

    /**
     * Speichert die Statistiken in einer Datei im JSON-Format.
     *
     * @param statistics Die zu speichernden Statistiken
     * @param path Der Pfad zur Datei, in der die Statistiken gespeichert werden sollen
     */
    @Override
    public void saveStatistics(Statistics statistics, String path) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("correct", statistics.getCorrectAttempts());
        jsonObject.put("incorrect", statistics.getWrongAttempts());

        try (FileWriter fileWriter = new FileWriter(path, true)) {
            fileWriter.write(jsonObject.toString() + System.lineSeparator());
            System.out.println("Statistics have been written to the file: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lädt die Statistiken aus einer Datei im JSON-Format.
     *
     * @param path Der Pfad zur Datei, aus der die Statistiken geladen werden sollen
     * @return Die geladenen Statistiken
     */
    @Override
    public Statistics loadStatistics(String path) {
        File file = new File(path);

        if (!file.exists()) {
            System.out.println("Statistics file does not exist.");
            return null;
        }

        String lastLine = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                currentLine = currentLine.trim();
                if (!currentLine.isEmpty()) {
                    lastLine = currentLine;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (lastLine != null) {
            try {
                JSONObject jsonObject = new JSONObject(lastLine);
                int correctAttempts = jsonObject.getInt("correct");
                int incorrectAttempts = jsonObject.getInt("incorrect");
                return new Statistics(correctAttempts, incorrectAttempts);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * Löscht den Inhalt der Datei.
     *
     * @param path Der Pfad zur Datei, deren Inhalt gelöscht werden soll
     */
    @Override
    public void clear(String path) {
        try (FileWriter fileWriter = new FileWriter(path, false)) {
            System.out.println("Data has been cleared: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}