package tetris;

import java.awt.Point;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

/**
 *
 * @author ilkay
 */
public class JokerShape extends TetrisObject {

    public JokerShape() {

    }

    public JokerShape(int x, int y) {
        jokersPoint = new Point(x, y);
        color = Color.CYAN;
    }

    public Color getColor() {
        return color;
    }

    protected void ROTATE() {

    }

    @Override
    public void draw(Pane pane) {
        getRekt((TilePane) pane, jokersPoint.x, jokersPoint.y).setFill(getColor());
    }

    @Override
    public void clean(Pane pane) {
        getRekt((TilePane) pane, jokersPoint.x, jokersPoint.y).setFill(Color.WHITE);
    }

    @Override
    public void drawNextObj(Pane pane) {
       getRektForNextObj((TilePane) pane, 2, 2).setFill(getColor());
    }

    @Override
    public void cleanNextObj(Pane pane) {
        getRektForNextObj((TilePane) pane, 2, 2).setFill(Color.WHITE);

    }

    @Override
    public Point getPoint(int n) {
        return jokersPoint;
    }

    @Override
    public int getMaxY() {
        return jokersPoint.y;
    }

    @Override
    public int getMinY() {
        return jokersPoint.y;
    }

    @Override
    public int getMaxX() {
        return jokersPoint.x;
    }

    @Override
    public int getMinX() {
        return jokersPoint.x;
    }

    @Override
    public void DOWN() {
        jokersPoint.x++;
    }

    @Override
    public void RIGHT() {
        jokersPoint.y++;
        //moveSoundPlayer.play();
    }

    @Override
    public void LEFT() {
        jokersPoint.y--;
        // moveSoundPlayer.play();
    }

    @Override
    public void printInfo() {
        System.out.println("Joker-SHAPE");
    }

}
