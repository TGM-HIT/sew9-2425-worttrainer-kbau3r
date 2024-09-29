package Model;

import java.net.MalformedURLException;
import java.net.URL;

public class Data {
    private String name;
    private URL url;

    public Data(String name, String url) {
        this.name = name;
        this.addUrl(url);
    }

    public void addName(String name) {
        this.name = name;
    }

    public boolean checkName(String name) {
        if(this.name.equalsIgnoreCase(name)) {
            return true;
        }
        return false;
    }

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

    // nur sicherheitshalber gemacht, wird wahrscheinlich nicht verwendet
    public boolean checkUrl(String str) {
        try {
            new URL(str);
        } catch (MalformedURLException malformedURLException) {
            System.out.println("Wrong URL Format");
            return false;
        }
        return true;
    }
    
    public void addUrl(URL url) {
        this.url = url;
    }

    public URL getUrl() {
        return this.url;
    }

    // new
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
