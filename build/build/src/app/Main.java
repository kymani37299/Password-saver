package app;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	private MainFrame mainFrame;
	
	@Override
	public void stop() throws Exception {
		this.mainFrame.stop();
		super.stop();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.mainFrame = new MainFrame();
		this.mainFrame.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
