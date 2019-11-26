package checkers_game;

import checkers_game.checkers.board.CheckersBoard;
import checkers_game.checkers.figures.Figure;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GUI {
    private Image backgroundImg = new Image("board/Background&board.jpg");
    private GridPane grid = new GridPane();
    private int widthOfCell = 70;
    private int heightOfCell = 70;
    private int heightOfBackground = 224;
    private int widthOfBackground = 130;
    private CheckersBoard checkersBoard;
    private Stage stage;

    GridPane getGrid() {
        return grid;
    }

    public void addBackground() {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(backgroundImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        grid.setBackground(new Background(backgroundImage));

        int i;
        for(i = 0; i < 8; ++i) {
            grid.getColumnConstraints().add(new ColumnConstraints(70));
        }
        for(i = 0; i < 8; ++i) {
            grid.getRowConstraints().add(new RowConstraints(70));
        }
        grid.setAlignment(Pos.CENTER);
        grid.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            int col = (int) (e.getX() - heightOfBackground) / (widthOfCell);
            int row = (int) (e.getY() - widthOfBackground) / (heightOfCell);
            checkersBoard.doClick(col, row);
            showBoard();
        });
    }

    public void setSceneAndBoard(Scene scene, CheckersBoard board, Stage stage){
        this.checkersBoard = board;
        this.stage = stage;
    }

    public void showBoard() {
        grid.getChildren().clear();
        for(int row = 0; row < 8; ++row) {
            for(int col = 0; col < 8; ++col) {
                Figure figure = checkersBoard.getFigure(col, row);
                ImageView imageView = new ImageView(figure.getImage());
                grid.add(imageView, col, row);
            }
        }
        stage.setTitle(String.format("CheckersGame [MOVE: %s]", checkersBoard.getWhoseMove()));
    }
}
