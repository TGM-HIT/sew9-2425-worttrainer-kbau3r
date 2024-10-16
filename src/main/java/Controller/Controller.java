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
        // Frage den Benutzer, ob er mit dem letzten Stand fortfahren möchte oder nicht
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
                // Fordere den Benutzer zur Eingabe des Wortes auf
                String userInput = view.getInput();

                // Falls die Eingabe null oder leer ist, beende das Training
                if (userInput == null || userInput.trim().isEmpty()) {
                    trainingActive = false;
                    break;
                }

                // Überprüfe, ob die Benutzereingabe korrekt ist
                if (data.checkName(userInput)) {
                    view.showMessage("Richtig!");
                    model.getStatistics().update(true);  // Statistik für richtigen Versuch aktualisieren
                    correctAnswer = true;
                } else {
                    view.showMessage("Falsch! Versuchen Sie es noch einmal.");
                    model.getStatistics().update(false);  // Statistik für falschen Versuch aktualisieren
                }

                // Speichere die aktualisierte Statistik nach jedem Versuch
                model.addStatisticsToJson();
            }

            // Falls das Training abgebrochen wurde, beende die Schleife
            if (!trainingActive) {
                break;
            }
        }

        // Zeige am Ende die Statistiken an
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
