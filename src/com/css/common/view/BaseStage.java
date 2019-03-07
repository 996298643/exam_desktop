package com.css.common.view;

import org.controlsfx.control.NotificationPane;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BaseStage {

	protected boolean alreadyFullScreen = false;

	protected boolean maximized = false;

	protected Scene scene;

	protected double widht = 1382;

	protected double height = 618;

	NotificationPane notificationPane = new NotificationPane();

	protected Stage primaryStage;

	public BaseStage() {
		this.primaryStage = new Stage(StageStyle.TRANSPARENT);
	}

	public BaseStage(double width, double height) {
		this.primaryStage = new Stage(StageStyle.TRANSPARENT);
		this.widht = width;
		this.height = height;
	}

	public void initView(Pane pane) {
		this.notificationPane.getStyleClass().addAll(
				NotificationPane.STYLE_CLASS_DARK);
		this.notificationPane.showFromTopProperty().set(false);
		this.notificationPane.setContent(pane);
		setNotiDrag(this.notificationPane, this.primaryStage);
		this.scene = new Scene(this.notificationPane, this.widht, this.height);
		this.scene.setFill(Color.TRANSPARENT);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {;
				if (event.getCode() == KeyCode.ESCAPE) {
					event.consume();
					notificationPane.setText("你以退出全屏状态，按F5键重新进入全屏。");
					notificationPane.show();
					scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

						public void handle(MouseEvent event) {
							if (event.getClickCount() == 2) {
								toogleMaximized(primaryStage);
							}
						}
					});
					if (alreadyFullScreen)
						alreadyFullScreen = false;

				} else if (event.getCode() == KeyCode.F5) {
					event.consume();
					primaryStage.setFullScreen(true);
					alreadyFullScreen = true;
					notificationPane.setText("你以进入全屏状态");
					notificationPane.show();
					scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

						public void handle(MouseEvent event) {

						}
					});

				}
			};
		});
		this.scene.getRoot().setId("background");
		this.scene.getRoot().setEffect(new DropShadow());
		this.scene.getStylesheets().add("css/default.css");
		this.primaryStage.setScene(scene);
	}

	public void show() {
		this.primaryStage.show();
	}
	
	public void close() {
		this.primaryStage.close();
	}

	/**
	 * 设在notificationPane可以拖动
	 */
	private static double x;
	private static double y;

	public void setNotiDrag(NotificationPane notificationPane, Stage stage) {
		notificationPane.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				if (!maximized && !alreadyFullScreen) {
					x = me.getScreenX() - stage.getX();
					y = me.getScreenY() - stage.getY();
				}
			}
		});

		notificationPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				if (!maximized && !alreadyFullScreen) {
					stage.setX(me.getScreenX() - x);
					stage.setY(me.getScreenY() - y);
				}
			}
		});

		notificationPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
	
	public void replaceCss(String filePath) {      //清除全部css并添加新的Css文件
		int size = this.scene.getStylesheets().size();
		for(int i = 0 ; i < size; i++) {
			this.scene.getStylesheets().remove(i);
		}
		this.scene.getStylesheets().add(filePath);
	}

	
	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}

