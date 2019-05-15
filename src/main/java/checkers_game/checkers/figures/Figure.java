package checkers_game.checkers.figures;

import javafx.scene.image.Image;

public class Figure {
    private FigureColor color;
    private FigureType type;
    private Image blackPawn = new Image("pawns/Pawn-black.png");
    private Image whitePawn = new Image("pawns/Pawn-white.png");
    private Image blackQueen = new Image("pawns/Queen-black.png");
    private Image whiteQueen = new Image("pawns/Queen-white.png");

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

    public Image getImage() {
        Image image = null;
        if (color == FigureColor.BLACK && type == FigureType.PAWN) {
            image = blackPawn;
        }

        if (color == FigureColor.WHITE && type == FigureType.PAWN) {
            image = whitePawn;
        }

        if (color == FigureColor.BLACK && type == FigureType.QUEEN) {
            image = blackQueen;
        }

        if (color == FigureColor.WHITE && type == FigureType.QUEEN) {
            image = whiteQueen;
        }

        return image;
    }
}
