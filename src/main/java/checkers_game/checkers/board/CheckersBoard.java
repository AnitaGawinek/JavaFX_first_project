package checkers_game.checkers.board;

import checkers_game.Computer;
import checkers_game.checkers.figures.Figure;
import checkers_game.checkers.figures.FigureColor;
import checkers_game.checkers.figures.FigureType;
import checkers_game.checkers.moves.Move;

import java.util.ArrayList;
import java.util.List;
import static checkers_game.checkers.figures.FigureColor.*;
import static checkers_game.checkers.figures.FigureType.NONE;
import static checkers_game.checkers.figures.FigureType.PAWN;

public class CheckersBoard {
    private Computer computer = new Computer(this);
    private List<BoardRow> rows = new ArrayList();
    private FigureColor whoseMove = WHITE;
    private int clickedCol = -1;
    private int clickedRow = -1;
    private int opponentToRemoveCol = -1;
    private int opponentToRemoveRow = -1;
    private int whitePawnsCount = 12;
    private int blackPawnsCount = 12;
    private boolean ai = false;

    public CheckersBoard() {
        for(int n = 0; n < 8; ++n) {
            rows.add(new BoardRow());
        }
    }
    public FigureColor getWhoseMove(){
        return whoseMove;
    }
    private boolean checkMove(Figure figure) {
        return figure.getColor() == whoseMove;
    }
    private int getClickedCol() {
        return clickedCol;
    }
    private void setClickedCol(int clickedCol){
        this.clickedCol = clickedCol;
    }

    private int getClickedRow() {
        return clickedRow;
    }
    private void setClickedRow(int clickedRow){
        this.clickedRow = clickedRow;
    }

    public Figure getFigure(int col, int row) {
        return rows.get(row).getCols().get(col);
    }

    private void setFigure(int col, int row, Figure figure) {
        rows.get(row).getCols().add(col, figure);
        rows.get(row).getCols().remove(col + 1);
    }
    public void setClickedColAndRow(int col, int row){
        this.clickedCol = col;
        this.clickedRow = row;
    }

    public void doClick(int col, int row){
        if (col >= 0 && col < 8 && row >= 0 && row < 8) {
            if (checkClickedElement()) {
                if (checkPawnPresentAndColor(col, row)) {
                    setClickedColAndRow(col, row);
                    getFigure(col, row).setIsSelected(true);
                }
            } else {
                move(col, row);
            }
        }
    }
    private boolean isCloseToBecomeAQueen(int col, int row){
        Figure figure = getFigure(col, row);
        return !(figure.isQueen()) && figure.getColor() == BLACK && row == 6 || !(figure.isQueen()) && figure.getColor() == WHITE && row == 1;
    }
    public void move(int newCol, int newRow) {
        if (isMoveAllowed(clickedCol, clickedRow, newCol, newRow)) {
            Figure selectedFigure = getFigure(clickedCol, clickedRow);
            setFigure(newCol, newRow, selectedFigure);
            setFigure(clickedCol, clickedRow, new Figure(FigureColor.NONE, FigureType.NONE));
            if (newRow == 7){
                selectedFigure.changeBlackPawnToQueen();
            } else if (newRow == 0){
                selectedFigure.changeWhitePawnToQueen();
            }
            setClickedColAndRow(-1, -1);
            selectedFigure.setIsSelected(false);
            if (opponentToRemoveCol != -1) {
                setFigure(opponentToRemoveCol, opponentToRemoveRow, new Figure(FigureColor.NONE, FigureType.NONE));
                opponentToRemoveCol = -1;
                opponentToRemoveRow = -1;
                if (whoseMove == WHITE) {
                    blackPawnsCount--;
                    System.out.println("Black pawns count: " + blackPawnsCount);
                } else {
                    whitePawnsCount--;
                    System.out.println("White pawns count: " + whitePawnsCount);
                }
            }
            if (isGameEnds()) {
                whoWins();
            }
            whoseMove = oppositeColor(whoseMove);
            if (whoseMove.equals(BLACK) && ai) {
                doComputerMove();
            }
        }
    }

    private void doComputerMove() {
        Computer computer = new Computer(this);
        computer.createMove();
//        Move move = computer.createMove();
//        clickedCol = move.getX1();
//        clickedRow = move.getY1();
//        move(move.getX2(), move.getY2());
        System.out.println("AI");
    }

    public void setAi(boolean userChoice) {
        this.ai = userChoice;
    }

    private boolean isGameEnds(){
        return whitePawnsCount == 0 || blackPawnsCount == 0;
    }

    private void whoWins(){
        if (whitePawnsCount == 0){
            System.out.println("Black pawns wins");
        } else {
            System.out.println("White pawns wins");
        }
    }

    private FigureColor oppositeColor(FigureColor figureColor) {
        return figureColor == WHITE ? BLACK : WHITE;
    }

