package com.css.module.view.prev;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.css.common.utils.Alert;
import com.css.common.utils.CoreUtil;
import com.css.common.utils.StrKit;
import com.css.common.view.BaseStage;
import com.css.module.model.Ksjg;
import com.css.module.model.Ksjgnr;
import com.css.module.model.Kstm;
import com.css.module.model.Tmxx;
import com.css.module.model.Tmxxlb;
import com.css.module.service.AdminService;
import com.css.module.service.ExamService;

import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TestStage extends BaseStage {

	private String title;

	private String kslb;

	private Map<String, Button> maps = new HashMap<String, Button>();

	private Map<String, Kstm> kstmaps = new HashMap<String, Kstm>();

	public TestStage(String title, String kslb) {
		super();
		this.title = title;
		this.kslb = kslb;
		initView(createExamPane(this));
		getPrimaryStage().setFullScreen(true);
		show();
	}

	public StackPane createExamPane(BaseStage stage) {
		List<Kstm> kstms = AdminService.selectKstmKslbid(this.kslb);

		StackPane stack = new StackPane();
		BorderPane root = new BorderPane();

		root.setTop(topPane());

		root.setLeft(leftPane());

		BorderPane centerPane = new BorderPane();

		ScrollPane scroll = rightPane(kstms, centerPane);

		root.setRight(scroll);

		centerPane.setCenter(createExamPane(kstmaps.get("1"), maps.get("1"), centerPane));
		
		root.setCenter(centerPane);
		
		stack.getChildren().add(root);
		return stack;
	}

	public HBox topPane() {
		HBox hbox = new HBox();
		hbox.setStyle("-fx-background-color:rgb(30.0,170.0,255.0)");
		hbox.setSpacing(50);
		hbox.setAlignment(Pos.CENTER);
		Text t = new Text(this.title);
		Light.Point light = new Light.Point();
		light.setX(100);
		light.setY(100);
		light.setZ(50);

		Lighting lighting = new Lighting();
		lighting.setLight(light);
		lighting.setSurfaceScale(5.0);
		t.setFill(Color.STEELBLUE);
		t.setFont(javafx.scene.text.Font.font(null, FontWeight.BOLD, 20));
		t.setX(10.0);
		t.setY(10.0);

		t.setEffect(lighting);
		t.setEffect(lighting);
		// t.setId("fancytext");
		t.setFontSmoothingType(FontSmoothingType.LCD);
		t.setTextOrigin(VPos.TOP);
		hbox.getChildren().add(t);
		return hbox;
	}

	public VBox leftPane() {
		VBox vertical = new VBox();
		vertical.setStyle("-fx-width:50%;");
		vertical.setAlignment(Pos.CENTER);
		Image image = new Image("/image/sword.png");
		ImageView view = new ImageView(image);
		Button closeBtn = new Button("退出", view);
		closeBtn.setTooltip(new Tooltip("退出"));
		closeBtn.setOnMouseClicked((e) -> {
			this.close();
		});
		vertical.getChildren().add(closeBtn);
		return vertical;
	}

	public ScrollPane rightPane(List<Kstm> kstms, BorderPane centerPane) {
		ScrollPane scroll = new ScrollPane();
		VBox colume = new VBox();
		colume.setStyle("-fx-width:50%;");
		colume.setAlignment(Pos.CENTER);
		colume.setSpacing(6.18);
		int i = 0;
		HBox row = new HBox();
		row.setSpacing(13.82);
		String tmpbh = System.getProperty("bh");
		Ksjg tmpksjg = ExamService.selectKsjgByBhAndKslbbm(tmpbh, this.kslb);
		for (Kstm kstm : kstms) {
			if (i % 6 == 0) {
				colume.getChildren().add(row);
				row = new HBox();
				row.setSpacing(6.18);
			}
			Button btn = new Button(kstm.getKstmxh());
			btn.setMaxWidth(45);
			btn.setMinWidth(45);
			Ksjgnr tmpnr = ExamService.selectKsjgnrByTxh(tmpksjg.getId(), kstm.getKstmxh());
			if (StrKit.notBlank(tmpnr.getId())) {
				btn.setStyle("-fx-color:rgb(30.0,170.0,255.0);");
			}
			btn.setOnMouseClicked((e) -> {
				initBtnEvent(kstm, btn, centerPane);
			});
			this.maps.put(kstm.getKstmxh(), btn);
			this.kstmaps.put(kstm.getKstmxh(), kstm);
			row.getChildren().add(btn);
			i++;
		}
		colume.getChildren().add(row);
		Button commit = new Button("交卷");
		commit.setOnMouseClicked((e) -> {
			String bh = System.getProperty("bh");

			List<Ksjgnr> ksjgnrs = ExamService.selectKsjgnrByKsjgid(tmpksjg.getId());
			String jgstr = "";
			for (Ksjgnr ksjgnr : ksjgnrs) {
				if (StrKit.notBlank(ksjgnr.getKtda())) {
					jgstr += ksjgnr.getKtxh();
				}
			}

			String tmstr = "";
			List<Kstm> tmpkstms = AdminService.selectKstmKslbid(this.kslb);
			for (Kstm kstm : tmpkstms) {
				tmstr += kstm.getKstmxh();
			}

			if (tmstr.equals(jgstr)) {
				ExamService.updateKsjgSfwc("1", tmpksjg.getId());
				Alert.notifi("提示", "答题完成谢谢！");
				createSaveDialog(this.primaryStage, bh + tmpksjg.getKsmc() + "结果", bh + tmpksjg.getKsmc() + "结果.xls",
						ksjgnrs);
			} else {
				Alert.notifi("提示", "请选择完所有题目谢谢！");
			}
		});
		commit.setStyle("-fx-padding:20px;");
		commit.getStyleClass().add("btn");
		colume.getChildren().add(commit);
		scroll.setContent(colume);
		return scroll;
	}

	public void initBtnEvent(Kstm kstm, Button btn, BorderPane centerPane) {
		centerPane.setCenter(createExamPane(kstm, btn, centerPane));
	}

	public VBox createExamPane(Kstm kstm, Button btn, BorderPane centerPane) {
		String tmpbh = System.getProperty("bh");
		Ksjg tmpksjg = ExamService.selectKsjgByBhAndKslbbm(tmpbh, this.kslb);
		Ksjgnr tmpnr = ExamService.selectKsjgnrByTxh(tmpksjg.getId(), kstm.getKstmxh());
		Tmxxlb kstmlb2 = AdminService.selectTmxxlbId(kstm.getTMXXLBID());
		List<Tmxx> tmxxs2 = AdminService.selectByTmxxlbid(kstmlb2.getId());

		VBox tmxxtool2 = new VBox();
		tmxxtool2.setSpacing(38.2);
		Label label = new Label(kstm.getKstmxh() + "." + kstm.getKstmmc());
		label.setStyle("-fx-font-size: 22;");
		tmxxtool2.getChildren().add(label);
		tmxxtool2.setAlignment(Pos.CENTER);
		ToggleGroup group2 = new ToggleGroup();
		group2.selectedToggleProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null) {
				String da = newValue.getUserData().toString();
				String bh = System.getProperty("bh");
				Ksjg ksjg = ExamService.selectKsjgByBhAndKslbbm(bh, this.kslb);
				Ksjgnr nr = ExamService.selectKsjgnrByTxh(ksjg.getId(), kstm.getKstmxh());
				if (StrKit.notBlank(nr.getId())) {
					nr.setKtda(da);
					ExamService.updateKsjgnrByTxh(nr);
				} else {
					nr = new Ksjgnr();
					nr.setId(CoreUtil.uuid32());
					nr.setKsjgid(ksjg.getId());
					nr.setKtda(da);
					nr.setKtmc(kstm.getKstmmc());
					nr.setKtxh(kstm.getKstmxh());
					ExamService.saveKsjgnr(nr);
					btn.setStyle("-fx-color:rgb(30.0,170.0,255.0);");
				}

			}
		});
		if (StrKit.notBlank(tmpnr.getId())) {
			for (Tmxx t : tmxxs2) {
				RadioButton rb = new RadioButton(t.getXXDM() + "." + t.getXXMC());
				if (t.getXXDM().equals(tmpnr.getKtda())) {
					rb.setSelected(true);
				}
				rb.setUserData(t.getXXDM());
				rb.setToggleGroup(group2);
				tmxxtool2.getChildren().add(rb);
			}
		} else {
			for (Tmxx t : tmxxs2) {
				RadioButton rb = new RadioButton(t.getXXDM() + "." + t.getXXMC());
				rb.setUserData(t.getXXDM());
				rb.setToggleGroup(group2);
				tmxxtool2.getChildren().add(rb);
			}
		}
		HBox bottom = new HBox();
		bottom.setSpacing(61.8);
		bottom.setAlignment(Pos.BOTTOM_CENTER);
		Image previmage = new Image("/image/prev.png");
		ImageView prevview = new ImageView(previmage);
		Button prevBtn = new Button("", prevview);
		prevBtn.getStyleClass().add("pnbtn");
		prevBtn.setOnMouseClicked((e) -> {
			int xh = Integer.parseInt(kstm.getKstmxh());
			if (xh != 1) {
				xh = xh - 1;
				Button ksbtn = maps.get(String.valueOf(xh));
				Kstm kskstm = kstmaps.get(String.valueOf(xh));
				if (ksbtn != null && kskstm != null) {
					centerPane.setCenter(createExamPane(kskstm, ksbtn, centerPane));
				}
			}

		});

		Image nextimage = new Image("/image/next.png");
		ImageView nextview = new ImageView(nextimage);
		Button nextBtn = new Button("", nextview);
		nextBtn.getStyleClass().add("pnbtn");
		nextBtn.setOnMouseClicked((e) -> {
			int xh = Integer.parseInt(kstm.getKstmxh());
			xh = xh + 1;
			Button ksbtn = maps.get(String.valueOf(xh));
			Kstm kskstm = kstmaps.get(String.valueOf(xh));
			if (ksbtn != null && kskstm != null) {
				centerPane.setCenter(createExamPane(kskstm, ksbtn, centerPane));
			}
		});
		bottom.getChildren().addAll(prevBtn, nextBtn);
		tmxxtool2.getChildren().add(bottom);
		return tmxxtool2;

	}

	/**
	 * 创建保存导出文件对话框
	 * 
	 * @param: title=对话框标题,initName=导出文件默认名字
	 */
	public void createSaveDialog(Stage stage, String title, String initName, List<Ksjgnr> ksjgnrs) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		fileChooser.setInitialFileName(initName);
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XLS Files", "*.xls"));
		File file = fileChooser.showSaveDialog(stage);
		if (file != null) {
			exportExcel(file.getAbsolutePath(), ksjgnrs);
		}
	}

	/**
	 * 把文件内容导入到文件中
	 * 
	 * @param: filePaht=绝对文件路径
	 */
	public void exportExcel(String filePath, List<Ksjgnr> ksjgnrs) {
		Workbook wb = new HSSFWorkbook();
		CellStyle cs = wb.createCellStyle();
		cs.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		cs.setBorderBottom((short) 1);
		Font font = wb.createFont();
		font.setBold(true);
		font.setFontName("宋体");
		font.setFontHeight((short) 240);
		cs.setFont(font);

		CellStyle cs2 = wb.createCellStyle();
		cs2.setAlignment(CellStyle.ALIGN_CENTER);
		cs2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		cs2.setBorderBottom((short) 1);
		Font font2 = wb.createFont();
		font2.setFontName("宋体");
		font2.setFontHeight((short) 240);
		cs2.setFont(font2);

		CreationHelper createHelper = wb.getCreationHelper();
		Sheet sheet = wb.createSheet("导出结果");

		Row row = sheet.createRow((short) 0);
		Cell cell1 = row.createCell(0);
		cell1.setCellStyle(cs);
		cell1.setCellValue(createHelper.createRichTextString("罪犯编号"));
		int i = 1;
		for (Ksjgnr ksjgnr : ksjgnrs) {
			Cell cell = row.createCell(i);
			cell.setCellStyle(cs);
			cell.setCellValue(createHelper.createRichTextString("题目="+ksjgnr.getKtxh()));
			i++;
		}
		
		Row row2 = sheet.createRow((short) 1);
		Cell cell2 = row2.createCell(0);
		cell2.setCellStyle(cs);
		cell2.setCellValue(createHelper.createRichTextString(System.getProperty("bh")));
		int j = 1;
		for (Ksjgnr ksjgnr : ksjgnrs) {
			Cell cell = row2.createCell(j);
			cell.setCellStyle(cs);
			cell.setCellValue(createHelper.createRichTextString(ksjgnr.getKtda()));
			j++;
		}
		
		outSaveFile(wb, filePath);
	}

	public void outSaveFile(Workbook wb, String filePath) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(filePath);
			wb.write(fos);
			fos.close();
			wb.close();
		} catch (IOException e) {

		}
	}
}
