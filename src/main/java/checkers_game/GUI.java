package checkers_game;

import checkers_game.checkers.board.CheckersBoard;
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
    int widthOfCell = 69;
    int heightOfCell = 69;
    int heighOfBackground = 224;
    int widthOfBackground = 130;
    CheckersBoard checkersBoard;
    Scene scene;

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

          int col = (int) ((e.getX() - heighOfBackground) / (widthOfCell));
          int row = (int) (e.getY() - widthOfBackground) / (heightOfCell);
          doClick(col, row);
        });
    }
// TODO : metoda ustawiająca jednocześnie col i row
// TODO : grafika NONE
    private void doClick(int col, int row) {

        if (col >= 0 && col < 8 && row >= 0 && row < 8){
            if (checkersBoard.getClickedCol() == -1 && checkersBoard.getClickedRow() == -1){
                System.out.println("Pierwszy click");
                checkersBoard.setClickedCol(col);
                checkersBoard.setClickedRow(row);
                System.out.println(checkersBoard.isEmpty());

            } else {
                System.out.println("Drugi click");
                checkersBoard.move(col, row);
                checkersBoard.setClickedCol(-1);
                checkersBoard.setClickedRow(-1);
            }

            showBoard();
        }
    }
    public void setSceneAndBoard(Scene scene, CheckersBoard board){
        this.checkersBoard = board;
        this.scene = scene;
    }

    public void showBoard() {

        for(int row = 0; row < 8; ++row) {
            for(int col = 0; col < 8; ++col) {
                Figure figure = checkersBoard.getFigure(col, row);
                ImageView imageView = new ImageView(figure.getImage());
                grid.add(imageView, col, row);
            }
        }
    }
}
