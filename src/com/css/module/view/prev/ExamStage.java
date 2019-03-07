package com.css.module.view.prev;

import java.util.List;

import com.css.common.utils.CoreUtil;
import com.css.common.utils.StrKit;
import com.css.common.view.CssStage;
import com.css.module.model.Ksjg;
import com.css.module.model.Kslb;
import com.css.module.service.AdminService;
import com.css.module.service.ExamService;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ExamStage {
	
	public ExamStage() {
		CssStage stage = new CssStage();
		StackPane stack = new StackPane();
		ScrollPane s = new ScrollPane();
		
		HBox h =  new HBox();
		h.setStyle("-fx-padding: 50px 50px 50px 50px;");
		h.setAlignment(Pos.CENTER);
		h.setSpacing(30);
		List<Kslb> kslbs = AdminService.selectKslbAll();
		for (Kslb kslb : kslbs) {
			Image image = new Image("/image/doc1.png");
			ImageView view = new ImageView(image);
			Button button = new Button("进入"+kslb.getLbmc()+"评测", view);
			button.getStyleClass().add("btn");
			button.setOnMouseClicked((e)->{
				String bh = System.getProperty("bh");
				Ksjg ksjg = ExamService.selectKsjgByBhAndKslbbm(bh, kslb.getBm());
				if(ksjg.getId() != null && ksjg.getId() != "") {
					if(ksjg.getSfwc().equals("1")) {   //如果以前的评测已经完成就重新生成一个新的评测
						Ksjg newksjg = new Ksjg();
						newksjg.setBh(bh);
						newksjg.setId(CoreUtil.uuid32());
						newksjg.setKslbbm(kslb.getBm());
						newksjg.setKsmc(kslb.getLbmc());
						newksjg.setSfwc("0");
						newksjg.setXh((ksjg.getXh()+1));
						ExamService.saveKsjg(newksjg);
						System.out.println("111111111111111111"+ksjg.getId());
					}
				}else { //重新生成一个新的评测
					System.out.println("00000000000000000"+ksjg.getId()+"。");
					ksjg = new Ksjg();
					ksjg.setBh(bh);
					ksjg.setId(CoreUtil.uuid32());
					ksjg.setKslbbm(kslb.getBm());
					ksjg.setKsmc(kslb.getLbmc());
					ksjg.setSfwc("0");
					ksjg.setXh(0);
					ExamService.saveKsjg(ksjg);
				}
				TestStage test = new  TestStage(kslb.getLbmc(), kslb.getBm());
			});
			h.getChildren().add(button);
		}
		s.setContent(h);
		stack.getChildren().add(s);
		stage.setView(stack, 1038, 642);
	}
}
