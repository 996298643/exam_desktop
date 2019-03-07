package com.css.common.view;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import com.css.animation.FlipOutXTransition;
import com.css.common.component.WindowResizeButton;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DialogStage extends Stage {
	private DialogStage stage;
	protected boolean alreadyFullScreen = false;
	protected boolean maximized = false;
	protected WindowResizeButton windowResizeButton;
	protected BorderPane root;
	protected Scene scene;
	protected double width;
	protected double height;

	public DialogStage(double width, double height) {
		this.width = width;
		this.height = height;
		init();
	}

	public void init() {
		stage = this;
		this.initStyle(StageStyle.UNDECORATED);
		// create window resize button
		windowResizeButton = new WindowResizeButton(stage, this.width, this.height);
		root = new BorderPane() {
			@Override
			protected void layoutChildren() {
				super.layoutChildren();
				windowResizeButton.autosize();
				windowResizeButton.setLayoutX(getWidth() - windowResizeButton.getLayoutBounds().getWidth());
				windowResizeButton.setLayoutY(getHeight() - windowResizeButton.getLayoutBounds().getHeight());
			}
		};
		this.root.setTop(createTool(stage));
		this.root.setBottom(createBottom());
	}

	public void setView(Pane pane) {
		root.setCenter(pane);
		root.getCenter().setId("center");
		windowResizeButton.setManaged(false);
		this.root.getChildren().add(windowResizeButton);
		this.scene = new Scene(root, this.width, this.height);
		this.scene.setFill(Color.TRANSPARENT);
		this.scene.getRoot().setId("background");
		this.scene.getRoot().setEffect(new DropShadow());
		this.scene.getStylesheets().addAll("css/default.css");
		setSceneDrag(scene, this);
		this.setScene(scene);
		this.show();
	}

	/**
	 * 设在notificationPane可以拖动
	 */
	private static double x;
	private static double y;
	public void setSceneDrag(Scene scene, Stage stage) {
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				if (!maximized && !alreadyFullScreen) {
					x = me.getScreenX() - stage.getX();
					y = me.getScreenY() - stage.getY();
				}
			}
		});

		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				if (!maximized && !alreadyFullScreen) {
					stage.setX(me.getScreenX() - x);
					stage.setY(me.getScreenY() - y);
				}
			}
		});

		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					toogleMaximized(stage);
				}
			}
		});
	}
	
	public StackPane createBottom() {
		StackPane stack = new StackPane();
	   stack.setMaxHeight(25);
	   stack.setMinHeight(25);
		return stack;
	}

	/**
	 * 设在关闭,最大化,最小化按钮
	 */
	public StackPane createTool(Stage stage) {
		StackPane p = new StackPane();
		p.setId("top");
		HBox ltool = new HBox();
		ltool.setStyle("-fx-padding: 0px 0px 0px 0px;");
		ltool.setAlignment(Pos.TOP_LEFT);
		Text t = new Text("罪犯评测系统");
		t.setFill(Color.WHITE);
		t.setFont(Font.font(null, FontWeight.BOLD, 18));
		t.setY(10.0);
		t.setFontSmoothingType(FontSmoothingType.LCD);
		t.setTextOrigin(VPos.BASELINE);
		ltool.getChildren().add(t);
		
		HBox rtool = new HBox();
		rtool.setStyle("-fx-padding: 0px 0px 0px 0px;");
		rtool.setAlignment(Pos.TOP_RIGHT);
		rtool.getChildren().addAll(createMinBtn(stage), createMaxBtn(stage), createCloseBtn(stage));
		p.getChildren().addAll(ltool, rtool);
		return p;
	}

	/**
	 * 创建关闭按钮
	 */
	public Button createCloseBtn(Stage stage) {
		Button close = new Button();
		close.setOnAction((e) -> {	
				stage.close();
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
			toogleMaximized(stage);
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

	/**
	 * 设在窗口最大化
	 */
	private Rectangle2D backupWindowBounds = null; // 零时存储stage位置u和高宽

	public void toogleMaximized(Stage stage) {
		final Screen screen = Screen.getScreensForRectangle(stage.getX(), stage.getY(), 1, 1).get(0);
		if (maximized) {
			maximized = false;
			if (backupWindowBounds != null) {
				stage.setX(backupWindowBounds.getMinX());
				stage.setY(backupWindowBounds.getMinY());
				stage.setWidth(backupWindowBounds.getWidth());
				stage.setHeight(backupWindowBounds.getHeight());
			}
		} else {
			maximized = true;
			backupWindowBounds = new Rectangle2D(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight());
			stage.setX(screen.getVisualBounds().getMinX());
			stage.setY(screen.getVisualBounds().getMinY());
			stage.setWidth(screen.getVisualBounds().getWidth());
			stage.setHeight(screen.getVisualBounds().getHeight());
		}
	}
}
