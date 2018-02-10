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
import javafx.scene.shape.Rectangle;

/**
 *
 * @author ilkay
 */
public abstract class TetrisObject {

    private SoundPlayer moveSoundPlayer = new SoundPlayer("resources/move.mp3");
    private int horizontal;
    protected Color color;
    protected Point[] points = new Point[4];
    protected Point jokersPoint;
    protected int[][] mtrx = new int[4][4];
    protected int rotation = 0;
    private FXMLDocumentController c;

    protected abstract void ROTATE();

    protected abstract Color getColor();

    public TetrisObject() {

    }

    public void draw(Pane pane) {

        Point p0 = points[0];
        Point p1 = points[1];
        Point p2 = points[2];
        Point p3 = points[3];

        getRekt((TilePane) pane, p0.x, p0.y).setFill(getColor());
        getRekt((TilePane) pane, p1.x, p1.y).setFill(getColor());
        getRekt((TilePane) pane, p2.x, p2.y).setFill(getColor());
        getRekt((TilePane) pane, p3.x, p3.y).setFill(getColor());
    }

    public void clean(Pane pane) {

        Point p0 = points[0];
        Point p1 = points[1];
        Point p2 = points[2];
        Point p3 = points[3];

        getRekt((TilePane) pane, p0.x, p0.y).setFill(Color.WHITE);
        getRekt((TilePane) pane, p1.x, p1.y).setFill(Color.WHITE);
        getRekt((TilePane) pane, p2.x, p2.y).setFill(Color.WHITE);
        getRekt((TilePane) pane, p3.x, p3.y).setFill(Color.WHITE);

    }

    public void drawNextObj(Pane pane) {
  
       }

    public void cleanNextObj(Pane pane) {
        
}

    public Rectangle getRekt(TilePane tp, int i, int j) {
        Rectangle rect = (Rectangle) tp.getChildren().get(j + i * horizontal);
        if (rect == null) {
            System.err.println("rect is null");
        }
        return rect;
    }
    
    public Rectangle getRektForNextObj(TilePane tp, int i, int j) {
        Rectangle rect = (Rectangle) tp.getChildren().get(j + i * 4);
        return rect;
    }

    public Point getPoint(int n) {
        return points[n];
    }

    public int getMaxY() {
        int maxY = points[0].y;
        for (int i = 1; i < 4; i++) {
            if (points[i].y > maxY) {
                maxY = points[i].y;
            }
        }
        return maxY;
    }

    public int getMinY() {
        int minY = points[0].y;
        for (int i = 1; i < 4; i++) {
            if (points[i].y < minY) {
                minY = points[i].y;
            }
        }
        return minY;
    }

    public int getMaxX() {
        int maxX = points[0].x;
        for (int i = 1; i < 4; i++) {
            if (points[i].x > maxX) {
                maxX = points[i].x;
            }
        }
        return maxX;
    }

    public int getMinX() {
        int minX = points[0].x;
        for (int i = 1; i < 4; i++) {
            if (points[i].x < minX) {
                minX = points[i].x;
            }
        }
        return minX;
    }

    public void DOWN() {
        for (Point p : points) {
            p.x++;
        }
    }

    public void RIGHT() {
        for (Point p : points) {
            p.y++;
        }
        moveSoundPlayer.play();
    }

    public void LEFT() {
        for (Point p : points) {
            p.y--;
        }
         moveSoundPlayer.play();
    }

    public void setHorizontal(int h) {
        this.horizontal = h;
    }

    public void printInfo() {
    }
}
