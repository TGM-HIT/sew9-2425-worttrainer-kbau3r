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
    }
}
