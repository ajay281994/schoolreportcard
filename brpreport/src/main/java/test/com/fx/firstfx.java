package test.com.fx;
import java.awt.Label;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class firstfx extends Application{

	public static void main(String[] args) {
	launch(args);
	}

	@SuppressWarnings("restriction")
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("School ERP");
		Parent p = new Parent() {
		};
        Scene scene = new Scene(p , 200, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
      
	}

}
