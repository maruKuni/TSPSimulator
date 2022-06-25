package application;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;

import java.net.*;
import java.util.*;
import java.util.function.Consumer;

import application.algorithm.SimulatedAnnealing;
import javafx.event.*;

public class TSPController implements Initializable {

    @FXML
    private CheckBox CheckBoxSA;
    @FXML
    private Slider SliderInitialTemper, SliderSAmultipler;
    @FXML
    private TextField textFieldSAIterate, textFieldStep;
    @FXML
    private ColorPicker colorPickerSA;
    @FXML
    private Canvas canvasMain;
    @FXML
    private Button buttonDo, buttonPointReset, buttonConfigReset, buttonAllReset;

    private GraphicsContext gc;
    private double CANVAS_WIDTH, CANVAS_HEIGHT;
    ArrayList<Point> points;
    SimulatedAnnealing SAInstance = null;
    private double weight[][] = null;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        gc = canvasMain.getGraphicsContext2D();
        CANVAS_HEIGHT = canvasMain.getHeight();
        CANVAS_WIDTH = canvasMain.getWidth();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        points = new ArrayList<>();
    }

    @FXML
    protected void handleCanvasClicked(MouseEvent e) {
        weight = null;
        final double x = e.getX();
        final double y = e.getY();
        points.add(new Point(x, y));
        gc.setFill(Color.BLACK);
        gc.fillOval(x - 2, y - 2, 4, 4);
        gc.setFill(Color.WHITE);
    }

    @FXML
    protected void handleSACheckBoxSelected(ActionEvent e) {
        if (CheckBoxSA.isSelected()) {
            enableSAConfig();
        } else {
            disableSAConfig();
        }
    }

    @FXML
    protected void handleDoPressed(ActionEvent e) {
        int route[];
        if (weight == null) {
            weight = new double[points.size()][points.size()];
            for (int i = 0; i < points.size(); i++) {
                for (int j = i; j < points.size(); j++) {
                    Point t1 = points.get(i), t2 = points.get(j);
                    weight[i][j] = weight[j][i] = Math.sqrt(
                            Math.pow(t1.getX() - t2.getX(), 2.0) + Math.pow(t1.getY() - t2.getY(), 2.0));
                }
            }
            SAInstance = new SimulatedAnnealing(points, weight, SliderInitialTemper.getValue(),
                    SliderSAmultipler.getValue(),
                    Integer.parseInt(textFieldSAIterate.getText()));
            disableSAConfig();
        }
        route = SAInstance.search(Integer.parseInt(textFieldStep.getText()));
        drawPath(route);
    }

    @FXML
    protected void handlePointResetPressed(ActionEvent e) {
        points.clear();
        weight = null;
        clearCanvas();
    }

    @FXML
    protected void handleConfigResetPressed(ActionEvent e) {

    }

    @FXML
    protected void handleAllResetPressed(ActionEvent e) {

    }

    private void disableSAConfig() {
        SliderInitialTemper.setDisable(true);
        SliderSAmultipler.setDisable(true);
        textFieldSAIterate.setDisable(true);
        colorPickerSA.setDisable(true);
    }

    private void enableSAConfig() {
        SliderInitialTemper.setDisable(false);
        SliderSAmultipler.setDisable(false);
        textFieldSAIterate.setDisable(false);
        colorPickerSA.setDisable(false);
    }

    private void drawPath(int[] route) {
        assert route.length == points.size();
        clearCanvas();
        redrawPoints();
        gc.setStroke(colorPickerSA.getValue());
        for (int i = 0; i < points.size(); i++) {
            drawEdge(route[i], route[(i + 1) % route.length]);
        }
    }

    private void drawEdge(int start, int end) {
        Point s = points.get(start), e = points.get(end);
        gc.strokeLine(s.getX(), s.getY(), e.getX(), e.getY());
    }

    private void redrawPoints() {
        gc.setFill(Color.BLACK);
        final Consumer<Point> drawPoint = p -> gc.fillOval(p.getX() - 5, p.getY() - 5, 10, 10);
        points.stream().forEach(drawPoint);
    }

    private void clearCanvas() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
    }

    private void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d%c", array[i], (i == array.length - 1) ? '\n' : ',');
        }
    }
}
