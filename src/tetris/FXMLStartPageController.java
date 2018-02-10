/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ilkay
 */
public class FXMLStartPageController implements Initializable {

    private FXMLDocumentController controller;
    private Timer timer = new Timer();
    @FXML
    public TextField horizontalTxt;
    @FXML
    public TextField verticalTxt;

    public static int vert;
    public static int hor;

    @FXML
    private void handlePlayAction(ActionEvent event) throws IOException {

        vert = Integer.valueOf(verticalTxt.getText());
        hor = Integer.valueOf(horizontalTxt.getText());

        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = fXMLLoader.load();

        //((FXMLDocumentController) fXMLLoader.getController()).SetSize(Integer.parseInt(verticalTxt.getText()), Integer.parseInt(horizontalTxt.getText()), timer);
        Scene scene = new Scene(root);
        //css
        String css = Tetris.class.getResource("cssForButtons.css").toExternalForm();
        scene.getStylesheets().add(css);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        controller = (FXMLDocumentController) fXMLLoader.getController();
        //controller.VERT = Integer.parseInt(verticalTxt.getText());
        //controller.HOR = Integer.parseInt(horizontalTxt.getText());
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controller.keyPressed(event);
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                controller.keyReleased(event);
            }
        });

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
