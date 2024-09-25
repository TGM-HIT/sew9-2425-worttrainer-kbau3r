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