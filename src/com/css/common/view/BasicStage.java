package com.css.common.view;

import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.DepthTest;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BasicStage {

	protected boolean alreadyFullScreen = false;

	protected boolean maximized = false;

	protected Scene scene;

	protected double widht = 1382;

	protected double height = 618;

	protected Stage primaryStage;

	public BasicStage() {
		this.primaryStage = new Stage(StageStyle.UNDECORATED);
	}

	public BasicStage(double width, double height) {
		this.primaryStage = new Stage(StageStyle.UNDECORATED);
		this.widht = width;
		this.height = height;
	}

	public void initView(BorderPane pane) {
		this.scene = new Scene(pane, 1020, 700);
		this.scene.setFill(Color.TRANSPARENT);
		this.scene.getRoot().setId("background");
		this.scene.getRoot().setEffect(new DropShadow());
//		setSceneDrag(scene, this.primaryStage);
		this.primaryStage.setScene(scene);
	}

	public void show() {
		this.primaryStage.show();
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

	public void replaceCss(String filePath) { // 清除全部css并添加新的Css文件
		int size = this.scene.getStylesheets().size();
		for (int i = 0; i < size; i++) {
			this.scene.getStylesheets().remove(i);
		}
		this.scene.getStylesheets().add(filePath);
	}
}
