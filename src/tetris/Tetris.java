/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */
public class Tetris extends Application {

    private FXMLDocumentController controller;

    @Override
    public void start(Stage stage) throws Exception {

        // FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        //  Parent root = fXMLLoader.load();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLStartPage.fxml"));

        Scene scene = new Scene(root);
        //css
        String css = Tetris.class.getResource("cssForButtons.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        stage.setScene(scene);
        stage.show();
        /*
        controller = (FXMLDocumentController) fXMLLoader.getController();
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
         */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
