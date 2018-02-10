/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author ilkay
 */
public class FXMLDocumentController implements Initializable {

    TetrisObject currentObj = null;
    TetrisObject tempObj = null;
    SoundPlayer soundPlayer = new SoundPlayer("resources/tetris.mp3");

    Timer timer;
    public int VERT = FXMLStartPageController.vert;
    public int HOR = FXMLStartPageController.hor;
    private int timerSpeed = 600;
    private int ratio = 20;
    private int nextType;
    private Map map;
    private Random random = new Random();
    private boolean start = true;

    @FXML
    private Label gameOverLabel;
    @FXML
    private Label jokerOnDuty;

    @FXML
    private Label scoreLabel;

    @FXML
    private TilePane tilePane;
    @FXML
    private TilePane nextObjPane;

    @FXML
    private void handleRetryAction(ActionEvent event) throws IOException {
        map.cleanMap();
        timerSpeed = 600;
        gameOverLabel.setVisible(false);
        currentObj = null;
    }

    @FXML
    private void handleExitAction(ActionEvent event) {
        System.exit(1);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //creating game window with given parameters
        if (HOR <= 10 && VERT <= 21) {
            ratio = 20;
        }
        tilePane.setPrefWidth((HOR + 1) * ratio);
        tilePane.setPrefHeight((VERT + 1) * ratio);
        for (int i = 0; i < VERT * HOR; i++) {
            Rectangle rectangle = new Rectangle(ratio, ratio);
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(Color.GREY);
            tilePane.getChildren().add(rectangle);
        }

        //creating window to show nextObject
        nextObjPane.setPrefWidth(5 * 20);
        nextObjPane.setPrefHeight(5 * 20);
        for (int i = 0; i < 16; i++) {
            Rectangle rectangle = new Rectangle(20, 20);
            rectangle.setFill(Color.WHITE);
            rectangle.setStroke(Color.GREY);
            nextObjPane.getChildren().add(rectangle);
        }

        map = new Map(tilePane, VERT, HOR);
        soundPlayer.play();
        gameOverLabel.setVisible(false);
        jokerOnDuty.setVisible(false);

        timer = new Timer();
        timer.scheduleAtFixedRate(new Loop(), 0, timerSpeed);

    }//initialize end

    public void SetSize(int v, int h, Timer tmr) {
        System.out.println("" + v + " " + h);
        //this.HOR = h;
        //this.VERT = v;
        //this.timer = tmr;
    }

    public class Loop extends TimerTask {

        @Override
        public void run() {

            if (!soundPlayer.isPlaying()) {
                soundPlayer.play();
            }
            if (currentObj instanceof JokerShape) {
                jokerOnDuty.setVisible(true);
            } else {
                jokerOnDuty.setVisible(false);
            }

            if (currentObj == null) {
                createNewObj();
                currentObj.drawNextObj(nextObjPane);
            }
            currentObj.clean(tilePane);
            boolean isDown = DOWN(currentObj);
            boolean isGameOver = checkIfGameOver();
            currentObj.draw(tilePane);

            if (!isDown) {
                if (currentObj instanceof JokerShape) {
                    map.updateForJoker(((JokerShape) currentObj).getPoint(0).x);
                } else {
                    for (int i = 0; i < 4; i++) {
                        map.add2Map(currentObj.getPoint(i).x, currentObj.getPoint(i).y, currentObj.getColor());
                    }
                    map.checkForLines(currentObj.getMinX());
                    //scoreLabel.setText("Score: " + Integer.toString(map.getScore()));
                    System.err.println(map.getScore());
                }//else end
                if (isGameOver == false) {
                    currentObj = null;
                }
            }//isDown end

            if (isGameOver) {
                gameOverLabel.setVisible(true);
                soundPlayer.close();
                System.err.println("GAME OVER!!");
            } else {
                this.cancel();
                timer = new Timer();
                timer.scheduleAtFixedRate(new Loop(), timerSpeed, timerSpeed);
            }
            //scoreLabel.setText("Score: " + Integer.toString(map.getScore()));            
            //System.out.println(map.getScore());
            //currentObj.drawNextObj(nextObjPane);

        }//run end
    }//TimerTask class end

    public Rectangle getRekt(int i, int j) {
        Rectangle rect = (Rectangle) tilePane.getChildren().get(j + i * HOR);
        return rect;
    }

    public boolean DOWN(TetrisObject Obj) {
        //Keep Going Down
        if (currentObj.getMaxX() != VERT - 1 && !checkBottom()) {
            currentObj.DOWN();
            return true;
        } //Stop and check the line if it's full
        else {
            currentObj.cleanNextObj(nextObjPane);
            return false;
        }
    }

    public void keyPressed(KeyEvent event) {
        if (currentObj!=null) {
            if (event.getCode().equals(KeyCode.LEFT) && !isLeftIllegal()) {
                currentObj.clean(tilePane);
                currentObj.LEFT();
            } else if (!isRightIllegal() && event.getCode().equals(KeyCode.RIGHT)) {
                currentObj.clean(tilePane);
                currentObj.RIGHT();
            } else if (event.getCode().equals(KeyCode.SPACE) && canBeRotated()) {
                currentObj.clean(tilePane);
                currentObj.ROTATE();
            } else if (event.getCode().equals(KeyCode.DOWN)) {
                timerSpeed = 150;
            }
            currentObj.draw(tilePane);
        }
    }//key pressed end