    private boolean isMoveAllowed(int col1, int row1, int col2, int row2) {
        boolean result = true;
        result = result && correctPlayerTriesToMove(col1, row1);
        result = result && isTargetCellEmpty(col2, row2);
        if (isJumpOverMove(col1, row1, col2, row2)){
            result = result && isOpponentBetween(col1, row1, col2, row2);
        } else {
            result = result && isOneStepDiagonalMove(col1, row1, col2, row2);
            result = result && checkPawnMoveByColor(col1, row1, col2, row2);
        }
        return result;
    }

    private boolean checkPawnMoveByColor(int col1, int row1, int col2, int row2) {
        boolean result = true;
        if (getFigure(col1, row1).getColor() == BLACK){
            result = ((row1 - row2) == -1);
        } else if (getFigure(col1, row1).getColor() == WHITE){
            result = ((row1 - row2) == 1);
        }
        return result;
    }

    private boolean isOneStepDiagonalMove(int col1, int row1, int col2, int row2) {
        System.out.println(col1-col2);
        System.out.println(row1-row2);
        return (Math.abs(col1 - col2) == 1)&&(Math.abs(row1 - row2) == 1);
    }

    private boolean isTargetCellEmpty(int col, int row) {
        return getFigure(col, row).getType() == NONE;
    }

    private boolean isOpponentBetween(int col1, int row1, int col2, int row2) {
        Figure opponent;
        int opponentCol;
        int opponentRow;
        if (col1 > col2){
            if (row1 > row2){
                opponent = getFigure(col1 - 1, row1 - 1);
                opponentCol = col1 - 1;
                opponentRow = row1 - 1;
            } else {
                opponent = getFigure(col1 -1, row1 + 1);
                opponentCol = col1 - 1;
                opponentRow = row1 + 1;
            }
        } else {
            if (row1 > row2) {
                opponent = getFigure(col1 + 1, row1 - 1);
                opponentCol = col1 + 1;
                opponentRow = row1 - 1;
            } else {
                opponent = getFigure(col1 + 1, row1 + 1);
                opponentCol = col1 + 1;
                opponentRow = row1 + 1;
            }
        }
        boolean result = opponent.getColor() == oppositeColor(whoseMove);
        if (result) {
            opponentToRemoveCol = opponentCol;
            opponentToRemoveRow = opponentRow;
        }
        return result;
    }

//    private boolean isQueenMove(){
//
//    }
    private boolean isJumpOverMove(int col1, int row1, int col2, int row2) {
        if (col1 > col2){
            if (row1 > row2){
                return (row1 == row2 + 2) && (col1 == col2 +2);
            } else {
                return (row2 == row1 + 2) && (col1 == col2 +2);
            }
        } else{
            if (row1 > row2){
                return (row1 == row2 + 2) && (col2 == col1 +2);
            } else{
                return (row2 == row1 + 2) && (col2 == col1 +2);
            }
        }
    }

    private boolean correctPlayerTriesToMove(int col, int row) {
        return getFigure(col, row).getColor() == whoseMove;
    }

    private boolean isEmpty() {
        Figure figure = getFigure(clickedCol, clickedRow);
        return figure.getColor() == FigureColor.NONE && figure.getType() == NONE;
    }

    private boolean checkClickedElement(){
        return getClickedCol() == -1 && getClickedRow() == -1;
    }
    private boolean checkPawnPresentAndColor(int col, int row){
        Figure figure = getFigure(col, row);
        return figure.isPresent() && checkMove(figure);
    }

    public void initBoard() {
        setFigure(0, 0, new Figure(BLACK, PAWN));
        setFigure(2, 0, new Figure(BLACK, PAWN));
        setFigure(4, 0, new Figure(BLACK, PAWN));
        setFigure(6, 0, new Figure(BLACK, PAWN));
        setFigure(1, 1, new Figure(BLACK, PAWN));
        setFigure(3, 1, new Figure(BLACK, PAWN));
        setFigure(5, 1, new Figure(BLACK, PAWN));
        setFigure(7, 1, new Figure(BLACK, PAWN));
        setFigure(0, 2, new Figure(BLACK, PAWN));
        setFigure(2, 2, new Figure(BLACK, PAWN));
        setFigure(4, 2, new Figure(BLACK, PAWN));
        setFigure(6, 2, new Figure(BLACK, PAWN));
        setFigure(1, 5, new Figure(WHITE, PAWN));
        setFigure(3, 5, new Figure(WHITE, PAWN));
        setFigure(5, 5, new Figure(WHITE, PAWN));
        setFigure(7, 5, new Figure(WHITE, PAWN));
        setFigure(0, 6, new Figure(WHITE, PAWN));
        setFigure(2, 6, new Figure(WHITE, PAWN));
        setFigure(4, 6, new Figure(WHITE, PAWN));
        setFigure(6, 6, new Figure(WHITE, PAWN));
        setFigure(1, 7, new Figure(WHITE, PAWN));
        setFigure(3, 7, new Figure(WHITE, PAWN));
        setFigure(5, 7, new Figure(WHITE, PAWN));
        setFigure(7, 7, new Figure(WHITE, PAWN));
    }
}
