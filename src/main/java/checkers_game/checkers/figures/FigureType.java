package checkers_game.checkers.figures;

public enum FigureType {
    PAWN,
    QUEEN,
    NONE;

    public boolean isQueen() {
        return this == QUEEN;
    }
}