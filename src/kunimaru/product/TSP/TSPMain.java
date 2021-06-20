package kunimaru.product.TSP;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
public class TSPMain extends Application{
	private Canvas cv;
	private VBox vb;
	private Button bt;
	private CheckBox greedy;
	private CheckBox IterativeImprove;
	private CheckBox SimAnn;
	private GraphicsContext gc;
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		greedy=new CheckBox("Greedy法");
		IterativeImprove =new CheckBox("反復改善法");
		SimAnn=new CheckBox("Simulated Anealing");
		vb=new VBox(5);
		bt=new Button("実行");
		cv=new Canvas(360,360);
		gc=cv.getGraphicsContext2D();
		BorderPane bp=new BorderPane();
		vb.getChildren().addAll(greedy,IterativeImprove,SimAnn,bt);
		bp.setLeft(cv);
		bp.setRight(vb);
		cv.setOnMouseClicked(new MouseClickedOnCanvas());
		cv.setOnMouseEntered(new MouseThrowPoint());
		bt.setOnAction(new ButtonClicked());
		Scene sc =new Scene(bp);
		stage.setScene(sc);
		stage.setWidth(640);
		stage.setHeight(640);
		stage.setTitle("TSP_Simulator");
		stage.show();
	}
	class MouseClickedOnCanvas implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent arg0) {
			// TODO 自動生成されたメソッド・スタブ
			//キャンバスでマウスがクリックされたときの処理
			//巡回する点を追加する．
			/*
			 * 各点の距離を計算するのはこのタイミングカモ
			 */
		}
	}
	class ButtonClicked implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			// TODO 自動生成されたメソッド・スタブ
			//実行ボタンがクリックされたときの処理
			/*
			 * チェックボックスを調べて使用するアルゴリズムを確認
			 * 
			 */
		}
	}
	class MouseThrowPoint implements EventHandler<MouseEvent>{
		@Override
		public void handle(MouseEvent arg0) {
			// TODO 自動生成されたメソッド・スタブ
			//マウスが点の上に来た時の処理．
			/*
			 * マウスの座標と点の座標を比べて，のっかっているものがあれば強調表示．
			 */
		}
	}
}
