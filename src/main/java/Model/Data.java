package Model;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Die Data-Klasse repräsentiert ein Wort- und URL-Paar.
 * @author Kevin Bauer
 * @version 16-10-2024
 */
public class Data {
    private String name;
    private URL url;

    /**
     * Konstruktor, der den Namen und die URL setzt.
     *
     * @param name Das Wort, das mit der URL verbunden ist
     * @param url Die URL, die das Bild des Wortes referenziert
     */
    public Data(String name, String url) {
        this.name = name;
        this.addUrl(url);
    }

    /**
     * Setzt den Namen.
     *
     * @param name Das zu setzende Wort
     */
    public void addName(String name) {
        this.name = name;
    }

    /**
     * Überprüft, ob der gegebene Name mit dem gespeicherten Namen übereinstimmt.
     *
     * @param name Der zu überprüfende Name
     * @return true, wenn die Namen übereinstimmen, sonst false
     */
    public boolean checkName(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    /**
     * Fügt die URL hinzu und validiert sie.
     *
     * @param str Die zu setzende URL als String
     * @return true, wenn die URL gültig ist, sonst false
     */
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

    /**
     * Überprüft, ob die gegebene URL gültig ist.
     *
     * @param str Die zu überprüfende URL als String
     * @return true, wenn die URL gültig ist, sonst false
     */
    public boolean checkUrl(String str) {
        try {
            new URL(str);
        } catch (MalformedURLException malformedURLException) {
            System.out.println("Wrong URL Format");
            return false;
        }
        return true;
    }

    /**
     * Setzt die URL.
     *
     * @param url Die zu setzende URL
     */
    public void addUrl(URL url) {
        this.url = url;
    }

    /**
     * Gibt die URL zurück.
     *
     * @return Die gespeicherte URL
     */
    public URL getUrl() {
        return this.url;
    }

    /**
     * Gibt den Namen zurück.
     *
     * @return Der gespeicherte Name
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "{name='" + name + "', url='" + url + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return name.equals(data.name) && url.equals(data.url);
    }
}
