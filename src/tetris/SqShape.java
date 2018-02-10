/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Point;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

/**
 *
 * @author ilkay
 */
public class SqShape extends TetrisObject {

    public SqShape() {

    }

    public SqShape(int x, int y) {
        this.points[0] = new Point(x, y);
        this.points[1] = new Point(x, y + 1);
        this.points[2] = new Point(x + 1, y);
        this.points[3] = new Point(x + 1, y + 1);
        color = Color.PURPLE;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    protected void ROTATE() {

    }

    @Override
    public void drawNextObj(Pane pane) {

        getRektForNextObj((TilePane) pane, 1, 1).setFill(getColor());
        getRektForNextObj((TilePane) pane, 1, 2).setFill(getColor());
        getRektForNextObj((TilePane) pane, 2, 1).setFill(getColor());
        getRektForNextObj((TilePane) pane, 2, 2).setFill(getColor());
    }

    @Override
    public void cleanNextObj(Pane pane) {

        getRektForNextObj((TilePane) pane, 1, 1).setFill(Color.WHITE);
        getRektForNextObj((TilePane) pane, 1, 2).setFill(Color.WHITE);
        getRektForNextObj((TilePane) pane, 1, 1).setFill(Color.WHITE);
        getRektForNextObj((TilePane) pane, 2, 2).setFill(Color.WHITE);

    }

    @Override
    public void printInfo() {
        System.out.println("Sq-SHAPE");
    }

}
