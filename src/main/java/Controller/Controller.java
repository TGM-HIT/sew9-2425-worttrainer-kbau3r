package Controller;

import Model.*;
import View.*;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void startTraining() {
        view.showMessage("Worttrainer gestartet!");

        boolean trainingActive = true;

        while (trainingActive) {
            Data data = model.getRandomData();
            view.showData(data);

            String userInput = view.getInput();

            if (userInput == null || userInput.trim().isEmpty()) {
                trainingActive = false;
                break;
            }

            if (data.checkName(userInput)) {
                view.showMessage("Richtig!");
                model.getStatistics().update(true);
            } else {
                view.showMessage("Falsch! Das richtige Wort ist: " + data.getName());
                model.getStatistics().update(false);
            }

            model.addStatisticsToJson();
        }

        view.displayStatistics(model.readStatisticsFromJson());
        view.showMessage("Training beendet!");
    }

    public void checkAnswer(String input) {
        Data selectedData = model.getAuswahl();
        if (selectedData.checkName(input)) {
            view.showMessage("Richtig!");
            model.getStatistics().update(true);
        } else {
            view.showMessage("Falsch! Das richtige Wort war: " + selectedData.getName());
            model.getStatistics().update(false);
        }
        model.addStatisticsToJson();
    }

    public void endTraining() {
        view.displayStatistics(model.readStatisticsFromJson());
        view.showMessage("Training beendet. Statistiken wurden gespeichert.");
    }
}
