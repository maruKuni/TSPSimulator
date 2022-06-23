package application;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;

import java.net.*;
import java.util.*;

import javafx.event.*;

public class TSPController implements Initializable {
    private enum Algorithm {
        SA,
    };

    @FXML
    private CheckBox CheckBoxSA;
    @FXML
    private Slider SliderInitialTemper, SliderSAmultipler;
    @FXML
    private TextField textFieldSAIterate;
    @FXML
    private ColorPicker colorPickerSA;
    @FXML
    private Canvas canvasMain;
    @FXML
    private Button buttonDo, buttonPointReset, buttonConfigReset, buttonAllReset;

    private GraphicsContext gc;
    private double CANVAS_WIDTH, CANVAS_HEIGHT;
    ArrayList<Point> points;

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
        final double x = e.getX();
        final double y = e.getY();
        System.out.println(x + ", " + y);
        points.add(new Point(x, y));
        gc.setFill(Color.BLACK);
        gc.fillOval(x - 5, y - 5, 10, 10);
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

    }

    @FXML
    protected void handlePointResetPressed(ActionEvent e) {

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
}
