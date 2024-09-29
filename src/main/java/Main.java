import Controller.Controller;
import Model.Model;
import View.View;
import Model.Data;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Data> list = new ArrayList<Data>();
        View view = new View();
        Model model = new Model(list);
        Controller controller = new Controller(model, view);
        System.out.println("Hello, Gradle!");

        Data data1 = new Data("Example 1", "http://example.com/1");
        Data data2 = new Data("Example 2", "http://example.com/2");

        List<Data> dataList = new ArrayList<>();
        dataList.add(data1);
        dataList.add(data2);

        model = new Model(dataList); // verwendet den test.ndjson

        Data newData = new Data("Example 3", "http://example.com/3");
        model.addData(newData);
        List<Data> dataList2 = model.readDataFromJson();
        System.out.println(dataList2);
        System.out.println(model.getDataList());
        System.out.println(newData);
        System.out.println(dataList2.contains(newData));
    }
}
