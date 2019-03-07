

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import com.css.animation.FlipOutXTransition;
import com.css.common.component.WindowResizeButton;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.DepthTest;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainLogin extends Application {

	private WindowResizeButton windowResizeButton;
	private BorderPane root;
	private Stage stage;
	private Scene scene;

	@Override
	public void start(final Stage stage) throws Exception {
		stage.initStyle(StageStyle.UNDECORATED);
		// create window resize button
		windowResizeButton = new WindowResizeButton(stage, 1020, 682);
		root = new BorderPane() {
			@Override
			protected void layoutChildren() {
				super.layoutChildren();
				windowResizeButton.autosize();
				windowResizeButton.setLayoutX(getWidth() - windowResizeButton.getLayoutBounds().getWidth());
				windowResizeButton.setLayoutY(getHeight() - windowResizeButton.getLayoutBounds().getHeight());
			}
		};
		windowResizeButton.setManaged(false);
		this.root.getChildren().add(windowResizeButton);
		this.root.setTop(createTool(stage));
		this.root.setCenter(createTool(stage));
		scene = new Scene(root, 1020, 700);
		scene.getStylesheets().addAll("css/default.css");
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * 设在关闭,最大化,最小化按钮
	 */
	public StackPane createTool(Stage stage) {
		StackPane p = new StackPane();
		p.setId("top");
		HBox tool = new HBox();
		tool.setAlignment(Pos.TOP_RIGHT);
		tool.getChildren().addAll(createMinBtn(stage), createMaxBtn(stage), createCloseBtn(stage));
		p.setMaxHeight(26);
		p.setMinHeight(26);
		p.getChildren().add(tool);
		return p;
	}

	/**
	 * 创建关闭按钮
	 */
	public Button createCloseBtn(Stage stage) {
		Button close = new Button();
		close.setOnAction((e) -> {
			Dialogs response = Dialogs.create().owner(stage).title("提示").message("确定要关闭？")
					.actions(Dialog.ACTION_OK, Dialog.ACTION_CANCEL).styleClass(Dialog.STYLE_CLASS_CROSS_PLATFORM);

			Action go = response.showConfirm();
			if (go == Dialog.ACTION_OK) {
				new FlipOutXTransition(stage).play();
			}
		});
		close.setMaxHeight(25);
		close.setMaxWidth(26);
		close.setMinWidth(26);
		close.setMinHeight(25);
		close.setId("close");
		return close;
	}

	/**
	 * 创建最大化按钮
	 */
	public Button createMaxBtn(Stage stage) {
		Button max = new Button();
		max.setOnAction((e) -> {
//			toogleMaximized(stage);
		});
		max.setMaxHeight(25);
		max.setMaxWidth(26);
		max.setMinWidth(26);
		max.setMinHeight(25);
		max.setId("max");
		return max;
	}

	/**
	 * 创建最小化按钮
	 */
	public Button createMinBtn(Stage stage) {
		Button min = new Button();
		min.setOnAction((e) -> {
			stage.setIconified(true);
		});
		min.setMaxHeight(25);
		min.setMaxWidth(26);
		min.setMinWidth(26);
		min.setMinHeight(25);
		min.setId("min");
		return min;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
