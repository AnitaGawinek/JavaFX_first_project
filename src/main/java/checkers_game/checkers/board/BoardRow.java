package checkers_game.checkers.board;

import checkers_game.checkers.figures.Figure;
import checkers_game.checkers.figures.FigureColor;
import checkers_game.checkers.figures.FigureType;

import java.util.ArrayList;
import java.util.List;

public class BoardRow {
    private List<Figure> cols = new ArrayList();

    public BoardRow() {
        for(int n = 0; n < 8; ++n) {
            cols.add(new Figure(FigureColor.NONE, FigureType.NONE));
        }
    }

    public List<Figure> getCols() {
        return cols;
    }
}