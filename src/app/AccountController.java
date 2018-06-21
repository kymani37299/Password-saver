package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class AccountController {

	private ObservableList<Account> viewList;
	private ArrayList<Account> accounts;
	private String code;
	
	
	public AccountController(ObservableList<Account> accountList) {
		this.viewList = accountList;
		code = "";
		loadAccounts();
	}
	
	@SuppressWarnings("unchecked")
	private void loadAccounts() {
		File file = new File("data.bin");
		if(!file.exists()) {
			this.accounts = new ArrayList<Account>();
			return;
		}
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			Object obj = in.readObject();
			this.accounts = (ArrayList<Account>) obj;
			in.close();
			for(Account acc:this.accounts) {
				acc.setID();
			}
		} catch (Exception e){
			this.accounts = new ArrayList<Account>();
		}
	}
	
	private boolean saveAccounts() {
		File file = new File("data.bin");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				return false;
			}
		}
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(this.accounts);
			out.close();
		} catch (Exception e){
			return false;
		}
		return true;
	}
	
	private Account encryptAccount(String code,Account account) {
		Encoder encoder = new Encoder(code);
		String server = encoder.encrypt(account.getServer());
		String username = encoder.encrypt(account.getUsername());
		String password = encoder.encrypt(account.getPassword());
		String email = encoder.encrypt(account.getEmail());
		String other = encoder.encrypt(account.getOther());
		String testingVal = encoder.encrypt(account.getTestingVal());
		Account acc = new Account(server, username, password, email, other, testingVal);
		acc.setID(account.getID());
		return acc;
	}
	
	private Account decryptAccount(String code,Account account) {
		Encoder encoder = new Encoder(code);
		String server = encoder.decrypt(account.getServer());
		String username = encoder.decrypt(account.getUsername());
		String password = encoder.decrypt(account.getPassword());
		String email = encoder.decrypt(account.getEmail());
		String other = encoder.decrypt(account.getOther());
		String testingVal = encoder.decrypt(account.getTestingVal());
		Account acc = new Account(server, username, password, email, other, testingVal);
		acc.setID(account.getID());
		return acc.isValid() ? acc : null;
	}
	
	public boolean save() {
		return this.saveAccounts();

	}
	
	private void updateAccounts() {
		viewList.clear();
		if(code==null || code.equals("")){
			return;
		}
		for(Account account : this.accounts) {
			Account acc = decryptAccount(code, account);
			if(acc!=null) {
				viewList.add(acc);
			}
		}
	}
	
	public void addAccount(Account account) {
		if(this.code==null || this.code.equals("")){
			return;
		}
		this.accounts.add(this.encryptAccount(code, account));
		this.updateAccounts();
	}
	
	public void removeAccount(Account account) {
		this.accounts.remove(account);
		this.updateAccounts();
	}
	
	public void editAccount(Account oldAcc,Account newAcc) {
		this.accounts.remove(oldAcc);
		this.addAccount(newAcc);
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
		this.updateAccounts();
	}
	
}
