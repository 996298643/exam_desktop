package com.css.common.utils;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import javafx.stage.Stage;

public class Alert {
	
	/**
	 * @description:在stage內显示的框
	 * @param: stage
	 * @param: message显示的消息
	 * */
	public static boolean inConfirm(Stage stage, String message) {
		Dialogs response = Dialogs.create().owner(stage).title("提示").message(message)
				.actions(Dialog.ACTION_OK, Dialog.ACTION_CANCEL).styleClass(Dialog.STYLE_CLASS_CROSS_PLATFORM);
		Action action = response.showConfirm();
		if(action == Dialog.ACTION_OK) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * @description:显示的框
	 * @param: stage
	 * @param: message显示的消息
	 * */
	public static boolean confirm(String message) {
		Dialogs response = Dialogs.create().title("提示").message(message)
				.actions(Dialog.ACTION_OK, Dialog.ACTION_CANCEL).styleClass(Dialog.STYLE_CLASS_CROSS_PLATFORM);
		Action action = response.showConfirm();
		if(action == Dialog.ACTION_OK) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * @description:右下角弹出dialog
	 * */
	public static void notifi(String title, String message) {
		Notifications.create().title(title).text(message).darkStyle()
		.showConfirm();
	}
	
	
	
	
	

}
