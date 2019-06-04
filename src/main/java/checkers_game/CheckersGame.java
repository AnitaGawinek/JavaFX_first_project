package checkers_game;

import checkers_game.checkers.board.CheckersBoard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CheckersGame extends Application {
    private CheckersBoard checkersBoard = new CheckersBoard();
    private GUI gui = new GUI();
    // 0 => no type selected
    // 1 => player vs player
    // 2 => player vs computer
    private int gameType = 2;

    public static void main(String[] args) {

        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        checkersBoard.setAi( gameType == 2 );
        Scene scene = new Scene(gui.getGrid(), 1000, 800);
        primaryStage.setTitle("CheckersBoard Game [MOVE: WHITE]");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        gui.addBackground();
        checkersBoard.initBoard();
        gui.setSceneAndBoard(scene, checkersBoard, primaryStage);
        gui.showBoard();

    }
}
// TODO : możliwość zmiany wybranego pionka
// TODO : ruch damki
// TODO : działanie komputera