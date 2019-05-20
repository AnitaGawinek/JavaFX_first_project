package checkers_game.checkers.board;

import checkers_game.checkers.figures.Figure;
import checkers_game.checkers.figures.FigureColor;
import checkers_game.checkers.figures.FigureType;
import java.util.ArrayList;
import java.util.List;
import static checkers_game.checkers.figures.FigureColor.*;
import static checkers_game.checkers.figures.FigureType.NONE;
import static checkers_game.checkers.figures.FigureType.PAWN;

public class CheckersBoard {
    private List<BoardRow> rows = new ArrayList();
    private int clickedCol = -1;
    private int clickedRow = -1;

    public CheckersBoard() {
        for(int n = 0; n < 8; ++n) {
            rows.add(new BoardRow());
        }
    }

    public int getClickedCol() {
        return clickedCol;
    }
    public void setClickedCol(int clickedCol){
        this.clickedCol = clickedCol;
    }

    public int getClickedRow() {
        return clickedRow;
    }
    public void setClickedRow(int clickedRow){
        this.clickedRow = clickedRow;
    }

    public Figure getFigure(int col, int row) {
        return rows.get(row).getCols().get(col);
    }

    public void setFigure(int col, int row, Figure figure) {
        rows.get(row).getCols().add(col, figure);
        rows.get(row).getCols().remove(col + 1);
    }

    public void move(int newCol, int newRow){
        Figure selectedFigure = getFigure(clickedCol, clickedRow);
        setFigure(newCol, newRow, selectedFigure);
        setFigure(clickedCol, clickedRow, new Figure(FigureColor.NONE, FigureType.NONE));
    }
    public boolean isEmpty() {
        Figure figure = getFigure(clickedCol, clickedRow);
        if(figure.getColor() == FigureColor.NONE && figure.getType() == NONE) {
            return true;
        } else {
            return false;
        }
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
