package com.css.login;

import com.css.animation.FlipOutXTransition;
import com.css.common.utils.Alert;
import com.css.module.view.admin.AdminStage;
import com.css.module.view.prev.ExamStage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class login extends Application {
	private static double x;
	private static double y;

	@Override
	public void start(Stage stage) {
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		stage = new Stage(StageStyle.TRANSPARENT);
		try {
			init(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init(Stage stage) throws Exception {
		BorderPane mainLayou = new BorderPane();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		Parent parent = (Parent) fxmlLoader.load();
		setField(parent, stage, mainLayou);
		mainLayou.setTop(getTop(stage));
		mainLayou.setCenter(parent);
		Scene s = new Scene(mainLayou, 450, 600);
		s.setFill(Color.TRANSPARENT);
		s.getStylesheets().addAll("css/login/controls.css", "css/login/login.css", "css/login/styles.css");
		s.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				x = me.getScreenX() - stage.getX();
				y = me.getScreenY() - stage.getY();
			}
		});
		s.setOnMouseDragged(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent me) {
				stage.setX(me.getScreenX() - x);
				stage.setY(me.getScreenY() - y);
			}
		});
		s.getRoot().setId("go");
		stage.setScene(s);
		stage.show();
	}

	public void setField(Parent root, Stage stage, BorderPane mainLayou) {
		// 帐号
		TextField account = (TextField) root.lookup("#account");

		// 密码
		PasswordField pass = (PasswordField) root.lookup("#pass");

		// 确认按钮
		Button ensure = (Button) root.lookup("#ensure");
		ensure.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String actStr = account.getText();
				String passStr = pass.getText();
				if (actStr.equals("a") && passStr.equals("a")) {
					stage.close();
					AdminStage admin = new AdminStage();
				} else {
					if(actStr.length() == 11) {
						String act = actStr.substring(5, 11);
					    if(act.equals(passStr)) {
					    	System.setProperty("bh", actStr);
					    	stage.close();
					    	ExamStage exam = new ExamStage();
					    }else {
					    	Alert.notifi("提示", "密码错误");
					    }
					}else {
						Alert.notifi("提示", "编号错误");
					}
					
				}
			}
		});
	}

	public AnchorPane getTop(Stage stage) {
		AnchorPane an = new AnchorPane();
		an.setStyle("-fx-background-color:transparent;");

		Button b = new Button();
		b.setOnAction((e) -> {
			boolean vis = Alert.confirm("确定要关闭？");
			if (vis) {
				new FlipOutXTransition(stage).play();
			}
		});
		b.setMaxHeight(30);
		b.setMaxWidth(34);
		b.setMinWidth(34);
		b.setMinHeight(30);
		b.setId("close");

		Button m = new Button();
		m.setOnAction((e) -> {
			stage.setIconified(true);
		});
		m.setMaxHeight(30);
		m.setMaxWidth(34);
		m.setMinWidth(34);
		m.setMinHeight(30);
		m.setId("min");

		HBox h = new HBox();
		h.getChildren().addAll(m, b);
		an.setRightAnchor(h, 10.0);
		an.getChildren().add(h);

		return an;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
