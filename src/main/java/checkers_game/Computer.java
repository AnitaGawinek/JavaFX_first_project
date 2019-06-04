package checkers_game;

import checkers_game.checkers.board.CheckersBoard;
import checkers_game.checkers.figures.FigureColor;
import checkers_game.checkers.figures.FigureType;
import checkers_game.checkers.moves.Move;

public class Computer {
    private CheckersBoard board;
    private int currentCol = 0;
    private int currentRow = 0;
    public Computer(CheckersBoard board) {
        this.board = board;
    }
    private boolean checkIfFigureIsBlack(){
        return board.getFigure(currentCol, currentRow).getColor() == FigureColor.BLACK;
    }
    private boolean checkRightMoveDirection(){
        return currentCol + 1 < 8 && currentRow + 1 < 8 && board.getFigure(currentCol + 1, currentRow + 1).getType() == FigureType.NONE;
    }
    private boolean checkLeftMoveDirection() {
        return currentCol - 1 >= 0 && currentRow + 1 < 8 && board.getFigure(currentCol - 1, currentRow + 1).getType() == FigureType.NONE;
    }
    private void move(int newCol, int newRow){
        board.setClickedColAndRow(currentCol, currentRow);
        board.move(newCol, newRow);
    }

    public Move createMove() {
        for (int y = 0; y < 8; y++) {
            currentRow = y;
            for (int x = 0; x < 8; x++) {
                currentCol = x;
                if (checkIfFigureIsBlack()) {
                    System.out.println("Black");
                    if (checkRightMoveDirection()) {
                        move(currentCol + 1, currentRow +1);
                        return null;
                    } else if (checkLeftMoveDirection()) {
                        move(currentCol - 1, currentRow + 1);
                        return null;
                    } else {
                        System.out.println("Ruch niemożliwy");
                    }
                } else {
                    System.out.println("Pionek inny niż czarny");
                }
            }
        }
        return null;
    }

}
