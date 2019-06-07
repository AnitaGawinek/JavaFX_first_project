package checkers_game;

import checkers_game.checkers.board.CheckersBoard;
import checkers_game.checkers.figures.FigureColor;

import java.util.concurrent.TimeUnit;

public class Computer {
    private int currentCol = 0;
    private int currentRow = 0;
    private CheckersBoard board;

    public Computer(CheckersBoard board) {
        this.board = board;
    }
    private boolean checkIfFigureIsBlack(){
        return getFigureColor(currentCol, currentRow) == FigureColor.BLACK;
    }
    private boolean checkIfFigureIsWhite(int checkedCol, int checkedRow) {
        return getFigureColor(checkedCol, checkedRow) == FigureColor.WHITE;
    }
    private boolean checkIfNone(int checkedCol, int checkedRow){
        return getFigureColor(checkedCol, checkedRow) == FigureColor.NONE;
    }
    private FigureColor getFigureColor(int checkedCol, int checkedRow){
        return board.getFigure(checkedCol, checkedRow).getColor();
    }

    private boolean checkIfColAndRowIsOnTheRange(int checkedCol, int checkedRow){
        return checkedCol < 8 && checkedCol >= 0 && checkedRow < 8;
    }

    private boolean checkRightMoveDirection(int step){
        int checkedCol = currentCol + step;
        int checkedRow = currentRow + step;
        return checkIfColAndRowIsOnTheRange(checkedCol, checkedRow) && checkIfNone(checkedCol, checkedRow);
    }
    private boolean checkLeftMoveDirection(int step) {
        int checkedCol = currentCol - step;
        int checkedRow = currentRow + step;
        return checkIfColAndRowIsOnTheRange(checkedCol, checkedRow) && checkIfNone(checkedCol, checkedRow);
    }
    private boolean checkRightBiteDirection(){
        int checkedCol = currentCol + 1;
        int checkedRow = currentRow + 1;
        return checkRightMoveDirection(2) && checkIfColAndRowIsOnTheRange(checkedCol, checkedRow) && checkIfFigureIsWhite(checkedCol, checkedRow);
    }
    private boolean checkLeftBiteDirection(){
        int checkedCol = currentCol - 1;
        int checkedRow = currentRow + 1;
        return checkLeftMoveDirection(2) && checkIfColAndRowIsOnTheRange(checkedCol, checkedRow) && checkIfFigureIsWhite(checkedCol, checkedRow);
    }
    private boolean checkAndBite(){
        for (int y = 0; y < 8; y++) {
            currentRow = y;
            for (int x = 0; x < 8; x++) {
                currentCol = x;
                if (checkIfFigureIsBlack()) {
                    System.out.println("Black");
                    if (checkRightBiteDirection()) {
                        move(currentCol + 2, currentRow + 2);
                        return false;
                    } else if (checkLeftBiteDirection()) {
                        move(currentCol - 2, currentRow + 2);
                        return false;
                    } else {
                        System.out.println("Unavailable move");
                    }
                } else {
                    System.out.println("Pawn other than black");
                }
            }
        }
        return true;
    }

    private boolean checkAndMove(){
        for (int y = 0; y < 8; y++) {
            currentRow = y;
            for (int x = 0; x < 8; x++) {
                currentCol = x;
                if (checkIfFigureIsBlack()) {
                    System.out.println("Black");
                    if (checkRightMoveDirection(1)) {
                        move(currentCol + 1, currentRow +1);
                        return false;
                    } else if (checkLeftMoveDirection(1)) {
                        move(currentCol - 1, currentRow + 1);
                        return false;
                    } else {
                        System.out.println("Unavailable move");
                    }
                } else {
                    System.out.println("Pawn other than black");
                }
            }
        }
        return true;
    }

    private void move(int newCol, int newRow){
        board.setClickedColAndRow(currentCol, currentRow);
        board.getFigure(currentCol, currentRow).setIsSelected(true);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        board.move(newCol, newRow);
    }

    public boolean createMove() {
        boolean moved = false;
        if (checkAndBite()){
            moved = checkAndMove();
        }
        return moved;
    }
}
