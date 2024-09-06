/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randompasswordgenerator;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author ANUJ KUMAR
 */
public class RandomPasswordGeneratorController {

    @FXML
    private TextField tf;
    @FXML
    private Label lengthLabel;
    @FXML
    private JFXSlider slideBar;
    @FXML
    private FontAwesomeIconView copyIcon;
    @FXML
    private JFXToggleButton lcTgBtn;
    @FXML
    private JFXToggleButton ucTgBtn;
    @FXML
    private JFXToggleButton numTgBtn;
    @FXML
    private JFXToggleButton scTgBtn;

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase();
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+{}[]";

    @FXML
    private void copyPass(ActionEvent event) {
        String str = tf.getText();
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection strse1 = new StringSelection(str);
        clip.setContents(strse1, strse1);
        copyIcon.setGlyphName("CHECK");

    }

    @FXML
    private void slideBarMove(MouseEvent event) {
        lengthLabel.setText("" + Math.round(slideBar.getValue()));
    }

    @FXML
    private void genPass(ActionEvent event) {
        tf.clear();
        int length = Integer.parseInt(lengthLabel.getText());
        tf.setText(generateRandomPass(length));
    }

    public String generateRandomPass(int length) {
        String generatedPassword = "";
        String allChars = "";
        // At least one uppercase letter
        if (ucTgBtn.selectedProperty().getValue())
        {
            allChars = allChars.concat(UPPER);
        }
        // At least one lowercase letter
        if (lcTgBtn.selectedProperty().getValue())
        {
            allChars = allChars.concat(LOWER);
        }
        // At least one digit
        if (numTgBtn.selectedProperty().getValue())
        {
            allChars = allChars.concat(DIGITS);
        }
        // At least one special character
        if (scTgBtn.selectedProperty().getValue())
        {
            allChars = allChars.concat(SPECIAL_CHARS);
        }

        if (allChars == "" || allChars.length() == 0)
        {
            return generatedPassword;
        }

        int i = 1;
        while (i <= length)
        {
            generatedPassword += allChars.charAt((int) Math.round(Math.random() * allChars.length()/2));
            i++;
        }

        return generatedPassword;
    }

}