    public void keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DOWN)) {
            timerSpeed = 600;
        }
    }//key released end

    public boolean checkBottom() {
        for (int j = 0; j < 4; j++) {
            if (currentObj.getPoint(j).x + 1 == VERT) {
                return true;
            } else if (currentObj.getPoint(j).x < VERT - 1 && map.cellIsFull(currentObj.getPoint(j).x + 1, currentObj.getPoint(j).y)) {
                return true;
            }

        }
        return false;
    }

    public boolean isLeftIllegal() {

        for (int j = 0; j < 4; j++) {
            if (currentObj.getMinY() >= 1) {
                if (map.cellIsFull(currentObj.getPoint(j).x, currentObj.getPoint(j).y - 1)) {
                    return true;
                }
            } else if (currentObj.getPoint(j).y == 0) {
                return true;
            }

        }

        return false;
    }

    public boolean isRightIllegal() {

        for (int j = 0; j < 4; j++) {
            if (currentObj.getMaxY() < HOR - 1) {
                if (map.cellIsFull(currentObj.getPoint(j).x, currentObj.getPoint(j).y + 1)) {
                    return true;
                }
            } else if (currentObj.getPoint(j).y == HOR - 1) {
                return true;
            }
        }

        return false;
    }

    public boolean canBeRotated() {
        tempObj = currentObj;
        tempObj.ROTATE();

        boolean canBeRotated = true;
        for (int j = 0; j < 4; j++) {
            if (tempObj.getPoint(j).y > HOR - 1) {
                canBeRotated = false;
            } else if (tempObj.getPoint(j).y < 0) {
                canBeRotated = false;
            } else if (map.cellIsFull(tempObj.getPoint(j).x, tempObj.getPoint(j).y)) {

                canBeRotated = false;
            }
        }
        tempObj.ROTATE();
        tempObj.ROTATE();
        tempObj.ROTATE();

        return canBeRotated;
    }

    private boolean checkIfGameOver() {
        for (int i = 0; i < 4; i++) {
            if (currentObj.getPoint(i).x == 0) {
                return true;
            }

        }
        return false;
    }

    private void createNewObj() {

        int type = random.nextInt(8);
        switch (type) {
            case 0:
                currentObj = new IShape(0, (int) HOR / 2);
                break;
            case 1:
                currentObj = new LShape(0, (int) HOR / 2);
                break;
            case 2:
                currentObj = new SqShape(0, (int) HOR / 2);
                break;
            case 3:
                currentObj = new ZShape(0, (int) HOR / 2);
                break;
            case 4:
                currentObj = new TShape(0, (int) HOR / 2);
                break;
            case 5:
                currentObj = new SShape(1, (int) HOR / 2);
                break;
            case 6:
                currentObj = new JShape(0, (int) HOR / 2);
                break;
            case 7:
                currentObj = new JokerShape(0, (int) HOR / 2);
                break;
            default:
                break;
        }
        currentObj.setHorizontal(HOR);

    }
}
/*
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.image.*?>
<?import javafx.scene.effect.*?>
<?import javafx.scene.paint.*?>
<?import javafx.scene.media.*?>
<?import javafx.scene.text.*?>
<?import java.lang.*?>
<?import java.util.*?>
<?import javafx.scene.*?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<AnchorPane id="AnchorPane" prefHeight="510.0" prefWidth="485.0" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1" fx:controller="tetris.FXMLDocumentController">
    <children>
        <ImageView fitHeight="510.0" fitWidth="485.0" layoutX="-1.0" opacity="0.86" >
            <image>
                <Image backgroundLoading="false" requestedHeight="600" requestedWidth="550" url="file:src/tetra.jpg" />
            </image> 
        </ImageView>
        <TilePane fx:id="tilePane" layoutX="236.0" layoutY="65.0" opacity="0.83" />
        <Label layoutX="30.0" layoutY="36.0" text="TETRIS" textFill="#15b3fc">
            <font>
                <Font name="Arial Black" size="41.0" />
            </font>
            <effect>
                <Bloom />
            </effect>
        </Label>
        <Label fx:id="scoreLabel" layoutX="39.0" layoutY="96.0" opacity="0.96" prefHeight="44.0" prefWidth="122.0" text="Score:" textFill="#1aa5fc">
            <font>
                <Font name="Arial Black" size="21.0" />
            </font>
         <effect>
            <Bloom threshold="0.41" />
         </effect>
        </Label>
        <Button layoutX="37.0" layoutY="414.0" mnemonicParsing="true" onAction="#handleRetryAction" opacity="0.87" prefHeight="27.0" prefWidth="75.0" text="RETRY" textFill="#06a0ff">
         <font>
            <Font name="Arial Black" size="13.0" />
         </font>
         <effect>
            <Bloom />
         </effect></Button>
        <Button layoutX="28.0" layoutY="458.0" mnemonicParsing="true" onAction="#handleExitAction" text="Exit Game" textFill="#00aaff">
         <font>
            <Font name="Arial Black" size="13.0" />
         </font>
         <effect>
            <Bloom />
         </effect></Button>
        <Label fx:id="gameOverLabel" layoutX="243.0" layoutY="10.0" prefHeight="52.0" prefWidth="196.0" text="Game Over" textFill="#00aaff">
            <font>
                <Font name="Arial Black" size="30.0" />
            </font>
            <effect>
                <Bloom threshold="0.54" />
            </effect>
        </Label>
      <TilePane fx:id="nextObjPane" layoutX="30.0" layoutY="206.0" opacity="0.83" />
    </children>
</AnchorPane>

 */
