/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author ilkay
 */
public class Map {

    private Color[][] map;
    private TilePane pane;
    private int cleaningCounter;

    public Map() {

    }

    public Map(TilePane pane, int vert, int hor) {
        this.cleaningCounter = 0;
        this.map = new Color[vert][hor];
        this.pane = pane;
        for (Color[] map1 : map) {
            for (int j = 0; j < map[0].length; j++) {
                map1[j] = Color.WHITE;
            }
        }
    }

    public void add2Map(int x, int y, Color tmp) {
        this.map[x][y] = new Color(tmp.getRed(), tmp.getGreen(), tmp.getBlue(), tmp.getOpacity());
    }

    public boolean cellIsFull(int x, int y) {
        return map[x][y] != Color.WHITE;

    }

    public Rectangle getRekt(int i, int j) {
        Rectangle rect = (Rectangle) pane.getChildren().get(j + i * map[0].length);
        return rect;
    }

    public void checkForLines(int minX) {
        //Check for the each lineof object bottom to top of it
        //if a line is full clear and update the map
        int fullnessOfLine;

        while (minX < map.length) {
            fullnessOfLine = 0;
            for (int j = 0; j < map[0].length; j++) {
                if (map[minX][j] != Color.WHITE) {
                    fullnessOfLine++;
                }
            }
            if (fullnessOfLine == map[0].length) {
                System.err.println("HI!");
                cleaningCounter++;
                updateMap(minX);
            }
            minX++;
        }//while end
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                reDraw(i, j);
            }
        }
    }

    private void updateMap(int line) {
        //take down the colors that are top of being cleaned line
        for (int i = line; i > 0; i--) {
            for (int j = 0; j < map[0].length; j++) {
                if (i != 0) {
                    map[i][j] = map[i - 1][j];
                    map[i - 1][j] = Color.WHITE;
                }//if end
            }//for end
        }//for end

    }

    private void reDraw(int x, int y) {
        getRekt(x, y).setFill(map[x][y]);

    }

    public void cleanMap() {
        for (Color[] map1 : map) {
            for (int j = 0; j < map[0].length; j++) {
                map1[j] = Color.WHITE;
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                reDraw(i, j);
            }
        }
        this.cleaningCounter = 0;
    }

    public int getScore() {
        return cleaningCounter;
    }

    public void updateForJoker(int line) {
        if (line <= map.length - 1) {
            for (int i = line; i - 3 >= 0; i--) {
                for (int j = 0; j < map[0].length; j++) {
                    if (i - 3 >= 0) {
                        map[i][j] = map[i - 3][j];
                        map[i - 3][j] = Color.WHITE;
                    }
                }//for end
            }//for end      
        }//else if end
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                reDraw(i, j);
            }
        }

    }

}
