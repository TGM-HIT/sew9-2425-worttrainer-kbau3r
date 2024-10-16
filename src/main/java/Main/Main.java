package Main;

import Model.*;
import View.*;
import Controller.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data("Hund", "http://example.com/hund.jpg"));
        dataList.add(new Data("Katze", "http://example.com/katze.jpg"));
        dataList.add(new Data("Vogel", "http://example.com/vogel.jpg"));

        Model model = new Model(dataList);
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.startTraining();
        controller.endTraining();
    }
}
