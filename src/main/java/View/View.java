package View;

import Model.*;
import javax.swing.*;
import java.net.URL;
import java.awt.Image;

/**
 * Die View-Klasse steuert die Anzeige und Benutzerinteraktionen in der grafischen Oberfläche.
 */
public class View {

    /**
     * Zeigt das Bild des aktuellen Datenobjekts an.
     *
     * @param data Das anzuzeigende Datenobjekt
     */
    public void showData(Data data) {
        try {
            URL imageUrl = data.getUrl();
            ImageIcon imageIcon = new ImageIcon(imageUrl);

            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(scaledImage);

            JOptionPane.showMessageDialog(null, imageIcon, "Aktuelles Wort", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            showMessage("Bild konnte nicht geladen werden: " + data.getUrl().toString());
        }
    }

    /**
     * Fordert den Benutzer auf, ein Wort einzugeben.
     *
     * @return Die Benutzereingabe als String
     */
    public String getInput() {
        return JOptionPane.showInputDialog("Bitte das Wort eingeben:");
    }

    /**
     * Fordert den Benutzer mit einer benutzerdefinierten Nachricht auf, ein Wort einzugeben.
     *
     * @param string Die Nachricht, die den Benutzer zur Eingabe auffordert
     * @return Die Benutzereingabe als String
     */
    public  String getInput(String string) {
        return JOptionPane.showInputDialog(string);
    }

    /**
     * Zeigt die Statistik der richtigen und falschen Versuche an.
     *
     * @param statistics Die anzuzeigende Statistik
     */
    public void displayStatistics(Statistics statistics) {
        String message = "Richtige Versuche: " + statistics.getCorrectAttempts() +
                "\nFalsche Versuche: " + statistics.getWrongAttempts();
        JOptionPane.showMessageDialog(null, message, "Statistik", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Zeigt eine Informationsnachricht an.
     *
     * @param message Die anzuzeigende Nachricht
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Zeigt eine Fehlermeldung an.
     *
     * @param error Die anzuzeigende Fehlermeldung
     */
    public void displayError(String error) {
        JOptionPane.showMessageDialog(null, error, "Fehler", JOptionPane.ERROR_MESSAGE);
    }
}
