package Model;


import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import org.json.JSONObject;
import java.io.*;

public class Model {
    private List<Data> dataList;
    private Data selection;
    private String path, path2;
    private Statistics statistics;

    public Model(List<Data> data) {
        this.dataList = data;
        this.statistics = new Statistics();
        this.path = "test.ndjson";
        this.path2 = "stats.ndjson";
        for (Data dataEntry : this.dataList) {
            addDataToJson(dataEntry);
        }
    }

    public List<Data> getDataList() {
        return this.dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
        for (Data dataEntry : this.dataList) {
            addDataToJson(dataEntry);
        }
    }

    public Data getRandomData() {
        this.selection = this.dataList.get((int) (Math.random() * this.dataList.size()));
        return this.selection;
    }

    public Data getSpecificData(int i) {
        this.selection = this.dataList.get(i);
        return this.selection;
    }

    public void addData(Data data) {
        addDataToJson(data);
        dataList.add(data);
    }

    public void removeData(Data data) {
        dataList.remove(data);
    }

    public Data getAuswahl() {
        return this.selection;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public void addDataToJson(Data data){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", data.getName());
        jsonObject.put("url", data.getUrl().toString());

        try (FileWriter fileWriter = new FileWriter(path, true)) { // true, da es an Zeile anfügt ~kbauer
            fileWriter.write(jsonObject.toString() + System.lineSeparator());
            System.out.println("Data has been written to the file: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public boolean compareDatalist(List<Data> dataList2) {
        return this.dataList.equals(dataList2);
    }

    public void clearPersistence() {
        try (FileWriter fileWriter = new FileWriter(path, false)) { // 'false' für überschreiben
            // durch das überschreiben, wird das file gecleart
            System.out.println("Persistence has been cleared: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addStatisticsToJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("correct", this.statistics.getCorrectAttempts());
        jsonObject.put("incorrect", this.statistics.getWrongAttempts());

        try (FileWriter fileWriter = new FileWriter(path2, true)) { // true, da es an Zeile anfügt ~kbauer
            fileWriter.write(jsonObject.toString() + System.lineSeparator());
            System.out.println("Statistics has been written to the file: " + path2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Statistics readStatisticsFromJson() {
        File file = new File(this.path2); // stats.ndjson file

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
                    lastLine = currentLine; // update, until the last line
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
                return null;
            }
        }

        return null;
    }

}
