package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
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


public class App extends Application implements IAnimalObserver {
    private DarwinMap map;
    private GridPane grid;
    private SimulationEngine engine;

    //starting parametres
    public int StartingGrass;
    public int StartingEnergy;
    public int StartingAnimals;
    public int StartingMapSize = 100;
    public int StartingJungleSize;
    public int energyGain;
    public boolean IsMagical;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        Vector2d[] positions = {new Vector2d(4, 5), new Vector2d(3,8)};
        this.map = new DarwinMap();
        this.engine = new SimulationEngine(map, positions);
        this.engine.addObserver(this);
        this.grid = new GridPane();
    }

    @Override
    public void start(Stage primaryStage) {
        TextField movesInput = new TextField();
        Button startButton = new Button("Move!");
        VBox inputBox = new VBox(movesInput, startButton);
        VBox appBox = new VBox(this.grid, inputBox);
        grid.setAlignment(Pos.CENTER);
        inputBox.setAlignment(Pos.CENTER);
        appBox.setAlignment(Pos.CENTER);
        movesInput.setMaxWidth(50);


        draw();
        Scene scene = new Scene(appBox, 1280, 960);
        primaryStage.setScene(scene);
        primaryStage.show();

//        startButton.setOnAction(ev -> {
//            String[] args = movesInput.getText().split("");
//            MoveDirection[] directions = OptionsParser.parse(args);
//            MoveDirection[] directions = {}
//            engine.setMoves(directions);
        Thread engineThread = new Thread(engine);
        engineThread.start();
//        });
    }

    public void draw() {
        this.grid.getChildren().clear();
        drawBase(grid, 800/StartingMapSize);
        drawFill(grid, map);
    }

    public void drawBase(GridPane grid, int grid_size) {
        this.grid.getColumnConstraints().clear();
        this.grid.getRowConstraints().clear();
        grid.setGridLinesVisible(false);
        grid.setGridLinesVisible(true);

        for (int i = 0; i <= StartingMapSize; i++) {
            Label label = new Label(String.valueOf(i));
            grid.add(label, i+1, StartingMapSize+1);
            GridPane.setHalignment(label, HPos.CENTER);
            grid.getColumnConstraints().add(new ColumnConstraints(grid_size));
        }
        for (int i = 0; i <= StartingMapSize; i++) {
            Label label = new Label(String.valueOf(StartingMapSize-i));
            grid.add(label, 0, i);
            GridPane.setHalignment(label, HPos.CENTER);
            grid.getRowConstraints().add(new RowConstraints(grid_size));
        }

        Label xylabel = new Label("y/x");
        grid.add(xylabel, 0, StartingMapSize+1);
        GridPane.setHalignment(xylabel, HPos.CENTER);
        grid.getRowConstraints().add(new RowConstraints(grid_size));
        grid.getColumnConstraints().add(new ColumnConstraints(grid_size));
    }

    public void drawFill(GridPane grid, DarwinMap map) {
        for (int i = 0; i <= StartingMapSize; i++) {
            for (int j = 0; j <= StartingMapSize; j++) {
                Object object = map.objectAt(new Vector2d(i, j));
                if (object != null) {
                    grid.add(new GuiElementBox((IMapElement) object).mapElementView(),
                            i+1, StartingMapSize-j);
                }
            }
        }
    }

    @Override
    public void update() {
        Platform.runLater(this::draw);
    }
}