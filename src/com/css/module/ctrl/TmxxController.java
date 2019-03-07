package com.css.module.ctrl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.css.common.utils.Alert;
import com.css.common.utils.CoreUtil;
import com.css.common.utils.StrKit;
import com.css.module.model.Tmxx;
import com.css.module.model.Tmxxlb;
import com.css.module.service.AdminService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class TmxxController implements Initializable {

	@FXML
	private TextField lbmc;

	@FXML
	private TextField bm;

	@FXML
	private Button saveXxlb;

	@FXML
	private TextField xxdm;

	@FXML
	private TextField xxmc;

	@FXML
	private ChoiceBox<Tmxxlb> xxlb;

	@FXML
	private Button savetmxx;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Tmxxlb> list = FXCollections.observableArrayList();
		List<Tmxxlb> lbs = AdminService.selectTmxxlbAll();
		for (Tmxxlb lb : lbs) {
			list.add(lb);
		}

		if (savetmxx != null) {
			xxlb.setValue(list.get(0));
			xxlb.setItems(list);
			xxlb.converterProperty().set(new StringConverter<Tmxxlb>() {
				@Override
				public String toString(Tmxxlb object) {
					return object.getLbmc();
				}

				@Override
				public Tmxxlb fromString(String string) {
					return null;
				}
			});
			
			savetmxx.setOnMouseClicked((e) -> {
				String id = xxlb.getValue().getId();
				if(StrKit.notBlank(xxdm.getText()) && StrKit.notBlank(xxmc.getText())) {
					Tmxx xx = new Tmxx();
					xx.setId(CoreUtil.uuid32());
					xx.setTMXXLBID(id);
					xx.setXXDM(xxdm.getText());
					xx.setXXMC(xxmc.getText());
					AdminService.saveTmxx(xx);
					Alert.notifi("提示", "保存成功");
				}else {
					Alert.notifi("提示", "输入框不能为空");
				}
			});
		}

		if (saveXxlb != null) {
			saveXxlb.setOnMouseClicked((e) -> {
				if (StrKit.notBlank(lbmc.getText()) && StrKit.notBlank(bm.getText())) {
					Tmxxlb xxlb = new Tmxxlb();
					xxlb.setId(CoreUtil.uuid32());
					xxlb.setLb(bm.getText());
					xxlb.setLbmc(lbmc.getText());
					AdminService.saveTmxxlb(xxlb);
					Alert.notifi("提示", "保存成功");
				} else {
					Alert.notifi("提示", "输入框不能为空");
				}
			});
		}
	}

}
