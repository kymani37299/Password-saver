package app;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AccountView extends GridPane{
		
	private final int maxLength = 300;
	
	private TextField tfServer;
	private TextField tfUsername;
	private TextField tfPassword;
	private TextField tfEmail;
	private TextArea tfOther;
	
	private Button btnAction;
	
	private AccountController accountController;
	
	public AccountView(AccountController accountController) {
		this.setHgap(5);
		this.setVgap(5);
		
		this.accountController = accountController;
		
		this.tfServer = new TextField();
		this.tfUsername = new TextField();
		this.tfPassword = new TextField(); //TODO: Implement show/hide button
		this.tfEmail = new TextField();
		this.tfOther = new TextArea();
		this.btnAction = new Button("Add");
		
		this.tfServer.setMaxWidth(maxLength);
		this.tfUsername.setMaxWidth(maxLength);
		this.tfPassword.setMaxWidth(maxLength);
		this.tfEmail.setMaxWidth(maxLength);
		this.tfOther.setMaxWidth(maxLength);
		this.btnAction.setMaxWidth(maxLength);
		
		
		this.add(new Label("Server: "), 0, 0);
		this.add(new Label("Username: "), 0, 1);
		this.add(new Label("Email: "), 0, 2);
		this.add(new Label("Password: "), 0, 3);
		this.add(new Label("Other: "), 0, 4);
		
		this.add(this.tfServer, 1, 0);
		this.add(this.tfUsername, 1, 1);
		this.add(this.tfEmail, 1, 2);
		this.add(this.tfPassword, 1, 3);
		this.add(this.tfOther, 1, 4);
		
		this.add(this.btnAction, 0, 5);
		
		this.setForAdd();
	}
	
	public void update(Account account) {
		this.tfServer.setText(account==null ? "" : account.getServer());
		this.tfUsername.setText(account==null ? "" : account.getUsername());
		this.tfPassword.setText(account==null ? "" : account.getPassword());
		this.tfEmail.setText(account==null ? "" : account.getEmail());
		this.tfOther.setText(account==null ? "" : account.getOther());
	}
	
	public void setForAdd() {
		if(this.btnAction.getText().equals("Add")){
			this.update(null);
		}
		this.btnAction.setText("Add");
		this.btnAction.setOnAction(e -> {
			if(this.tfServer.getText().length()==0){
				return;
			}
			Account acc = new Account(this.tfServer.getText(),this.tfUsername.getText(),this.tfPassword.getText(),this.tfEmail.getText(),this.tfOther.getText());
			acc.setID();
			this.accountController.addAccount(acc);
		});
	}
	
	public void setForEdit(Account account) {
		this.btnAction.setText("Edit");
		this.btnAction.setOnAction(e -> {
			if(account!=null){
				Account acc = new Account(this.tfServer.getText(),this.tfUsername.getText(),this.tfPassword.getText(),this.tfEmail.getText(),this.tfOther.getText());
				long accId = account.getID();
				acc.setID(accId);
				this.accountController.editAccount(account, acc);
			}
		});
	}
	
}