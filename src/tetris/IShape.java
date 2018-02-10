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
public class IShape extends TetrisObject {

    public IShape() {

    }

    public IShape(int x, int y) {
        this.points[0] = new Point(x, y);
        this.points[1] = new Point(x + 1, y);
        this.points[2] = new Point(x + 2, y);
        this.points[3] = new Point(x + 3, y);
        color = Color.YELLOWGREEN;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    protected void ROTATE() {
        rotation++;
        if (rotation % 4 == 1) {
            this.points[0] = new Point(points[0].x + 2, points[0].y - 2);
            this.points[1] = new Point(points[1].x + 1, points[1].y - 1);
            this.points[2] = new Point(points[2].x, points[2].y);
            this.points[3] = new Point(points[3].x - 1, points[3].y + 1);

        } else if (rotation % 4 == 2) {
            this.points[0] = new Point(points[0].x - 2, points[0].y + 2);
            this.points[1] = new Point(points[1].x - 1, points[1].y + 1);
            this.points[2] = new Point(points[2].x, points[2].y);
            this.points[3] = new Point(points[3].x + 1, points[3].y - 1);

            rotation = 0;
        }

    }

    @Override
    public void drawNextObj(Pane pane) {

        getRektForNextObj((TilePane) pane, 0, 1).setFill(getColor());
        getRektForNextObj((TilePane) pane, 1, 1).setFill(getColor());
        getRektForNextObj((TilePane) pane, 2, 1).setFill(getColor());
        getRektForNextObj((TilePane) pane, 3, 1).setFill(getColor());
    }

    @Override
    public void cleanNextObj(Pane pane) {

        getRektForNextObj((TilePane) pane, 0, 1).setFill(Color.WHITE);
        getRektForNextObj((TilePane) pane, 1, 1).setFill(Color.WHITE);
        getRektForNextObj((TilePane) pane, 2, 1).setFill(Color.WHITE);
        getRektForNextObj((TilePane) pane, 3, 1).setFill(Color.WHITE);

    }

    @Override
    public void printInfo() {
        System.out.println("I-SHAPE");
    }

}
