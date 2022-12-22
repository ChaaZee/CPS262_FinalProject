package game_of_pig;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		primaryStage.setTitle("Game of Pigs");
		primaryStage.setScene(new Scene(menu));
		primaryStage.show();
	}


}
