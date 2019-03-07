package com.css.animation;

import com.fxexperience.javafx.animation.CachedTimelineTransition;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TimelineBuilder;
import javafx.scene.Camera;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FlipOutXTransition extends CachedTimelineTransition {
	private Camera oldCamera;
	 private Stage stage;
	public FlipOutXTransition(Stage node) {
		super(node.getScene().getRoot(), TimelineBuilder
				.create()
				.keyFrames(
						new KeyFrame[] {
								new KeyFrame(Duration.millis(0.0D),
										new KeyValue[] {
												new KeyValue(node.getScene().getRoot()
														.rotateProperty(),
														Integer.valueOf(0),
														WEB_EASE),
												new KeyValue(node
														.opacityProperty(),
														Integer.valueOf(1),
														WEB_EASE) }),
								new KeyFrame(Duration.millis(1000.0D),
										new KeyValue[] {
												new KeyValue(node.getScene().getRoot()
														.rotateProperty(),
														Integer.valueOf(-90),
														WEB_EASE),
												new KeyValue(node
														.opacityProperty(),
														Integer.valueOf(0),
														WEB_EASE) }) }).build());

		setCycleDuration(Duration.seconds(1.0D));
		setDelay(Duration.seconds(0.2D));
	}

	protected void starting() {
		super.starting();
		this.node.setRotationAxis(Rotate.X_AXIS);
		this.oldCamera = this.node.getScene().getCamera();
		this.node.getScene().setCamera(new PerspectiveCamera());
	}

	protected void stopping() {
		super.stopping();
		this.node.setRotate(0.0D);
		this.node.setRotationAxis(Rotate.Z_AXIS);
		this.node.getScene().setCamera(this.oldCamera);
		System.exit(0);
	}
}