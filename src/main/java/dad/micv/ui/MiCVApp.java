package dad.micv.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MiCVApp extends Application {
	
	public static Stage primaryStage;
	
	private MainController mainController;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		MiCVApp.primaryStage = primaryStage;

		mainController = new MainController();
		
		primaryStage.setTitle("MiCV");
		primaryStage.getIcons().add(new Image("/images/cv64x64.png"));
		primaryStage.setScene(new Scene(mainController.getView()));
		primaryStage.show();

	}

}
