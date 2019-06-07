package checkers_game;

import checkers_game.checkers.board.CheckersBoard;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CheckersGame extends Application {
    private CheckersBoard checkersBoard = new CheckersBoard();
    private GUI gui = new GUI();
    // 0 - no type selected
    // 1 - player vs player
    // 2 - player vs computer
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
        primaryStage.hide();

        BorderPane borderPane = new BorderPane();
        Scene window = new Scene(borderPane, 450, 200);
        Stage stage = new Stage();
        stage.setScene(window);
        stage.setTitle("Select game type: ");
        stage.show();

        Button buttonPVP = new Button("Player vs player");
        Button buttonPVC = new Button("Player vs computer");
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(buttonPVP, buttonPVC);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);
        buttonPVP.setOnAction(event ->  {
            checkersBoard.setAi(false);
            showBoard(scene, primaryStage);
            stage.hide();
        });
        buttonPVC.setOnAction(event ->  {
            checkersBoard.setAi(true);
            showBoard(scene, primaryStage);
            stage.hide();
        });
    }
    public void showBoard(Scene scene, Stage primaryStage) {
        primaryStage.show();
        gui.addBackground();
        checkersBoard.initBoard();
        gui.setSceneAndBoard(scene, checkersBoard, primaryStage);
        gui.showBoard();
    }
}