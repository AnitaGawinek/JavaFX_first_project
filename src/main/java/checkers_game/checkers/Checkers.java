package checkers_game.checkers;

import checkers_game.checkers.figures.Figure;
import checkers_game.checkers.figures.FigureColor;
import checkers_game.checkers.figures.FigureType;
import java.util.ArrayList;
import java.util.List;

public class Checkers {
    private List<BoardRow> rows = new ArrayList();

    public Checkers() {
        for(int n = 0; n < 8; ++n) {
            rows.add(new BoardRow());
        }
    }

    public Figure getFigure(int col, int row) {
        return rows.get(row).getCols().get(col);
    }

    public void setFigure(int col, int row, Figure figure) {
        rows.get(row).getCols().add(col, figure);
        rows.get(row).getCols().remove(col + 1);
    }

    public void initBoard() {
        setFigure(0, 0, new Figure(FigureColor.BLACK, FigureType.PAWN));
        setFigure(2, 0, new Figure(FigureColor.BLACK, FigureType.PAWN));
        setFigure(4, 0, new Figure(FigureColor.BLACK, FigureType.PAWN));
        setFigure(6, 0, new Figure(FigureColor.BLACK, FigureType.PAWN));
        setFigure(1, 1, new Figure(FigureColor.BLACK, FigureType.PAWN));
        setFigure(3, 1, new Figure(FigureColor.BLACK, FigureType.PAWN));
        setFigure(5, 1, new Figure(FigureColor.BLACK, FigureType.PAWN));
        setFigure(7, 1, new Figure(FigureColor.BLACK, FigureType.PAWN));
        setFigure(0, 2, new Figure(FigureColor.BLACK, FigureType.PAWN));
        setFigure(2, 2, new Figure(FigureColor.BLACK, FigureType.PAWN));
        setFigure(4, 2, new Figure(FigureColor.BLACK, FigureType.PAWN));
        setFigure(6, 2, new Figure(FigureColor.BLACK, FigureType.PAWN));
        setFigure(1, 5, new Figure(FigureColor.WHITE, FigureType.PAWN));
        setFigure(3, 5, new Figure(FigureColor.WHITE, FigureType.PAWN));
        setFigure(5, 5, new Figure(FigureColor.WHITE, FigureType.PAWN));
        setFigure(7, 5, new Figure(FigureColor.WHITE, FigureType.PAWN));
        setFigure(0, 6, new Figure(FigureColor.WHITE, FigureType.PAWN));
        setFigure(2, 6, new Figure(FigureColor.WHITE, FigureType.PAWN));
        setFigure(4, 6, new Figure(FigureColor.WHITE, FigureType.PAWN));
        setFigure(6, 6, new Figure(FigureColor.WHITE, FigureType.PAWN));
        setFigure(1, 7, new Figure(FigureColor.WHITE, FigureType.PAWN));
        setFigure(3, 7, new Figure(FigureColor.WHITE, FigureType.PAWN));
        setFigure(5, 7, new Figure(FigureColor.WHITE, FigureType.PAWN));
        setFigure(7, 7, new Figure(FigureColor.WHITE, FigureType.PAWN));
    }
}
