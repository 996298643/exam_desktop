package com.css.animation;

import org.controlsfx.control.NotificationPane;

import com.fxexperience.javafx.animation.CachedTimelineTransition;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TimelineBuilder;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HingeTransition extends CachedTimelineTransition {
	private Rotate rotate;
	private Stage stage;
	private NotificationPane noti = new NotificationPane();

	public HingeTransition(Stage node, NotificationPane noti) {
		super(node.getScene().getRoot(), null);
		setCycleDuration(Duration.seconds(2.0D));
		setDelay(Duration.seconds(0.2D));
		stage = node;
		noti = noti;
	}

	protected void starting() {
		super.starting();
		double endY = this.node.getScene().getHeight() - this.node.localToScene(0.0D, 0.0D).getY();
		this.rotate = new Rotate(0.0D, 0.0D, 0.0D);
		this.timeline = TimelineBuilder.create()
				.keyFrames(new KeyFrame[] {
						new KeyFrame(Duration.millis(0.0D),
								new KeyValue[] { new KeyValue(this.rotate.angleProperty(), Integer.valueOf(0),
										Interpolator.EASE_BOTH) }),
				new KeyFrame(Duration.millis(200.0D),
						new KeyValue[] { new KeyValue(this.rotate.angleProperty(), Integer.valueOf(80),
								Interpolator.EASE_BOTH) }),
				new KeyFrame(Duration.millis(400.0D),
						new KeyValue[] { new KeyValue(this.rotate.angleProperty(), Integer.valueOf(60),
								Interpolator.EASE_BOTH) }),
				new KeyFrame(Duration.millis(600.0D),
						new KeyValue[] { new KeyValue(this.rotate.angleProperty(), Integer.valueOf(80),
								Interpolator.EASE_BOTH) }),
				new KeyFrame(Duration.millis(800.0D),
						new KeyValue[] {
								new KeyValue(this.node.opacityProperty(), Integer.valueOf(1), Interpolator.EASE_BOTH),
								new KeyValue(this.node.translateYProperty(), Integer.valueOf(0),
										Interpolator.EASE_BOTH),
						new KeyValue(this.rotate.angleProperty(), Integer.valueOf(60), Interpolator.EASE_BOTH) }),
				new KeyFrame(Duration.millis(1000.0D),
						new KeyValue[] {
								new KeyValue(this.node.opacityProperty(), Integer.valueOf(0), Interpolator.EASE_BOTH),
								new KeyValue(this.node.translateYProperty(), Double.valueOf(endY),
										Interpolator.EASE_BOTH),
						new KeyValue(this.rotate.angleProperty(), Integer.valueOf(60), Interpolator.EASE_BOTH) }) })
				.build();
		this.node.getTransforms().add(this.rotate);

	}

	protected void stopping() {
		super.stopping();
		this.node.getTransforms().remove(this.rotate);
		this.node.setTranslateY(0.0D);
		stage.close();
	}
}