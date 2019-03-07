package com.css.module.view.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.css.common.utils.Alert;
import com.css.common.utils.CoreUtil;
import com.css.common.utils.ExcelUtils;
import com.css.common.view.CssStage;
import com.css.common.view.DialogStage;
import com.css.module.model.Kslb;
import com.css.module.model.Kstm;
import com.css.module.model.Tmxxlb;
import com.css.module.service.AdminService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class AdminStage {

	private List<ChoiceBox<Tmxxlb>> choices = new ArrayList<ChoiceBox<Tmxxlb>>();

	public AdminStage() {
		CssStage stage = new CssStage();
		StackPane stack = new StackPane();
		TabPane tabs = new TabPane();

		Tab tab1 = new Tab("导入题库");
		tab1.setClosable(false);
		tab1.setContent(ImportPane(stage));

		Tab tab2 = new Tab("考试结果");
		tab2.setClosable(false);

		Tab tab3 = new Tab("题目选项类别");
		tab3.setContent(createTmxxlb(stage));
		tab3.setClosable(false);

		tabs.getTabs().addAll(tab1, tab2, tab3);

		stack.getChildren().add(tabs);
		stage.setView(stack, 1038, 642);
	}

	public Pane createTmxxlb(Stage stage) {
		StackPane stack = new StackPane();
		ScrollPane scroll = new ScrollPane();

		VBox v = new VBox();
		v.setStyle("-fx-padding:20px 20px 20px 20px;");
		HBox tool = new HBox();
		tool.setStyle("-fx-padding:10px 10px 10px 10px;");
		tool.setAlignment(Pos.BASELINE_CENTER);
		tool.setSpacing(10);
		Button addBtn = new Button("添加类别");
		addBtn.setOnMouseClicked((e) -> {
			DialogStage dlg = new DialogStage(1038, 618);
			StackPane sp = new StackPane();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("xxlb.fxml"));
			try {
				Parent parent = (Parent) fxmlLoader.load();
				StackPane.setAlignment(parent, Pos.CENTER);
				sp.getChildren().add(parent);
				dlg.setView(sp);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		tool.getChildren().add(addBtn);
		v.getChildren().add(tool);

		HBox h = new HBox();
		h.setStyle("-fx-padding:10px 10px 10px 10px;");
		h.setAlignment(Pos.BASELINE_CENTER);
		h.setSpacing(10);
		List<Tmxxlb> lbs = AdminService.selectTmxxlbAll();
		int i = 0;
		for (Tmxxlb lb : lbs) {
			if (i % 6 == 0) {
				v.getChildren().add(h);
				h = new HBox();
				h.setStyle("-fx-padding:10px 10px 10px 10px;");
				h.setAlignment(Pos.BASELINE_CENTER);
				h.setSpacing(10);
			}
			Button b = new Button("管理" + lb.getLbmc());
			b.setOnMouseClicked((e) -> {
				DialogStage dlg = new DialogStage(1038, 618);
				StackPane sp = new StackPane();
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tmxx.fxml"));
				try {
					Parent parent = (Parent) fxmlLoader.load();
					StackPane.setAlignment(parent, Pos.CENTER);
					sp.getChildren().add(parent);
					dlg.setView(sp);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			h.getChildren().add(b);
			i++;
		}
		v.getChildren().add(h);

		scroll.setContent(v);
		stack.getChildren().add(scroll);

		return stack;
	}

	public Pane ImportPane(Stage stage) {
		VBox v = new VBox();
		v.setStyle("-fx-padding:20px 20px 20px 20px;");
		HBox h = new HBox();
		h.setStyle("-fx-padding:10px 10px 10px 10px;");
		h.setAlignment(Pos.BASELINE_CENTER);
		h.setSpacing(10);
		List<Kslb> kslbs = AdminService.selectKslbAll();
		int i = 0;
		for (Kslb kslb : kslbs) {
			if (i % 2 == 0) {
				v.getChildren().add(h);
				h = new HBox();
				h.setStyle("-fx-padding:10px 10px 10px 10px;");
				h.setAlignment(Pos.BASELINE_CENTER);
				h.setSpacing(10);
			}
			h.getChildren().add(createImportBtn("导入" + kslb.getLbmc(), kslb.getId(), stage));
			i++;
		}
		v.getChildren().add(h);
		return v;
	}

	public Button createImportBtn(String mc, String tklbid, Stage stage) {
		Button button = new Button(mc);
		button.setMaxWidth(138.2);
		button.setMinWidth(138.2);
		button.getStyleClass().add("btn");
		button.setOnMouseClicked((e) -> {
			FileChooser choose = new FileChooser();
			choose.setTitle(mc);
			File file = choose.showOpenDialog(stage);
			if (file != null) {
				List<String> list = ExcelUtils.importColumn(file);
				createImportDialog(list, tklbid);
			}
		});
		return button;
	}

	public void createImportDialog(List<String> list, String tklbid) {
		DialogStage dialog = new DialogStage(1038, 618);
		StackPane stack = new StackPane();
		ScrollPane sp = new ScrollPane();
		Map<String, ChoiceBox<Tmxxlb>> maps = new HashMap<String, ChoiceBox<Tmxxlb>>();
		VBox v = new VBox();
		v.setSpacing(10);
		ObservableList<Tmxxlb> obsers = FXCollections.observableArrayList();
		HBox allBtnCon = new HBox();
		List<Tmxxlb> lbs = AdminService.selectTmxxlbAll();
		for (Tmxxlb lb : lbs) {
			obsers.add(lb);
			Button allBtn = new Button("全部选择"+lb.getLbmc());
			allBtn.setOnMouseClicked((e)->{
				for(ChoiceBox<Tmxxlb>  choice : choices) {
					choice.setValue(lb);
				}
			});
			allBtnCon.getChildren().add(allBtn);
		}
		
		v.getChildren().add(allBtnCon);

		ChoiceBox<Tmxxlb> xxlb;
		for (String s : list) {
			System.out.println(s);
			xxlb = new ChoiceBox<Tmxxlb>(obsers);
			this.choices.add(xxlb);
			xxlb.setValue(obsers.get(0));
			xxlb.setItems(obsers);
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
			maps.put(s, xxlb);
		}

		int cnt = 1;
		for (String s : list) {
			HBox h = new HBox();
			h.setSpacing(13.8);
			Label l = new Label(cnt+"."+s);
			h.getChildren().add(l);
			ChoiceBox<Tmxxlb> choice = maps.get(s);
			h.getChildren().add(choice);
			maps.get(s);
			v.getChildren().add(h);
			cnt++;
		}

		sp.setContent(v);
		stack.getChildren().add(sp);

		HBox h = new HBox();
		Button ensure = new Button("确认导入");
		ensure.setOnMouseClicked((e) -> {
			AdminService.deleteKstmByKslbid(tklbid);
			int i = 1;
			for (String s : list) {
				Kstm tm = new Kstm();
				tm.setId(CoreUtil.uuid32());
				tm.setKstmmc(s);
				tm.setKstmxh(String.valueOf(i));
				tm.setKslbid(tklbid);
				tm.setTMXXLBID(maps.get(s).getValue().getId());
				i++;
				AdminService.saveKstm(tm);
			}
			Alert.notifi("提示", "导入成功");
		});

		h.getChildren().add(ensure);

		v.getChildren().add(h);

		dialog.setView(stack);

	}

}
