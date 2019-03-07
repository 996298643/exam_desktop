package com.css.animation;

import com.fxexperience.javafx.animation.CachedTimelineTransition;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TimelineBuilder;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FadeInUpTransition extends CachedTimelineTransition {
	private Stage node;

	public FadeInUpTransition(Stage stage) {
		super(stage.getScene().getRoot(), TimelineBuilder
				.create()
				.keyFrames(
						new KeyFrame[] {
								new KeyFrame(Duration.millis(0.0D),
										new KeyValue[] {
												new KeyValue(stage.getScene()
														.getRoot()
														.opacityProperty(),
														Integer.valueOf(0),
														WEB_EASE),
												new KeyValue(stage.getScene()
														.getRoot()
														.translateYProperty(),
														Integer.valueOf(20),
														WEB_EASE) }),
								new KeyFrame(Duration.millis(1000.0D),
										new KeyValue[] {
												new KeyValue(stage.getScene()
														.getRoot()
														.opacityProperty(),
														Integer.valueOf(1),
														WEB_EASE),
												new KeyValue(stage.getScene()
														.getRoot()
														.translateYProperty(),
														Integer.valueOf(0),
														WEB_EASE) }) }).build());
		node = stage;
		setCycleDuration(Duration.seconds(1.0D));
		setDelay(Duration.seconds(0.2D));
	
	}

	protected void starting() {
		super.starting();
		this.node.getScene().getRoot().setRotationAxis(Rotate.X_AXIS);
		this.node.getScene().setCamera(new PerspectiveCamera());
		if(node.isShowing())
		{
			
		}
		else
		{
			
			try {
				node.show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void stopping() {
		super.stopping();
	}
}