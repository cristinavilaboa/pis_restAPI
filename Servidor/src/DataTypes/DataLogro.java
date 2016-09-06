package DataTypes;

public class DataLogro {

	private String desc;
	private int cant;

	//constructor
	public DataLogro(String desc, int cant) {
		this.desc = desc;
		this.cant = cant;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getCant() {
		return cant;
	}
	public void setCant(int cant) {
		this.cant = cant;
	} 

}
