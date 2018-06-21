package app;

public class Encoder {
	
	private String code;
	
	public Encoder(String code) {
		this.code = code;
	}
	
	public String encrypt(String str) {
		StringBuilder sb = new StringBuilder();
		for(int i=0,j=0;i<str.length();i++,j++) {
			if(j==code.length()){
				j=0;
			}
			char ch=  (char) (str.charAt(i)+code.charAt(j));
			sb.append(""+ch);
		}
		return sb.toString();
	}
	
	public String decrypt(String str) {
		StringBuilder sb = new StringBuilder();
		for(int i=0,j=0;i<str.length();i++,j++) {
			if(j==code.length()){
				j=0;
			}
			char ch=  (char) (str.charAt(i)-code.charAt(j));
			sb.append(""+ch);
		}
		return sb.toString();
	}
}
