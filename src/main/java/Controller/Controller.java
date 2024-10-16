package Controller;

import Model.*;
import View.*;

/**
 * Die Controller-Klasse steuert die Interaktion zwischen der View und dem Model.
 */
public class Controller {

    private Model model;
    private View view;

    /**
     * Konstruktor, der das Model und die View initialisiert.
     *
     * @param model Das Model, das die Daten enthält
     * @param view Die View, die die Benutzeroberfläche darstellt
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Startet das Training und steuert den Ablauf der Trainingssession.
     */
    public void startTraining() {
        String response = view.getInput("Möchten Sie mit dem letzten Stand fortfahren? (ja/nein)");

        if (response == null || response.trim().equalsIgnoreCase("nein")) {
            model.clearPersistence();
            view.showMessage("Der alte Stand wurde gelöscht. Das Training beginnt neu.");
        } else {
            model.readStatisticsFromJson();
            view.showMessage("Fortsetzen des letzten Stands.");
        }

        boolean trainingActive = true;

        while (trainingActive) {
            Data data = model.getRandomData();
            view.showData(data);

            boolean correctAnswer = false;
            while (!correctAnswer) {
                String userInput = view.getInput();

                if (userInput == null || userInput.trim().isEmpty()) {
                    trainingActive = false;
                    break;
                }

                if (data.checkName(userInput)) {
                    view.showMessage("Richtig!");
                    model.getStatistics().update(true);
                    correctAnswer = true;
                } else {
                    view.showMessage("Falsch! Versuchen Sie es noch einmal.");
                    model.getStatistics().update(false);
                }

                model.addStatisticsToJson();
            }

            if (!trainingActive) {
                break;
            }
        }

        view.displayStatistics(model.readStatisticsFromJson());
        view.showMessage("Training beendet!");
    }

    /**
     * Überprüft die Antwort des Benutzers und aktualisiert die Statistik entsprechend.
     *
     * @param input Die Benutzereingabe, die überprüft wird
     */
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

    /**
     * Beendet das Training und zeigt die finalen Statistiken an.
     */
    public void endTraining() {
        view.displayStatistics(model.readStatisticsFromJson());
        view.showMessage("Training beendet. Statistiken wurden gespeichert.");
    }
}
