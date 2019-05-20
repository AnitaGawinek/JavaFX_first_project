package checkers_game.checkers.figures;

import javafx.scene.image.Image;

import static checkers_game.checkers.figures.FigureColor.BLACK;
import static checkers_game.checkers.figures.FigureColor.WHITE;
import static checkers_game.checkers.figures.FigureType.*;

public class Figure {
    private FigureColor color;
    private FigureType type;
    private boolean isQueen;
    private Image blackPawn = new Image("pawns/Pawn-black.png");
    private Image whitePawn = new Image("pawns/Pawn-white.png");
    private Image blackQueen = new Image("pawns/Queen-black.png");
    private Image whiteQueen = new Image("pawns/Queen-white.png");
    private Image pawnNone = new Image("pawns/Pawn-none.png");

    public Figure(FigureColor color, FigureType type) {
        this.color = color;
        this.type = type;
    }

    public FigureColor getColor() {
        return color;
    }

    public FigureType getType() {
        return type;
    }
    public boolean isQueen() {
        return isQueen;
    }
    public void makeQueen(){
        isQueen = true;
        type = QUEEN;
    }

    public Image getImage() {
        Image image = null;
        if (color == BLACK && type == PAWN) {
            image = blackPawn;
        }

        if (color == WHITE && type == PAWN) {
            image = whitePawn;
        }

        if (color == BLACK && type == QUEEN) {
            image = blackQueen;
        }

        if (color == WHITE && type == QUEEN) {
            image = whiteQueen;
        }
        if (color == FigureColor.NONE && type == NONE) {
            image = pawnNone;
        }

        return image;
    }
}
