package Main;

import Model.*;
import View.*;
import Controller.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Main-Klasse startet die Worttrainer-Anwendung.
 * @author Kevin Bauer
 * @version 16-10-2024
 */
public class Main {

    /**
     * Der Einstiegspunkt der Anwendung.
     *
     * @param args Kommandozeilenargumente
     */
    public static void main(String[] args) {
        List<Data> dataList = new ArrayList<>();
        dataList.add(new Data("Hund", "https://www.naturavetal.at/media/cache/images/category_header/05-infoseiten/04-ratgeber/heller-hund-im-wald-qf.jpg"));
        dataList.add(new Data("Katze", "https://image.geo.de/34423086/t/u8/v1/w1440/r0/-/katze-as-97589769.jpg"));
        dataList.add(new Data("Vogel", "https://www.fr.de/assets/images/27/530/27530574-wiedehopf-ist-vogel-des-jahres-2022-1K70.jpg"));

        Model model = new Model(dataList);
        View view = new View();
        Controller controller = new Controller(model, view);

        controller.startTraining();
        controller.endTraining();
    }
}
