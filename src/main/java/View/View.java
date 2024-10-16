package View;

import Model.*;
import javax.swing.JOptionPane;

public class View {

    public void showData(Data data) {
        String message = "Wort: " + data.getName() + "\nURL: " + data.getUrl().toString();
        JOptionPane.showMessageDialog(null, message, "Aktuelles Wort", JOptionPane.INFORMATION_MESSAGE);
    }

    public String getInput() {
        return JOptionPane.showInputDialog("Bitte das Wort eingeben:");
    }

    public void displayStatistics(Statistics statistics) {
        String message = "Richtige Versuche: " + statistics.getCorrectAttempts() +
                "\nFalsche Versuche: " + statistics.getWrongAttempts();
        JOptionPane.showMessageDialog(null, message, "Statistik", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayError(String error) {
        JOptionPane.showMessageDialog(null, error, "Fehler", JOptionPane.ERROR_MESSAGE);
    }
}
