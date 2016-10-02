package Datatypes;

public class DataLogin {

	private String nick;
	private boolean existe_token;
	
	public DataLogin(String nick, boolean existe_token) {
		this.nick = nick;
		this.existe_token = existe_token;
	}

	public String getNick() {
		return nick;
	}

	public boolean isExiste_token() {
		return existe_token;
	}
	
	
	
}
