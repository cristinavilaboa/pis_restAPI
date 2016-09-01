package Manejadores;

public class ManejadorUsuario {
	
	private static ManejadorUsuario instancia = new ManejadorUsuario();
	
	private ManejadorUsuario(){};
	
	public static ManejadorUsuario getInstancia(){
		return instancia;
	}
}
