package Model;

public class Data {
    private String name;
    private String url;

    public Data(String name, String url) {
        this.url = url;
        this.name = name;
    }


    public boolean checkURL(String url) {
        if(url.contains("//")) {
            return true;
        }
        return false;
    }
}
