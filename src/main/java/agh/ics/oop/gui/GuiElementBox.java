package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class GuiElementBox {
    private Image img;

    public GuiElementBox(IMapElement element) {
        try {
            this.img = element.getImage();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            this.img = null;
        }
    }

    public VBox mapElementView() {
        ImageView elementView = new ImageView(img);
        VBox elementVBox = new VBox();

        elementView.setFitWidth(20);
        elementView.setFitHeight(20);

        elementVBox.getChildren().addAll(elementView);
        elementVBox.setAlignment(Pos.CENTER);
        return elementVBox;

    }
}