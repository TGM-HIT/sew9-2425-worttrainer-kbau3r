package Model;

import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import org.json.JSONObject;
import java.io.*;

/**
 * Die Model-Klasse verwaltet die Daten und die Persistenz für den Worttrainer.
 * @author Kevin Bauer
 * @version 16-10-2024
 */
public class Model {
    private List<Data> dataList;
    private Data selection;
    private String path, path2;
    private Statistics statistics;

    /**
     * Konstruktor, der die Liste der Daten und die Statistiken initialisiert.
     *
     * @param data Die Liste der Wort-Bild-Paare
     */
    public Model(List<Data> data) {
        this.dataList = data;
        this.statistics = new Statistics();
        this.path = "test.ndjson";
        this.path2 = "stats.ndjson";
        for (Data dataEntry : this.dataList) {
            addDataToJson(dataEntry);
        }
    }

    /**
     * Gibt die Liste der Daten zurück.
     *
     * @return Liste der Daten
     */
    public List<Data> getDataList() {
        return this.dataList;
    }

    /**
     * Setzt die Liste der Daten und speichert sie in der JSON-Datei.
     *
     * @param dataList Die zu setzende Liste der Daten
     */
    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
        for (Data dataEntry : this.dataList) {
            addDataToJson(dataEntry);
        }
    }

    /**
     * Wählt zufällig ein Wort-Bild-Paar aus.
     *
     * @return Ein zufällig ausgewähltes Wort-Bild-Paar
     */
    public Data getRandomData() {
        this.selection = this.dataList.get((int) (Math.random() * this.dataList.size()));
        return this.selection;
    }

    /**
     * Gibt das spezifische Wort-Bild-Paar anhand des Indexes zurück.
     *
     * @param i Der Index des ausgewählten Wort-Bild-Paars
     * @return Das spezifisch ausgewählte Wort-Bild-Paar
     */
    public Data getSpecificData(int i) {
        this.selection = this.dataList.get(i);
        return this.selection;
    }

    /**
     * Fügt ein neues Wort-Bild-Paar hinzu und speichert es in der JSON-Datei.
     *
     * @param data Das hinzuzufügende Wort-Bild-Paar
     */
    public void addData(Data data) {
        addDataToJson(data);
        dataList.add(data);
    }

    /**
     * Entfernt ein Wort-Bild-Paar aus der Liste.
     *
     * @param data Das zu entfernende Wort-Bild-Paar
     */
    public void removeData(Data data) {
        dataList.remove(data);
    }

    /**
     * Gibt das ausgewählte Wort-Bild-Paar zurück.
     *
     * @return Das aktuell ausgewählte Wort-Bild-Paar
     */
    public Data getAuswahl() {
        return this.selection;
    }

    /**
     * Setzt den Pfad zur JSON-Datei.
     *
     * @param path Der Dateipfad
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Gibt den Pfad zur JSON-Datei zurück.
     *
     * @return Der Dateipfad
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Speichert das Wort-Bild-Paar in der JSON-Datei.
     *
     * @param data Das zu speichernde Wort-Bild-Paar
     */
    public void addDataToJson(Data data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", data.getName());
        jsonObject.put("url", data.getUrl().toString());

        try (FileWriter fileWriter = new FileWriter(path, true)) {
            fileWriter.write(jsonObject.toString() + System.lineSeparator());
            System.out.println("Data has been written to the file: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Liest die Daten aus der JSON-Datei.
     *
     * @return Liste der gelesenen Daten
     */
    public List<Data> readDataFromJson() {
        List<Data> dataList = new ArrayList<Data>();
        File file = new File(this.path);

        if (!file.exists()) {
            System.out.println("Path isnt working. Reupdate it please");
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
     * Vergleicht zwei Listen von Daten.
     *
     * @param dataList2 Die Liste, die mit der aktuellen Liste verglichen werden soll
     * @return true, wenn die Listen übereinstimmen, sonst false
     */
    public boolean compareDatalist(List<Data> dataList2) {
        return this.dataList.equals(dataList2);
    }

    /**
     * Löscht die persistierten Daten und Statistiken.
     */
    public void clearPersistence() {
        try (FileWriter fileWriter = new FileWriter(path, false)) {
            System.out.println("Persistence has been cleared: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter fileWriter = new FileWriter(path2, false)) {
            System.out.println("Persistence has been cleared: " + path2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Speichert die aktuellen Statistiken in der JSON-Datei.
     */
    public void addStatisticsToJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("correct", this.statistics.getCorrectAttempts());
        jsonObject.put("incorrect", this.statistics.getWrongAttempts());

        try (FileWriter fileWriter = new FileWriter(path2, true)) {
            fileWriter.write(jsonObject.toString() + System.lineSeparator());
            System.out.println("Statistics has been written to the file: " + path2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Liest die Statistiken aus der JSON-Datei und gibt sie zurück.
     *
     * @return Die gelesenen Statistiken
     */
    public Statistics readStatisticsFromJson() {
        File file = new File(this.path2);

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
                this.statistics = new Statistics(correctAttempts, incorrectAttempts);
                return new Statistics(correctAttempts, incorrectAttempts);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    /**
     * Gibt die aktuellen Statistiken zurück.
     *
     * @return Die gespeicherten Statistiken
     */
    public Statistics getStatistics() {
        return this.statistics;
    }

}
