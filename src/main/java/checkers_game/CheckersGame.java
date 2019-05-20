package checkers_game;

import checkers_game.checkers.board.CheckersBoard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CheckersGame extends Application {
    GUI gui = new GUI();
    CheckersBoard checkersBoard = new CheckersBoard();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(gui.getGrid(), 1000, 800);
        primaryStage.setTitle("CheckersBoard Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


        gui.addBackground();
        checkersBoard.initBoard();
        gui.setSceneAndBoard(scene, checkersBoard);
        gui.showBoard();
    }
}