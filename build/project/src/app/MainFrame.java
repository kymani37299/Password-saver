package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainFrame extends Stage{

	private AccountController accController;
	
	public MainFrame() {
		ObservableList<Account> accList = FXCollections.observableArrayList();
		accController =  new AccountController(accList);
		
		BorderPane mainLayout = new BorderPane();
		mainLayout.setPadding(new Insets(15));
		
		Button btnAction = new Button();
		
		//List
		HBox centerLayout = new HBox(15);
		
		AccountView accView = new AccountView(accController,btnAction);
		
		ListView<Account> accListView = new ListView<Account>();
		accListView.setItems(accList);
		accListView.setMinWidth(300);
		accListView.getSelectionModel().selectedItemProperty().addListener((obs,oldVal,newVal) -> {
			accView.setForEdit();
			accView.update((Account)newVal);
		});
		
		
		
		centerLayout.getChildren().addAll(accListView,accView);
		mainLayout.setCenter(centerLayout);
		
		//Buttons
		HBox btnBox = new HBox(5);
		btnBox.setPadding(new Insets(0,0,5,0));
		
		btnAction.setText("Add");
		btnAction.setOnAction(e -> {
			if(btnAction.getText().equals("Add")){
				btnAction.setText("Edit");
				accView.setForAdd();
			}else{
				btnAction.setText("Add");
				accView.setForEdit();
			}
		});
		
		Button btnRemove = new Button("Remove");
		btnRemove.setOnAction(e -> {
			Account selected = accListView.getSelectionModel().getSelectedItem();
			if(selected!=null) {
				accController.removeAccount(selected);
			}
		});
		
		PasswordField tfCode = new PasswordField();
		tfCode.textProperty().addListener((obs,oldVal,newVal) -> {
			accController.setCode((String)newVal);
			accView.update(null);
		});
		
		btnBox.getChildren().addAll(btnAction,btnRemove,tfCode);
		mainLayout.setTop(btnBox);
		
		this.setScene(new Scene(mainLayout));
		this.setTitle("Password saver");
		this.setResizable(false);
	}
	
	public void stop() {
		System.out.println(accController.save());
	}
	
}
