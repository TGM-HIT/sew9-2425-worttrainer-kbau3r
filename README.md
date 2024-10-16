# Worttrainer Reloaded

Autor: Kevin Bauer

## Model

  

### Data

  

Hierfür habe ich mich für diese Art der URL Speicherung entschieden, da ich somit auf eine checkURL Klasse verzichten kann und diese gleich bei dem Hinzufügen überprüft wird.

  

```java
private String name;
private URL url;

public Data(String name, String url) {
    this.name = name;
    this.addUrl(url);
}
```

  

Hier wird gleich im Konstruktor die addUrl Methode aufgerufen, sodass eben gleich eine Überprüfung stattfindet.

Man kann eine Url als String hinzufügen, die jedoch gleich in eine java.net.URL übersetzt wird und je nach Exception dann ein Fehler geworfen wird oder nict.

  

```java
public boolean addUrl(String str) {
    URL url;
    try {
        url = new URL(str);
    } catch (MalformedURLException malformedURLException) {
        System.out.println("Wrong URL Format");
        return false;
    }
    this.url = url;
    return true;
}
```

### Model

Für die Persistenz habe ich mich dazu entschieden nur JSONObject zu verwenden und keine fancy library, da ich es so gut wie möglich selber schreiben wollte.

Ich habe mich für das NDJSON datenformat entschieden, da es sich für diesen Anwendungszweck lohnt und effizienter ist, da es einfacher ist

```java
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
```

### Statistics

Die Klasse Statistics dient zur Verfolgung der Benutzerleistungen im Rechtschreibtrainer. Sie hält fest, wie viele Versuche korrekt und wie viele falsch waren.

Beispiel:

```java
    Statistics stats = new Statistics();
    stats.update(true);  // Erhöht correctAttempts auf 1
    stats.update(false); // Erhöht wrongAttempts auf 1
    System.out.println(stats.getStatistics()); // Ausgabe: "Richtig: 1, Falsch: 1"
```

## View

### View

Die View-Klasse stellt die grafische Benutzeroberfläche des Worttrainers dar. Sie verwendet JOptionPane, um Eingaben und Ausgaben durch einfache Dialogfenster zu ermöglichen. Diese Klasse ist zuständig für die Anzeige von Daten, Statistiken sowie für die Eingabe des Benutzers.

Diese Klasse dient als einfache Schnittstelle zur Interaktion mit dem Benutzer und ermöglicht es, Daten anzuzeigen und Benutzereingaben zu erfassen.


## Model

### Model

Die Controller-Klasse steuert die Interaktion zwischen dem Model (Datenlogik) und der View (Benutzeroberfläche). Sie verwaltet den Ablauf des Trainings, die Eingabeüberprüfung und das Speichern von Statistiken.
Konstruktor:

    Controller(Model model, View view)
    Initialisiert den Controller mit dem Model und der View, um die Daten- und Benutzerinteraktionen zu steuern.

Methoden:

    startTraining()
    Startet das Training und zeigt dem Benutzer nacheinander Wörter an. Der Benutzer gibt seine Antwort ein, und die Antwort wird überprüft. Das Training läuft, bis der Benutzer es abbricht. Statistiken werden nach jedem Versuch aktualisiert und am Ende angezeigt.

    checkAnswer(String input)
    Überprüft eine Benutzereingabe gegen das aktuell ausgewählte Wort und zeigt an, ob die Antwort korrekt war. Aktualisiert die Statistiken entsprechend.

    endTraining()
    Beendet die Trainingseinheit und zeigt die finalen Statistiken an. Die Statistiken werden gespeichert.