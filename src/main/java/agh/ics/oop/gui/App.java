package agh.ics.oop.gui;

import agh.ics.oop.DarwinMap;
import agh.ics.oop.IMapElement;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.Vector2d;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class App extends Application {
    private DarwinMap map;
    private GridPane grid;
    private SimulationEngine engine;
    private TextField mapWidthTF;
    private TextField mapHeightTF;
    private TextField startEnergyTF;
    private TextField moveEnergyTF;
    private TextField plantEnergyTF;
    private TextField jungleRatioTF;
    private TextField startAnimalsTF;
    private TextField startGrassTF;
    private TextField minEnergyToBreedTF;
    private TextField renderDelayTF;

    public void getArguments() {
        this.grid.setPadding(new Insets(25, 25, 25, 25));
        Label mapWidthLabel = new Label("Map Width:");
        this.grid.add(mapWidthLabel, 0, 1);
        this.mapWidthTF = new TextField();
        this.grid.add(this.mapWidthTF, 1, 1);

        Label mapHeightLabel = new Label("Map Height:");
        this.grid.add(mapHeightLabel, 0, 2);
        this.mapHeightTF = new TextField();
        this.grid.add(this.mapHeightTF, 1, 2);

        Label startEnergyLabel = new Label("Start Energy:");
        this.grid.add(startEnergyLabel, 0, 3);
        this.startEnergyTF = new TextField();
        this.grid.add(this.startEnergyTF, 1, 3);

        Label moveEnergyLabel = new Label("Move Energy:");
        this.grid.add(moveEnergyLabel, 0, 4);
        this.moveEnergyTF = new TextField();
        this.grid.add(this.moveEnergyTF, 1, 4);

        Label plantEnergyLabel = new Label("Plant Energy:");
        this.grid.add(plantEnergyLabel, 0, 5);
        this.plantEnergyTF = new TextField();
        this.grid.add(this.plantEnergyTF, 1, 5);

        Label jungleRatioLabel = new Label("Jungle Ratio:");
        this.grid.add(jungleRatioLabel, 0, 6);
        this.jungleRatioTF = new TextField();
        this.grid.add(this.jungleRatioTF, 1, 6);

        Label startingAnimalsLabel = new Label("Starting Animals:");
        this.grid.add(startingAnimalsLabel, 0, 7);
        this.startAnimalsTF = new TextField();
        this.grid.add(this.startAnimalsTF, 1, 7);

        Label startGrassLabel = new Label("Start Grass:");
        this.grid.add(startGrassLabel, 0, 8);
        this.startGrassTF = new TextField();
        this.grid.add(this.startGrassTF, 1, 8);

        Label minEnergyToBreedLabel = new Label("Min Energy to Breed:");
        this.grid.add(minEnergyToBreedLabel, 0, 9);
        this.minEnergyToBreedTF = new TextField();
        this.grid.add(this.minEnergyToBreedTF, 1, 9);

        Label renderDelayLabel = new Label("Render Delay (ms):");
        this.grid.add(renderDelayLabel, 0, 10);
        this.renderDelayTF = new TextField();
        this.grid.add(this.renderDelayTF, 1, 10);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        this.grid = new GridPane();
    }


    @Override
    public void start(Stage primaryStage) {
        TextField movesInput = new TextField();
        Button startButton = new Button("Start!");
        VBox appBox = new VBox(this.grid, startButton);
        this.grid.setAlignment(Pos.CENTER);
        appBox.setAlignment(Pos.CENTER);
        movesInput.setMaxWidth(50);

        getArguments();

        Scene scene = new Scene(appBox, 1600, 900);
        primaryStage.setScene(scene);
        primaryStage.show();

        startButton.setOnAction(ev -> {
            this.map = new DarwinMap(
                    Integer.parseInt(mapWidthTF.getText()),
                    Integer.parseInt(mapHeightTF.getText()),
                    Integer.parseInt(startEnergyTF.getText()),
                    Integer.parseInt(moveEnergyTF.getText()),
                    Integer.parseInt(plantEnergyTF.getText()),
                    Double.parseDouble(jungleRatioTF.getText()),
                    Integer.parseInt(startAnimalsTF.getText()),
                    Integer.parseInt(startGrassTF.getText()),
                    Integer.parseInt(minEnergyToBreedTF.getText())
            );
            this.engine = new SimulationEngine(this.map, Integer.parseInt(renderDelayTF.getText()));
            this.grid.getChildren().clear();
            this.engine.run();
        });
    }

    public void draw() {
        this.grid.getChildren().clear();
        drawBase(30);
        drawFill();
    }

    public void drawBase(int grid_size) {
        this.grid.getColumnConstraints().clear();
        this.grid.getRowConstraints().clear();
        this.grid.setGridLinesVisible(false);
//        grid.setGridLinesVisible(true);

        for (int i = 0; i < this.map.width; i++) {
            Label label = new Label(String.valueOf(this.map.width + i));
            this.grid.add(label, i, this.map.width);
            GridPane.setHalignment(label, HPos.CENTER);
            this.grid.getColumnConstraints().add(new ColumnConstraints(grid_size));
        }
        for (int i = 0; i < this.map.height; i++) {
            Label label = new Label(String.valueOf(this.map.height + i));
            this.grid.add(label, 0, this.map.height - i);
            GridPane.setHalignment(label, HPos.CENTER);
            this.grid.getRowConstraints().add(new RowConstraints(grid_size));
        }

//        Label xylabel = new Label("y/x");
//        grid.add(xylabel, 0, 20 + 1);
//        GridPane.setHalignment(xylabel, HPos.CENTER);
        this.grid.getRowConstraints().add(new RowConstraints(grid_size));
        this.grid.getColumnConstraints().add(new ColumnConstraints(grid_size));
    }

    public void drawFill() {
        for (int i = 0; i < this.map.height; i++) {
            for (int j = 0; j < this.map.width; j++) {
                Object object = this.map.objectAt(new Vector2d(i, j));
                if (object != null) {
                    this.grid.add(new GuiElementBox((IMapElement) object).mapElementView(), i, j);
                }
            }
        }
    }
}
