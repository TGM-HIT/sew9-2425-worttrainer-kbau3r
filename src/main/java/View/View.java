package View;

import Model.*;
import javax.swing.*;
import java.net.URL;
import java.awt.Image;

public class View {

    public void showData(Data data) {
        try {
            // Lade das Bild von der URL
            URL imageUrl = data.getUrl();
            ImageIcon imageIcon = new ImageIcon(imageUrl);

            // Optional: Größe des Bildes anpassen
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH); // Bild skalieren
            imageIcon = new ImageIcon(scaledImage);

            // Zeige das Bild zusammen mit der URL in einem JOptionPane
            JOptionPane.showMessageDialog(null, imageIcon, "Aktuelles Wort", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            // Falls die URL nicht korrekt ist oder das Bild nicht geladen werden kann
            showMessage("Bild konnte nicht geladen werden: " + data.getUrl().toString());
        }
    }

    public String getInput() {
        return JOptionPane.showInputDialog("Bitte das Wort eingeben:");
    }

    public  String getInput(String string) {
        return JOptionPane.showInputDialog(string);
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
