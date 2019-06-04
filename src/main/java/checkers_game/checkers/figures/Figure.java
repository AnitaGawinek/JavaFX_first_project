package checkers_game.checkers.figures;

import javafx.scene.image.Image;

import static checkers_game.checkers.figures.FigureColor.BLACK;
import static checkers_game.checkers.figures.FigureColor.WHITE;
import static checkers_game.checkers.figures.FigureType.*;

public class Figure {
    private FigureColor color;
    private FigureType type;
    private boolean isQueen = false;
    private boolean isSelected = false;
    private Image blackPawn = new Image("pawns/Pawn-black.png");
    private Image whitePawn = new Image("pawns/Pawn-white.png");
    private Image blackQueen = new Image("pawns/Queen-black.png");
    private Image whiteQueen = new Image("pawns/Queen-white.png");
    private Image selectedBlackPawn = new Image("pawns/SelectedPawn-black.png");
    private Image selectedWhitePawn = new Image("pawns/SelectedPawn-white.png");
    private Image selectedBlackQueen = new Image("pawns/SelectedQueen-black.png");
    private Image selectedWhiteQueen = new Image("pawns/SelectedQueen-white.png");

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
    public void setIsSelected(boolean isSelected){
        this.isSelected = isSelected;
    }
    public boolean isQueen() {
        return isQueen;
    }
    public void makeQueen(){
        isQueen = true;
        type = QUEEN;
    }
    public void changeBlackPawnToQueen(){
        if (!isQueen() && color == BLACK){
            makeQueen();
        }
    }
    public void changeWhitePawnToQueen(){
        if (!isQueen() && color == WHITE){
            makeQueen();
        }
    }

    public boolean isPresent() {
        if (type != FigureType.NONE && color != FigureColor.NONE){
            return true;
        } else {
            return false;
        }
    }

    public Image getImage() {
//        if (isSelected == false){
//            return getImageOfNotSelectedPawns();
//        } else {
//            return getImageOfSelectedPawns();
//        }
//        return isSelected == true ? getImageOfSelectedPawns() : getImageOfNotSelectedPawns() ;
        return isSelected ? getImageOfSelectedPawns() : getImageOfNotSelectedPawns() ;
    }
    public Image getImageOfNotSelectedPawns() {
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
        return image;
    }

    public Image getImageOfSelectedPawns() {
        Image image = null;
        if (color == BLACK && type == PAWN) {
            image = selectedBlackPawn;
        }

        if (color == WHITE && type == PAWN) {
            image = selectedWhitePawn;
        }

        if (color == BLACK && type == QUEEN) {
            image = selectedBlackQueen;
        }

        if (color == WHITE && type == QUEEN) {
            image = selectedWhiteQueen;
        }
        return image;
    }
}
