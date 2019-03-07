import com.css.module.view.admin.AdminStage;

import javafx.application.Application;
import javafx.stage.Stage;

public class AdminMain extends Application {


	
	public static void main(String[] args) {
		System.out.println("50010441315".substring(5, 11));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.show();
		AdminStage admin = new AdminStage();
	}
	
	public void init() {
		
	}

}
