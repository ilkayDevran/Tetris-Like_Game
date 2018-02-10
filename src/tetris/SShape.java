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
public class SShape extends TetrisObject {

    public SShape() {

    }

    public SShape(int x, int y) {
        this.points[0] = new Point(x, y);
        this.points[1] = new Point(x, y + 1);
        this.points[2] = new Point(x - 1, y + 1);
        this.points[3] = new Point(x - 1, y + 2);
        color = Color.VIOLET;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    protected void ROTATE() {
        rotation++;
        switch (rotation % 4) {
            case 1:
                this.points[0] = new Point(points[0].x - 1, points[0].y + 1);
                this.points[1] = new Point(points[1].x, points[1].y);
                this.points[2] = new Point(points[2].x + 1, points[2].y + 1);
                this.points[3] = new Point(points[3].x + 2, points[3].y);
                break;
            case 2:
                this.points[0] = new Point(points[0].x + 1, points[0].y + 1);
                this.points[1] = new Point(points[1].x, points[1].y);
                this.points[2] = new Point(points[2].x + 1, points[2].y - 1);
                this.points[3] = new Point(points[3].x, points[3].y - 2);
                break;
            case 3:
                this.points[0] = new Point(points[0].x + 1, points[0].y - 1);
                this.points[1] = new Point(points[1].x, points[1].y);
                this.points[2] = new Point(points[2].x - 1, points[2].y - 1);
                this.points[3] = new Point(points[3].x - 2, points[3].y);
                break;
            case 0:
                this.points[0] = new Point(points[0].x - 1, points[0].y - 1);
                this.points[1] = new Point(points[1].x, points[1].y);
                this.points[2] = new Point(points[2].x - 1, points[2].y + 1);
                this.points[3] = new Point(points[3].x, points[3].y + 2);
                rotation = 0;
                break;
            default:
                break;
        }

    }

    @Override
    public void drawNextObj(Pane pane) {

        getRektForNextObj((TilePane) pane, 2, 1).setFill(getColor());
        getRektForNextObj((TilePane) pane, 2, 2).setFill(getColor());
        getRektForNextObj((TilePane) pane, 1, 2).setFill(getColor());
        getRektForNextObj((TilePane) pane, 1, 3).setFill(getColor());
    }

    @Override
    public void cleanNextObj(Pane pane) {

        getRektForNextObj((TilePane) pane, 2, 1).setFill(Color.WHITE);
        getRektForNextObj((TilePane) pane, 2, 2).setFill(Color.WHITE);
        getRektForNextObj((TilePane) pane, 1, 2).setFill(Color.WHITE);
        getRektForNextObj((TilePane) pane, 1, 3).setFill(Color.WHITE);

    }

    @Override
    public void printInfo() {
        System.out.println("S-SHAPE");
    }

}
