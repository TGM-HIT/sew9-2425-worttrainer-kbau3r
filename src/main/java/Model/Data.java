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

    /*
    public boolean checkURL(String url) {
        if (url.contains("//")) {
            return true;
        }
        return false;
    } */

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

    public URL getUrl() {
        return this.url;
    }

}
