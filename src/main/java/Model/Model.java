package Model;


import java.util.List;

public class Model {
    private List<Data> dataList;
    private Data auswahl;
    public Model(List<Data> data) {
        this.dataList = data;
    }

    public List<Data> getDataList() {
        return this.dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public Data getRandomData() {
        this.auswahl = this.dataList.get((int) (Math.random() * this.dataList.size()));
        return this.auswahl;
    }

    public Data getSpecificData(int i) {
        this.auswahl = this.dataList.get(i);
        return this.auswahl;
    }

    public void addData(Data data) {
        dataList.add(data);
    }

    public void removeData(Data data) {
        dataList.remove(data);
    }

}
