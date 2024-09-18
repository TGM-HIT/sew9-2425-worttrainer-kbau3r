package Model;

import java.util.List;

public class Model {
    private List<Data> dataList;
    public Model(List<Data> data) {
        this.dataList = data;
    }

    public List<Data> getDataList() {
        return this.dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public void addData(Data data) {
        dataList.add(data);
    }

    public void removeData(Data data) {
        dataList.remove(data);
    }



}
