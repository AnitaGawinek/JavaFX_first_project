package checkers_game;

import checkers_game.checkers.Checkers;
import checkers_game.checkers.figures.Figure;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class GUI {
    private Image backgroundImg = new Image("board/Background&board.jpg");
    private GridPane grid = new GridPane();

    public GridPane getGrid() {
        return grid;
    }

    public void addBackground() {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(backgroundImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        grid.setBackground(new Background(new BackgroundImage[]{backgroundImage}));

        int i;
        for(i = 0; i < 8; ++i) {
            grid.getColumnConstraints().add(new ColumnConstraints(69));
        }

        for(i = 0; i < 8; ++i) {
            grid.getRowConstraints().add(new RowConstraints(69));
        }

        grid.setAlignment(Pos.CENTER);
        grid.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            System.out.println(e.getX());
            System.out.println(e.getY());
            int col = 69;
            int row = 69;
            doClick(col, row);
        });
    }

    private void doClick(int col, int row) {
    }

    public void showBoard(Scene scene, Checkers checkers) {
        for(int row = 0; row < 8; ++row) {
            for(int col = 0; col < 8; ++col) {
                Figure figure = checkers.getFigure(col, row);
                ImageView imageView = new ImageView(figure.getImage());
                grid.add(imageView, col, row);
            }
        }
    }
}
